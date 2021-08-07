package com.ndebugs.simjam.api.services;

import com.ndebugs.simjam.api.models.TransactionModel;

public interface KafkaProducer {
    
    void send(TransactionModel message);
}
