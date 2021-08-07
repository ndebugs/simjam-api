package com.ndebugs.simjam.api.services;

import com.ndebugs.simjam.messaging.TransactionMessage;

public interface KafkaProducer {
    
    void send(TransactionMessage message);
}
