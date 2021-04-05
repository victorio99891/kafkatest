package pl.wiktor.kafkaapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wiktor.kafkaapi.services.KafkaProducer;

@RestController
@RequestMapping("/msg")
public class MessageController {

    public final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public String createMessage(@RequestBody() String message) {
        return kafkaProducer.publish(message);
    }
}
