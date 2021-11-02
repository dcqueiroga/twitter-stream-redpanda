package com.example.twitterstreamredpanda.messaging;

import com.example.twitterstreamredpanda.domain.TweetEntity;
import com.example.twitterstreamredpanda.domain.TweetElasticEntity;
import com.example.twitterstreamredpanda.domain.repository.TweetRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TwitterConsumer {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ModelMapper modelMapper;

    @StreamListener(TwitterBinder.TWEETS_IN)
    public void consumer(@Payload TweetEntity tweet) {
        try {
            log.info("Message consumed: {}", tweet);
            tweetRepository.save(convert(tweet));
        } catch (Exception e) {
            log.error("Error executing TwitterConsumer.consumer method", e.getLocalizedMessage());
        }
    }

    private TweetElasticEntity convert(TweetEntity tweetEntity) {
        return modelMapper.map(tweetEntity, TweetElasticEntity.class);
    }
}
