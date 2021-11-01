package com.example.twitterstreamredpanda.messaging;

import com.example.twitterstreamredpanda.domain.Tweet;
import com.example.twitterstreamredpanda.domain.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(value = { TwitterBinder.class })
public class TwitterConsumer {

    @Autowired
    private TweetRepository tweetRepository;

    @StreamListener(target = TwitterBinder.TWEETS_IN)
    public void consumer(@Payload String message) {
        try {
            Thread.sleep(200);
            log.info("Message consumed: {}", message);

            Tweet tweet = Tweet.builder()
                    .message(message)
                    .build();
            tweetRepository.save(tweet);

        } catch (InterruptedException e) {
            log.error("Error executing TwitterConsumer.consumer method");
        } catch (Exception e) {
            log.error("Error executing TwitterConsumer.consumer method");
        }
    }
}
