package com.jiu.autoconfigure.rocketmq;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "rocketmq")
public class RocketmqProperties {

    @Value("${namesrvAddr}")
    private String namesrvAddr;
    @Value("${producerGroupName}")
    private String producerGroupName;
    @Value("${transactionProducerGroupName}")
    private String transactionProducerGroupName;
    @Value("${consumerGroupName}")
    private String consumerGroupName;
    @Value("${producerInstanceName}")
    private String producerInstanceName;
    @Value("${consumerInstanceName}")
    private String consumerInstanceName;
    @Value("${producerTranInstanceName}")
    private String producerTranInstanceName;
    @Value("${consumerBatchMaxSize}")
    private int consumerBatchMaxSize;
    @Value("${consumerBroadcasting}")
    private boolean consumerBroadcasting;
    @Value("${enableHistoryConsumer}")
    private boolean enableHistoryConsumer;
    @Value("${enableOrderConsumer}")
    private boolean enableOrderConsumer;
    @Value("${subscribe[0]}")
    private List<String> subscribe = new ArrayList<String>();

}
