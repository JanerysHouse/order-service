package ru.janeryshouse.orderservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.janeryshouse.orderservice.event.OrderPlacedEvent;
import ru.janeryshouse.orderservice.kafka.producer.util.KafkaProperties;
import ru.janeryshouse.orderservice.utils.JsonUtils;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final KafkaProperties kafkaProperties;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrder(OrderPlacedEvent event) {
        ProducerRecord<String, String> record = new ProducerRecord<>(
                kafkaProperties.getCreateOrderTopicName(),
                event.getOrderNumber(),
                JsonUtils.toJson(event)
        );

        kafkaTemplate.send(record)
                .thenAccept(result ->
                        log.info("Заказ отправлен в Kafka: {}", event.getOrderNumber()))
                .exceptionally(ex -> {
                    log.error("Ошибка отправки в Kafka: {}", ex.getMessage(), ex);
                    return null;
                });
    }
}
