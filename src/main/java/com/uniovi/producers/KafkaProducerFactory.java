package com.uniovi.producers;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.uniovi.entitites.Incident;
import com.uniovi.serializer.InciSerializer;

@Configuration
@EnableKafka
public class KafkaProducerFactory {
	@Bean
    public ProducerFactory<String, Incident> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> incidents = new HashMap<>();
        incidents.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        incidents.put(ProducerConfig.RETRIES_CONFIG, 0);
        incidents.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        incidents.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        incidents.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        incidents.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        incidents.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, InciSerializer.class);
        return incidents;
    }

    @Bean
    public KafkaTemplate<String, Incident> kafkaTemplate() {
        return new KafkaTemplate<String, Incident>(producerFactory());
    }
}
