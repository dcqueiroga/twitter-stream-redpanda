package com.example.twitterstreamredpanda.messaging;

import com.example.twitterstreamredpanda.domain.TweetEntity;
import com.example.twitterstreamredpanda.util.HashTagUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TwitterProducer {

    public static final String STR_SEPARATOR = ";";

    private final TwitterBinder twitterBinder;
    private final Twitter twitter;
    private final Environment env;

    public void run() {
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
        List<StreamListener> listeners = new ArrayList<>();
        FilterStreamParameters parameters = listParameters();
        listeners.add(streamListener);
        twitter.streamingOperations().filter(parameters, listeners);
    }

    private FilterStreamParameters listParameters() {
        String searchTerms = env.getProperty("twitter.search-terms");
        log.info("searchTerms: {}", searchTerms);
        FilterStreamParameters parameters = new FilterStreamParameters();
        if (searchTerms != null) {
            for (String term : searchTerms.split(STR_SEPARATOR)) {
                parameters.track(term);
            }
        }
        return parameters;
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
