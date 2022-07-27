package com.example.demo.kafka;


import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

import static com.example.demo.utils.Constants.*;

public class ContentProducer {

    private KafkaProducer producer;

    public void initialize() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        producer = new KafkaProducer<String, Article>(props);
    }

    public void send(Article article) {
        ProducerRecord<String, Article> record = new ProducerRecord<>(KAFKA_ARTICLE_TOPIC
                , KAFKA_ARTICLE_TOPIC_PARTITION, article.getArticleId(), article);
        producer.send(record);
    }

}
