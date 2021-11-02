package com.example.twitterstreamredpanda.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfig {
    @Bean
    public TwitterTemplate twitterTemplate(final @Value("${twitter.api-key}") String appId,
                                           final @Value("${twitter.api-secret}") String appSecret,
                                           final @Value("${twitter.access-token}") String accessToken,
                                           final @Value("${twitter.access-token-secret}") String accessTokenSecret) {
        return new TwitterTemplate(appId, appSecret,accessToken,accessTokenSecret);
    }
}