package com.devsu.bank.client_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.devsu.bank.client_service.model.Client;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public ProducerFactory<String, Client> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), new JsonSerializer<Client>());
    }

    @Bean
    public KafkaTemplate<String, Client> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}