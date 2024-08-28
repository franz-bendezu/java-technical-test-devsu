package com.devsu.bank.account_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.devsu.bank.account_service.dto.ClientDTO;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, ClientDTO> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(ClientDTO.class));
    }
}
