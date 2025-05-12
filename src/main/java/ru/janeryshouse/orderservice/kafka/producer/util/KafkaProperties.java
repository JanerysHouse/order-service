package ru.janeryshouse.orderservice.kafka.producer.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class KafkaProperties {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.topics.create-order}")
    private String createOrderTopicName;

    @Value("${spring.kafka.topics.notifications}")
    private String notifications;

    @Value("${spring.kafka.producer.info.replication-factor}")
    private Short replicationFactor;

    @Value("${spring.kafka.producer.info.message-lifetime}")
    private String messageLifetime;
}
