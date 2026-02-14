package week._0.week._0.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "notification-topic";

    public void sendNotificationEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
