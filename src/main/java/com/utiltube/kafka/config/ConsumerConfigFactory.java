package com.utiltube.kafka.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import kafka.consumer.ConsumerConfig;

import org.springframework.stereotype.Component;

@Component
public class ConsumerConfigFactory {

    private static final String ZK_CONNECT = "localhost:2181";
    
    private ConsumerConfig consumerConfig;

    @PostConstruct
    private void createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", ZK_CONNECT);
        props.put("group.id", "Video-cg-0");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        consumerConfig = new ConsumerConfig(props);
    }

    public ConsumerConfig getConsumerConfig() {
        return consumerConfig;
    }
}