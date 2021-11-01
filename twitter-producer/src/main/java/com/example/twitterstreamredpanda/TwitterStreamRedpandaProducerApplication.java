package com.example.twitterstreamredpanda;

import com.example.twitterstreamredpanda.messaging.TwitterBinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBinding(TwitterBinder.class)
@EnableScheduling
public class TwitterStreamRedpandaProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterStreamRedpandaProducerApplication.class, args);
    }

}
