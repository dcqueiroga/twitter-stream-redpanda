package com.example.twitterstreamredpanda.messaging;

import com.example.twitterstreamredpanda.domain.TweetEntity;
import com.example.twitterstreamredpanda.util.HashTagUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TwitterService {

    private final TwitterBinder twitterBinder;
    private final Twitter twitter;

    @Value("${twitter.search-terms}")
    private String searchTerms;

    public void run() {
        List<StreamListener> listeners = new ArrayList<>();
        StreamListener streamListener = new StreamListener() {
            @Override
            public void onTweet(Tweet tweet) {
                // filter non-English tweets:
                if (!"en".equals(tweet.getLanguageCode())) {
                    return;
                }

                // filter tweets without hashTags:
                Iterator<String> hashTags = HashTagUtils.hashTagsFromTweet(tweet.getText());
                if (!hashTags.hasNext()) {
                    return;
                }

                // send tweet to Kafka topic
                log.info("User '{}', Tweeted : {}, from ; {}", tweet.getUser().getName(), tweet.getText(), tweet.getUser().getLocation());
                TweetEntity tweetEntity = buildTweetEntity(tweet);
                twitterBinder.tweetsOut().send(MessageBuilder
                        .withPayload(tweetEntity)
                        .build());
            }

            @Override
            public void onDelete(StreamDeleteEvent deleteEvent) {
                log.debug("onDelete");
            }

            @Override
            public void onLimit(int numberOfLimitedTweets) {
                log.debug("onLimit");
            }

            @Override
            public void onWarning(StreamWarningEvent warningEvent) {
                log.debug("onLimit");
            }
        };

        // start stream when run a service
        FilterStreamParameters parameters = new FilterStreamParameters();
        for (String term: searchTerms.split(";")){
            parameters.track(term);
        }
        listeners.add(streamListener);
        twitter.streamingOperations().filter(parameters, listeners);
    }

    private TweetEntity buildTweetEntity(Tweet tweet) {
        return TweetEntity.newBuilder()
                .setId((int) tweet.getId())
                .setText(tweet.getText())
                .setCreatedAt((int) tweet.getCreatedAt().getTime())
                .setFromUser(tweet.getFromUser())
                .setLanguageCode(tweet.getLanguageCode())
                .setSource(tweet.getSource())
                .setRetweetCount(tweet.getRetweetCount())
                .setRetweeted(tweet.getRetweetCount() > 0)
                .setFavoriteCount(tweet.getFavoriteCount())
                .setFavorited(tweet.getFavoriteCount() > 0)
                .build();
    }
}
