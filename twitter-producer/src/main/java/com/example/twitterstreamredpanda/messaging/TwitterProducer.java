package com.example.twitterstreamredpanda.messaging;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterProducer {

    private final TwitterBinder twitterBinder;

    private BlockingQueue<String> msgQueue;
    private List<String> trackTerms;
    private Client twitterClient;

    @Value("${twitter.api-key}")
    private String apiKey;

    @Value("${twitter.api-secret}")
    private String apiSecret;

    @Value("${twitter.access-token}")
    private String token;

    @Value("${twitter.access-token-secret}")
    private String secret;

    @Scheduled(initialDelay = 5_000, fixedDelay = 5_000)
    public void createTwitterClient() {
        Hosts twitterHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint twitterEndpoint = new StatusesFilterEndpoint();
        trackTerms = new ArrayList<>();
        trackTerms.add("#NFLBrasil");
        twitterEndpoint.trackTerms(trackTerms);
        msgQueue = new LinkedBlockingQueue<>(30);
        Authentication twitterAuth = new OAuth1(apiKey, apiSecret, token, secret);
        twitterClient = new ClientBuilder()
                .name("twitter-stream-redpanda-01")
                .hosts(twitterHosts)
                .authentication(twitterAuth)
                .endpoint(twitterEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .build();
        twitterClient.connect();
        log.info("connecting on twitter: done!");

        while (true) {
            collect();
        }
    }

    private String collect() {
        log.info("collecting tweet...");
        String msg = null;
        try {
            msg = msgQueue.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            this.twitterClient.stop();
            log.info("shutting down client from twitter: done!");
        }
        if (msg != null) {
            log.info("sending it to topic...");
            twitterBinder.tweetsOut().send(MessageBuilder.withPayload(msg).build());
        }
        log.info("collecting tweet and sending it: done!");
        return msg;
    }
}
