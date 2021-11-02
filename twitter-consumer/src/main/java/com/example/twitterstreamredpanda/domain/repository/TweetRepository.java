package com.example.twitterstreamredpanda.domain.repository;

import com.example.twitterstreamredpanda.domain.TweetElasticEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TweetRepository extends ElasticsearchRepository<TweetElasticEntity, Long> {
}