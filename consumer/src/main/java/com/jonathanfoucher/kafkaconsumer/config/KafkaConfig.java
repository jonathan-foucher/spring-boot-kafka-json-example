package com.jonathanfoucher.kafkaconsumer.config;

import com.jonathanfoucher.kafkaconsumer.data.dto.MovieKey;
import com.jonathanfoucher.kafkaconsumer.data.dto.MovieValue;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaConfig {
    @Bean
    public ConsumerFactory<MovieKey, MovieValue> consumerMovieFactory(KafkaProperties kafkaProperties, SslBundles sslBundles) {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(sslBundles));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<MovieKey, MovieValue> kafkaListenerContainerMovieFactory(KafkaProperties kafkaProperties, SslBundles sslBundles) {
        ConcurrentKafkaListenerContainerFactory<MovieKey, MovieValue> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerMovieFactory(kafkaProperties, sslBundles));
        factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());
        return factory;
    }
}
