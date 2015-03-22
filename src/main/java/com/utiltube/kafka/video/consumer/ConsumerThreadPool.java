package com.utiltube.kafka.video.consumer;

import static kafka.consumer.Consumer.createJavaConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utiltube.kafka.config.ConsumerConfigFactory;

@Component
public class ConsumerThreadPool {

    private static final String TOPIC = "test";
    private static final Integer NUM_THREADS = 1;
    
    @Autowired
    private ConsumerConfigFactory consumerConfigFactory;
    
    private ConsumerConnector consumer;
    private ExecutorService threadPool;
    
    public ConsumerThreadPool() {
        threadPool = Executors.newFixedThreadPool(NUM_THREADS);
    }
    
    @PostConstruct
    public void startConsuming() {
        ConsumerConfig consumerConfig = consumerConfigFactory.getConsumerConfig();
        consumer = createJavaConsumerConnector(consumerConfig);
        
        consume();
    }
    
    public void consume() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, NUM_THREADS);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(TOPIC);
     
        int threadNumber = 0;
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            threadPool.submit(new VideoConsumer(stream, threadNumber));
            threadNumber++;
        }
    }
}
