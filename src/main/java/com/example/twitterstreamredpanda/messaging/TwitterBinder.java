package com.example.twitterstreamredpanda.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface TwitterBinder {

    String TWEETS_IN = "tweets-in";
    String TWEETS_OUT = "tweets-out";

    @Input(TWEETS_IN)
    SubscribableChannel tweetsIn();

    @Output(TWEETS_OUT)
    SubscribableChannel tweetsOut();
}
