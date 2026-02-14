package week._0.week._0.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import week._0.week._0.util.RateLimiter;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "notification-topic", groupId = "gpp-group")
    public void consume(String message) {

        System.out.println("Received Event: " + message);

        // Here apply rate limiting
        if (RateLimiter.allow(message)) {
            System.out.println("Notification Sent: " + message);
        } else {
            System.out.println("Rate Limit Exceeded");
        }
    }
}
