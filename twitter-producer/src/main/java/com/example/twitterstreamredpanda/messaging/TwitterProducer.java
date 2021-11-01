package com.example.twitterstreamredpanda.messaging;

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
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

    //@Bean
    @Scheduled(initialDelay = 5_000, fixedDelay = 5_000)
    public void createTwitterClient() {
        /** Setting up a connection   */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hbEndpoint = new StatusesFilterEndpoint();
        // Term that I want to search on Twitter
        trackTerms = new ArrayList<>();
        trackTerms.add("#NFLBrasil");
        hbEndpoint.trackTerms(trackTerms);

        //StreamingEndpoint endpoint = new StatusesSampleEndpoint();

        msgQueue = new LinkedBlockingQueue<>(30);

        // Twitter API and tokens
        Authentication hosebirdAuth = new OAuth1(apiKey, apiSecret, token, secret);

        twitterClient = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hbEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .build();

        //return staticTwitterClient;

        log.info("connecting on twitter...");
        twitterClient.connect();
        log.info("connecting on twitter: done!");

        try {
            while (true) {
                collect();
            }
        }
        finally {
            stop();
        }
    }

    private String collect() {
        log.info("collecting tweet...");
        String msg = null;
        try {
            msg = msgQueue.poll(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            stop();
        }
        if (msg != null) {
            log.info("sending it to topic...");
            twitterBinder.tweetsOut().send(MessageBuilder.withPayload(msg).build());
        }
        log.info("collecting tweet and sending it: done!");
        return msg;
    }

    private void stop() {
        log.info("shutting down client from twitter...");
        this.twitterClient.stop();
        log.info("shutting down client from twitter: done!");
    }
}
