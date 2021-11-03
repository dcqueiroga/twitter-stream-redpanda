# twitter-stream-redpanda

This project provides a simple demonstration of how to develop a Kafka producer application in Java which will ingest data from Twitter API and publish it to a Kafka topic for consumer application to subscribe and consume messages.

## Introduction

### What is Kafka?

[Kafka](https://kafka.apache.org/) is a high throughput distributed messaging system which allows you to create data streaming pipelines. Their pros include:
- High-throughput
- Fault-tolerance
- Low-latency

In order to make it easier to use, there are some third-party providers that includes lots of Kafka features. Thinking of that, we are going to use Redpanda in our project.

### Why use RedPanda?

[RedPanda](https://vectorized.io/redpanda/) is a streaming platform, Kafka API compatible for mission-critical workloads built for modern apps. Their pros include:
- Reliable message delivery
- 10x faster speed than regular Kafka
- Ultra-low latencies, due to their thread-per-core architecture
- Reduced operational complexity
- Production ready
- Source available and free to use!

### Schema Registry: what is it and how it works

Schema Registry is a powerful concept that enforces data governance within our Kafka architecture. Due to the decoupled nature of Kafka, producers and consumers do not communicate with each other directly, but rather information transfer happens via topic. At the same time, the consumer still needs to know the type of data the producer is sending in order to deserialize it. In order to have a common data type that must be agreed upon the two parts, the schema registry is designed for.  

How it works: the producer talks to the schema registry first and checks if the schema is available; if it's not, it registers a new one and caches it. Once the producer gets the schema, it will serialize the data with it and send it to Kafka in binary format with a unique schema ID. When the consumer processes the message, it will communicate with the schema registry using the received schema ID and deserialize the data using the same schema. If there is a schema mismatch, the schema registry will throw an error letting the producer know that it's breaking the schema agreement between them. 

RedPanda supports schema registry: it resides outside of the Kafka cluster and handles distribution of schemas to the producer and consumer by storing a copy of schema in its local cache. You can find more about it [here](https://vectorized.io/blog/schema_registry/).

## Getting started

### Requirements

1. [Java 11](https://jdk.java.net/11/)
2. [Spring Cloud](https://spring.io/blog/2021/09/23/spring-cloud-2020-0-4-has-been-released)
3. [Docker](https://www.docker.com/get-started)
4. [Kafka using RedPanda](https://vectorized.io/redpanda)
5. [Twitter developer account](https://developer.twitter.com/en/apply-for-access) with API keys and access tokens
6. [Elasticsearch and Kibana](https://www.elastic.co/pt/what-is/elk-stack) for further data analysis

### Installing Kafka, Elasticsearch and Kibana

First, we need to install Kafka, Elasticsearch and Kibana locally. To do that, start the docker-compose running the command below on the root folder of this project:

```console
docker-compose up -d
```

This docker-compose configuration file looks like as it follows: 

```console
    version: '3.7'
    
    services:
      redpanda:
        command:
        - redpanda
        - start
        - --smp
        - '1'
        - --reserve-memory
        - 0M
        - --overprovisioned
        - --node-id
        - '0'
        - --kafka-addr
        - PLAINTEXT://0.0.0.0:29092,OUTSIDE://0.0.0.0:9092
        - --advertise-kafka-addr
        - PLAINTEXT://redpanda:29092,OUTSIDE://localhost:9092
        image: docker.vectorized.io/vectorized/redpanda:v21.7.6
        container_name: redpanda-1
        ports:
        - 9092:9092
        - 29092:29092
        
      elasticsearch:
        container_name: elasticsearch-1
        image: docker.elastic.co/elasticsearch/elasticsearch:7.15.1
        network_mode: elastic
        environment:
        - discovery.type=single-node
        - cluster.routing.allocation.disk.threshold_enabled=true
        - cluster.routing.allocation.disk.watermark.low=65%
        - cluster.routing.allocation.disk.watermark.high=70%
        - xpack.security.enabled=true
        - xpack.security.audit.enabled=true
        - ELASTIC_PASSWORD=elastic
        ports:
        - 9200:9200
        - 9300:9300
        
      kibana:
        container_name: kibana-1
        image: docker.elastic.co/kibana/kibana:7.15.1
        network_mode: elastic
        environment:
        - ELASTICSEARCH_HOSTS=http://elasticsearch-1:9200
        - ELASTICSEARCH_USERNAME=elastic
        - ELASTICSEARCH_PASSWORD=elastic
        ports:
        - 5601:5601
        depends_on:
        - elasticsearch
```

### Solution overview

In this project, I've developed two applications in Java with Spring Cloud: a producer, to ingest data from Twitter API data source and publish it to a Kafka topic; and a consumer, to consume messages from that topic, process and ingest them into an Elasticsearch NoSQL database for further analysis in Kibana.

### Creating the Producer Module

To start, create the first application using [Spring Initializr](https://start.spring.io/) and add the following Maven dependencies in the pom.xml file:

```console
    <!-- Kafka -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-stream</artifactId>
        <version>3.0.13.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-stream-binder-kafka</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-streams</artifactId>
    </dependency>
    
    <!-- Twitter -->
    <dependency>
        <groupId>org.springframework.social</groupId>
        <artifactId>spring-social-twitter</artifactId>
        <version>1.1.2.RELEASE</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-social-twitter</artifactId>
        <version>1.5.22.RELEASE</version>
    </dependency>
    
    <!-- Schema Registry -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-stream-schema</artifactId>
        <version>2.2.1.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.apache.avro</groupId>
        <artifactId>avro-compiler</artifactId>
        <version>${avro.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.avro</groupId>
        <artifactId>avro-maven-plugin</artifactId>
        <version>${avro.version}</version>
    </dependency>
    <dependency>
        <groupId>io.confluent</groupId>
        <artifactId>kafka-avro-serializer</artifactId>
        <version>${kafka-avro-serializer.version}</version>
    </dependency>
```

Then, implement two classes in order to place Schema Registry and Twitter configurations:

```console
@Configuration
public class SchemaRegistryConfig {

    @Value("${spring.cloud.stream.kafka.binder.producer-properties.schema.registry.url}")
    private String schemaRegistryUrl;

    @Bean
    public SchemaRegistryClient schemaRegistryClient() {
        ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
        client.setEndpoint(schemaRegistryUrl);
        return client;
    }
}
```
```console
@Configuration
public class TwitterConfig {
    @Bean
    public TwitterTemplate twitterTemplate(final @Value("${twitter.api-key}") String appId,
                                           final @Value("${twitter.api-secret}") String appSecret,
                                           final @Value("${twitter.access-token}") String accessToken,
                                           final @Value("${twitter.access-token-secret}") String accessTokenSecret) {
        return new TwitterTemplate(appId, appSecret,accessToken,accessTokenSecret);
    }
}
```

To complete Twitter configuration, please go to the application.properties file and fill in with your credencials and tokens.

Now, create an [Avro](https://avro.apache.org/) schema file that will represent a tweet for the value that will be stored on the topic:

```console
{
    "type": "record",
    "name": "TweetEntity",
    "namespace": "com.example.twitterstreamredpanda.domain",
    "fields": [
        { "name": "id", "type": "int" },
        { "name": "text", "type": "string" },
        { "name": "createdAt", "type": "int", "logicalType": "date" },
        { "name": "fromUser", "type": "string" },
        { "name": "languageCode", "type": "string" },
        { "name": "source", "type": "string" },
        { "name": "retweetCount", "type": "int" },
        { "name": "retweeted", "type": "boolean" },
        { "name": "favorited", "type": "boolean" },
        { "name": "favoriteCount", "type": "int" }
    ]
}
```

To simplify, we are using an avro-maven-plugin to auto-generate this Java class running the following command:

```console
mvn generate-sources
```

It's time to implement the Kafka message producer. For that, we are using the Spring Cloud Stream and the implementation is as simple as that: include the following information on the application.properties file:

```console
spring.cloud.stream.bindings.tweets-out.destination = tweets
spring.cloud.stream.bindings.tweets-out.content-type = application/*+avro
spring.cloud.stream.bindings.tweets-out.producer.partition-count = 4

spring.cloud.stream.kafka.binder.auto-add-partitions = true
spring.cloud.stream.kafka.binder.producer-properties.key.serializer = io.confluent.kafka.serializers.KafkaAvroSerializer
spring.cloud.stream.kafka.binder.producer-properties.value.serializer = io.confluent.kafka.serializers.KafkaAvroSerializer
spring.cloud.stream.kafka.binder.producer-properties.schema.registry.url = http://localhost:8081

spring.cloud.stream.kafka.binder.transaction.producer.use-native-encoding = true
```

Then, create the binder as an interface that will work as a bridge between the application and the external messaging system (in our case, Kafka):

```console
public interface TwitterBinder {

    String TWEETS_OUT = "tweets-out";

    @Output(TWEETS_OUT)
    SubscribableChannel tweetsOut();
}
```

Finally, use the annotation below to enable this binding connection we've just created:

```console
@EnableBinding(TwitterBinder.class)
```

The next step is develop the class that will fetch data from Twitter:

```console
    @Slf4j
    @Service
    @AllArgsConstructor
    public class TwitterProducer {
    
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
            };
    
            // start stream when run a service
            List<StreamListener> listeners = new ArrayList<>();
            FilterStreamParameters parameters = listParameters();
            listeners.add(streamListener);
            twitter.streamingOperations().filter(parameters, listeners);
        }
        ...
```

And that's it, the application is ready to get tweets and produce messages to Kafka!

### Creating the Consumer Module

Now, let's create the consumer application and add the following Maven dependencies in your pom.xml file:

```console
    <!-- Kafka -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-stream</artifactId>
        <version>3.0.13.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-stream-binder-kafka</artifactId>
    </dependency>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-streams</artifactId>
    </dependency>

    <!-- Twitter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-social-twitter</artifactId>
        <version>1.5.22.RELEASE</version>
    </dependency>

    <!-- Elasticsearch -->
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-elasticsearch</artifactId>
        <version>4.0.0.RELEASE</version>
    </dependency>

    <!-- Schema Registry -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-stream-schema</artifactId>
        <version>2.2.1.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>org.apache.avro</groupId>
        <artifactId>avro-compiler</artifactId>
        <version>${avro.version}</version>
    </dependency>
    <dependency>
        <groupId>org.apache.avro</groupId>
        <artifactId>avro-maven-plugin</artifactId>
        <version>${avro.version}</version>
    </dependency>
    <dependency>
        <groupId>io.confluent</groupId>
        <artifactId>kafka-avro-serializer</artifactId>
        <version>${kafka-avro-serializer.version}</version>
    </dependency>
```

Then, implement two other classes in order to place Schema Registry (the same as before) and Elasticsearch configurations:

```console
    @Configuration
    @EnableElasticsearchRepositories(basePackages = "com.example.twitterstreamredpanda.domain.repository")
    public class ElasticsearchConfig {
    
        @Value("${spring.elasticsearch.rest.uris}")
        private String elasticsearchHost;
    
        @Value("${spring.elasticsearch.rest.username}")
        private String elasticsearchUsername;
    
        @Value("${spring.elasticsearch.rest.password}")
        private String elasticsearchPassword;
    
        @Bean
        public RestHighLevelClient client() {
            ClientConfiguration clientConfiguration =
                    ClientConfiguration.builder()
                    .connectedTo(elasticsearchHost)
                    .withBasicAuth(elasticsearchUsername, elasticsearchPassword)
                    .build();
    
            return RestClients.create(clientConfiguration).rest();
        }
    
        @Bean
        public ElasticsearchOperations elasticsearchTemplate() {
            return new ElasticsearchRestTemplate(client());
        }
    }
```

Similar to what we've done before on the producer, we'll create the same schema registry configuration class and Avro schema file to the consumer.

In order to subscribe to the topic and receive the tweet messages, include the following information on the application.properties:

```console
spring.cloud.stream.bindings.tweets-in.destination = tweets
spring.cloud.stream.bindings.tweets-in.content-type = application/*+avro
spring.cloud.stream.bindings.tweets-in.group = ${spring.application.name}-group

spring.cloud.stream.kafka.binder.consumer-properties.key.deserializer = io.confluent.kafka.serializers.KafkaAvroDeserializer
spring.cloud.stream.kafka.binder.consumer-properties.value.deserializer = io.confluent.kafka.serializers.KafkaAvroDeserializer
spring.cloud.stream.kafka.binder.consumer-properties.schema.registry.url = http://localhost:8081
spring.cloud.stream.kafka.binder.consumer-properties.specific.avro.reader = true
```

Then, implement a binder interface and a consumer class:

```console
    public interface TwitterBinder {
    
        String TWEETS_IN = "tweets-in";
    
        @Input(TWEETS_IN)
        SubscribableChannel tweetsIn();
    }
```
```console
    @Slf4j
    @Component
    public class TwitterConsumer {
    
        @Autowired
        private TweetRepository tweetRepository;
    
        @Autowired
        private ModelMapper modelMapper;
    
        @StreamListener(TwitterBinder.TWEETS_IN)
        public void consumer(@Payload TweetEntity tweet) {
            try {
                log.info("Message consumed: {}", tweet);
                TweetElasticEntity tweetEs = convert(tweet);
                tweetRepository.save(tweetEs);
            } catch (Exception e) {
                log.error("Error executing TwitterConsumer.consumer method: {}", e.getLocalizedMessage());
            }
        }
    
        private TweetElasticEntity convert(TweetEntity tweetEntity) {
            return modelMapper.map(tweetEntity, TweetElasticEntity.class);
        }
    }
```

Finally, use the annotation below to enable this binding connection we've just created:

```console
    @EnableBinding(TwitterBinder.class)
```

Now that we are able to receive messages from the topic, implement a repository and an entity class so that we can send this data to the Elasticsearch:

```console
    public interface TweetRepository extends ElasticsearchRepository<TweetElasticEntity, Long>
```
```console
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
        
        ...

```

And it's done! Now we can run the producer app to connect to Twitter API, receive real-time data and send these messages to a Kafka topic, and then run the consumer app to subscribe to this topic, receive those messages and send processed data to the Elasticsearch. There, we can do as many analysis as we want over all those informations!

Once we've configured this topic with four partitions, we can run up to four instances of the consumer app in order to parallelize this work. ;)
