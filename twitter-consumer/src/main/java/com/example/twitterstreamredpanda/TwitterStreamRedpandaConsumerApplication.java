package com.example.twitterstreamredpanda;

import com.example.twitterstreamredpanda.messaging.TwitterBinder;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableSchemaRegistryClient
@EnableBinding(TwitterBinder.class)
public class TwitterStreamRedpandaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterStreamRedpandaConsumerApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
