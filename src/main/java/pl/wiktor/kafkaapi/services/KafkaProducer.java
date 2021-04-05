package pl.wiktor.kafkaapi.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class KafkaProducer {

    public final KafkaTemplate<String, String> kafkaTemplate;

    public final String topic;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
                         @Value("${spring.kafka.test-topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public String publish(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Message publish error: " + message, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Message publish success: " + message, result);
            }
        });
        return message;
    }
}
