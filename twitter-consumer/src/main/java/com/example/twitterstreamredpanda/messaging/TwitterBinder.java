package com.example.twitterstreamredpanda.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TwitterBinder {

    String TWEETS_IN = "tweets-in";

    @Input(TWEETS_IN)
    SubscribableChannel tweetsIn();
}
