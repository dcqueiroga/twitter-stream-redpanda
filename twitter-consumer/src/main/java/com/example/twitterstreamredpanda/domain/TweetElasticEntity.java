package com.example.twitterstreamredpanda.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "tweets_index")
public class TweetElasticEntity implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Text, store = true)
    private String text;

    @Field(type = FieldType.Date, store = true, name = "createdAt", format = DateFormat.basic_date_time)
    private Date createdAt;

    @Field(type = FieldType.Text, store = true)
    private String fromUser;

    @Field(type = FieldType.Text, store = true)
    private String languageCode;

    @Field(type = FieldType.Text, store = true)
    private String source;

    @Field(type = FieldType.Boolean, store = true)
    private boolean retweeted;

    @Field(type = FieldType.Boolean, store = true)
    private boolean favorited;

    public void setCreatedAtByEpochDays(int epochDays) {
        this.createdAt = java.sql.Date.valueOf(LocalDate.EPOCH.plusDays(epochDays));
    }
}