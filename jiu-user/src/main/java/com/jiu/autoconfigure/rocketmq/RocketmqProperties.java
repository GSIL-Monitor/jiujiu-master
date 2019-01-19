package com.jiu.autoconfigure.rocketmq;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
//@ConfigurationProperties(prefix = "rocketmq")
@Configuration
public class RocketmqProperties {

    @Value("${rocketmq.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.producerGroupName}")
    private String producerGroupName;
    @Value("${rocketmq.transactionProducerGroupName}")
    private String transactionProducerGroupName;
    @Value("${rocketmq.consumerGroupName}")
    private String consumerGroupName;
    @Value("${rocketmq.producerInstanceName}")
    private String producerInstanceName;
    @Value("${rocketmq.consumerInstanceName}")
    private String consumerInstanceName;
    @Value("${rocketmq.producerTranInstanceName}")
    private String producerTranInstanceName;
    @Value("${rocketmq.consumerBatchMaxSize}")
    private int consumerBatchMaxSize;
    @Value("${rocketmq.consumerBroadcasting}")
    private boolean consumerBroadcasting;
    @Value("${rocketmq.enableHistoryConsumer}")
    private boolean enableHistoryConsumer;
    @Value("${rocketmq.enableOrderConsumer}")
    private boolean enableOrderConsumer;
    @Value("${rocketmq.subscribe[0]}")
    private List<String> subscribe = new ArrayList<String>();

}
