package com.example.twitterstreamredpanda;

import com.example.twitterstreamredpanda.messaging.TwitterBinder;
import com.example.twitterstreamredpanda.messaging.TwitterProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBinding(TwitterBinder.class)
@EnableScheduling
public class TwitterStreamRedpandaProducerApplication {

    @Autowired
    TwitterProducer twitterService;

    public static void main(String[] args) {
        SpringApplication.run(TwitterStreamRedpandaProducerApplication.class, args);
    }

    @Bean
    public void streamDataFromTwitter() {
        twitterService.run();
    }
}
