package com.example.twitterstreamredpanda.domain.repository;

import com.example.twitterstreamredpanda.domain.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TweetRepository extends ElasticsearchRepository<Tweet, String> {

//    Page<Tweet> findByAuthorsName(String name, Pageable pageable);
//
//    @Query("{\"bool\": {\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}")
//    Page<Tweet> findByAuthorsNameUsingCustomQuery(String name, Pageable pageable);
}