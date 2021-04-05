package pl.wiktor.kafkaapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(id = "0", topics = "#{'${spring.kafka.test-topic}'}")
    public void listenForTestTopicMessages(@Payload String message) {
        log.info("START: Consumed message: " + message);
    }

    // FROM BEGIN
    @KafkaListener(id = "1", topics = "#{'${spring.kafka.test-topic}'}",
            topicPartitions = @TopicPartition(topic = "#{'${spring.kafka.test-topic}'}", partitionOffsets = {
                    @PartitionOffset(partition = "0", initialOffset = "0")
            })
    )
    public void listenForTestTopicMessagesFromBegin(@Payload String message) {
        log.info("BEGIN: Consumed message: " + message);
    }
}
