package com.utiltube.kafka.video.consumer;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class VideoConsumer implements Runnable {
    private KafkaStream<byte[], byte[]> kafkaStream;
    private int threadNumber;
 
    public VideoConsumer(KafkaStream<byte[], byte[]> kafkaStream, int threadNumber) {
        this.threadNumber = threadNumber;
        this.kafkaStream = kafkaStream;
    }
    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
        
        while (it.hasNext())
            System.out.println("Thread " + threadNumber + ": " + new String(it.next().message()));
        
        System.out.println("Shutting down Thread: " + kafkaStream);
    }
}
