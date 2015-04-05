package com.utiltube.kafka.video.consumer;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utiltube.kafka.video.model.Video;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class VideoConsumer implements Runnable {
    private ObjectMapper objectMapper;
    private KafkaStream<byte[], byte[]> kafkaStream;
    private int threadNumber;
 
    public VideoConsumer(KafkaStream<byte[], byte[]> kafkaStream, int threadNumber) {
        this.threadNumber = threadNumber;
        this.kafkaStream = kafkaStream;
        this.objectMapper = new ObjectMapper();
    }
    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
        
        while (it.hasNext()) {
            byte[] messageData = it.next().message();
            try {
                Video videoFromMessage = objectMapper.readValue(messageData, Video.class);
                System.out.println("Thread:" + threadNumber + ".Consuming video: " + videoFromMessage);
            } catch (JsonParseException | JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        System.out.println("Shutting down Thread: " + kafkaStream);
    }
}
