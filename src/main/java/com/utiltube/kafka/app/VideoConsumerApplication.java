package com.utiltube.kafka.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.utiltube")
public class VideoConsumerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(VideoConsumerApplication.class, args);
    }
}
