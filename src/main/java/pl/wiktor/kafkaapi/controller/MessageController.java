package pl.wiktor.kafkaapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wiktor.kafkaapi.services.KafkaProducer;

@Slf4j
@RestController
@RequestMapping("/msg")
public class MessageController {

    public final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public String createMessage(@RequestBody() String message) {
        log.info("Message publish request: " + message);
        return kafkaProducer.publish(message);
    }
}
