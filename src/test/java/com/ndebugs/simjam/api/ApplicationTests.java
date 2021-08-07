package com.ndebugs.simjam.api;

import com.ndebugs.simjam.api.services.KafkaProducer;
import com.ndebugs.simjam.messaging.TransactionMessage;
import com.ndebugs.simjam.messaging.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ApplicationTests {

    @Autowired
    private KafkaProducer producer;

    @Test
    void whenProduce_shouldSuccess() {
        TransactionMessage message = new TransactionMessage();
        message.setMemberId(1);
        message.setType(TransactionType.DEPOSIT);
        message.setAmount(BigDecimal.ONE);
        message.setTimestamp(LocalDateTime.now());
        
        producer.send(message);
    }

}
