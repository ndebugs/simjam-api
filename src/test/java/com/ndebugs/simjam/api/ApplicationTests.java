package com.ndebugs.simjam.api;

import com.ndebugs.simjam.api.entities.TransactionType;
import com.ndebugs.simjam.api.models.TransactionModel;
import com.ndebugs.simjam.api.services.KafkaProducer;
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
        TransactionModel model = new TransactionModel();
        model.setMemberId(1);
        model.setType(TransactionType.DEPOSIT);
        model.setAmount(BigDecimal.ONE);
        model.setTime(LocalDateTime.now());
        
        producer.send(model);
    }

}
