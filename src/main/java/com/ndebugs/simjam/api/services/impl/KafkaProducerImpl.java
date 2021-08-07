package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.configurations.KafkaTopicConfiguration;
import com.ndebugs.simjam.api.models.TransactionModel;
import com.ndebugs.simjam.api.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerImpl implements KafkaProducer {

    @Autowired
    private KafkaTemplate<String, TransactionModel> kafkaTemplate;
    
    @Override
    public void send(TransactionModel message) {
        kafkaTemplate.send(KafkaTopicConfiguration.TRANSACTION_TOPIC, message);
    }
}
