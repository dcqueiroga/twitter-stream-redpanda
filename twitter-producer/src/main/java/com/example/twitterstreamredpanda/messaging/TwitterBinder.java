package com.example.twitterstreamredpanda.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface TwitterBinder {

    String TWEETS_OUT = "tweets-out";

    @Output(TWEETS_OUT)
    SubscribableChannel tweetsOut();
}
