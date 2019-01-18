package com.jiu.autoconfigure.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(value = RocketmqProperties.class)
public class RocketmqAutoConfiguration {

    @Autowired
    private RocketmqProperties rocketmqProperties;

    //事件监听
    @Autowired
    private ApplicationEventPublisher publisher = null;

    private static boolean isFirstSub = true;

    private static long startTime = System.currentTimeMillis();


    /**
     * 容器初始化的时候打印信息
     */
    @PostConstruct
    public void init(){

        System.err.println(rocketmqProperties.getNamesrvAddr());
        System.err.println(rocketmqProperties.getProducerGroupName());
        System.err.println(rocketmqProperties.getConsumerBatchMaxSize());
        System.err.println(rocketmqProperties.getConsumerGroupName());
        System.err.println(rocketmqProperties.getConsumerInstanceName());
        System.err.println(rocketmqProperties.getProducerInstanceName());
        System.err.println(rocketmqProperties.getProducerTranInstanceName());
        System.err.println(rocketmqProperties.getTransactionProducerGroupName());
        System.err.println(rocketmqProperties.isConsumerBroadcasting());
        System.err.println(rocketmqProperties.isEnableHistoryConsumer());
        System.err.println(rocketmqProperties.isEnableOrderConsumer());
        System.err.println(rocketmqProperties.getSubscribe().get(0));

    }

    /**
     * 创建普通消息发送者实例
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQProducer defaultMQProducer() throws MQClientException {

        DefaultMQProducer producer = new DefaultMQProducer(rocketmqProperties.getProducerGroupName());
        producer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        producer.setInstanceName(rocketmqProperties.getProducerInstanceName());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.start();
        System.out.println("rocketmq producer server is starting....");
        return producer;
    }

    /**
     * 创建支持消息事务发送的实例
     * @return
     * @throws MQClientException
     */
    @Bean
    public TransactionMQProducer transactionMQProducer() throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer(rocketmqProperties.getTransactionProducerGroupName());
        producer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        producer.setInstanceName(rocketmqProperties.getProducerInstanceName());
        producer.setRetryTimesWhenSendAsyncFailed(10);
        producer.setCheckThreadPoolMinSize(2);
        producer.setCheckThreadPoolMaxSize(2);
        producer.setCheckRequestHoldMax(2000);;
        producer.start();
        System.out.println("rocketmq transaction producer server is starting....");
        return producer;
    }

    /**
     * 创建消息消费的实例
     * @return
     * @throws MQClientException
     */
    @Bean
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqProperties.getConsumerGroupName());
        consumer.setNamesrvAddr(rocketmqProperties.getNamesrvAddr());
        consumer.setInstanceName(rocketmqProperties.getConsumerInstanceName());
        //判断是否是广播模式
        if(rocketmqProperties.isConsumerBroadcasting()){
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }
        //设置批量消费
        consumer.setConsumeMessageBatchMaxSize(rocketmqProperties.getConsumerBatchMaxSize() == 0 ? 1 : rocketmqProperties.getConsumerBatchMaxSize());
        //获取topic
        List<String> subscribeList = rocketmqProperties.getSubscribe();
        for (String subscribe:subscribeList
                ) {
            consumer.subscribe(subscribe.split(":")[0],subscribe.split(":")[1]);
        }

        //顺序消费
        if(rocketmqProperties.isEnableHistoryConsumer()){
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

                    try {
                        context.setAutoCommit(true);
                        msgs = filterMessage(msgs);
                        if (msgs.size() == 0) {
                            return ConsumeOrderlyStatus.SUCCESS;
                        }
                        publisher.publishEvent(new MessageEvent(msgs, consumer));
                    }catch (Exception e){
                        e.printStackTrace();
                        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
        }else{  //并发模式
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    try {
                        msgs = filterMessage(msgs);
                        if(msgs.size() == 0){
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                        publisher.publishEvent(new MessageEvent(msgs,consumer));
                    }catch (Exception e){
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                    try {
                        consumer.start();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("rocketmq consumer server is starting....");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return consumer;
    }

    private List<MessageExt> filterMessage(List<MessageExt> msgs) {
        if(isFirstSub && !rocketmqProperties.isEnableHistoryConsumer()){
            msgs = msgs.stream().filter(item -> startTime - item.getBornTimestamp() < 0).collect(Collectors.toList());
        }
        if(isFirstSub && msgs.size() > 0){
            isFirstSub = false;
        }
        return msgs;
    }
}
