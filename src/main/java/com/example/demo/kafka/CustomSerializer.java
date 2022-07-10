package com.example.demo.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

@Slf4j
public class CustomSerializer<Article extends Serializable> implements Serializer<Article> {
    public CustomSerializer(){}
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public byte[] serialize(String topic, Article data) {
        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }

    @Override
    public void close() {
    }
}
