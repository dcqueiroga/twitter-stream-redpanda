package com.example.twitterstreamredpanda.domain;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Builder
@Document(indexName = "blog", type = "tweet")
public class Tweet {

    @Id
    private String id;

    private String message;

//    @Field(type = FieldType.Nested, includeInParent = true)
//    private List<Author> authors;

    // standard getters and setters
}