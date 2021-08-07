package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.configurations.KafkaTopicConfiguration;
import com.ndebugs.simjam.api.services.KafkaProducer;
import com.ndebugs.simjam.messaging.TransactionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, TransactionMessage> kafkaTemplate;
    
    @Override
    public void send(TransactionMessage message) {
        kafkaTemplate.send(KafkaTopicConfiguration.TRANSACTION_TOPIC, message);
    }
}
