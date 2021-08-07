package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.entities.Transaction;
import com.ndebugs.simjam.api.repositories.TransactionRepository;
import com.ndebugs.simjam.api.services.KafkaProducer;
import com.ndebugs.simjam.api.services.TransactionService;
import com.ndebugs.simjam.messaging.TransactionMessage;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends CRUDServiceImpl<Transaction, Integer> implements TransactionService {

    @Autowired
    private TransactionRepository repository;
    
    @Autowired
    private KafkaProducer kafkaProducer;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    protected JpaRepository<Transaction, Integer> getRepository() {
        return repository;
    }
    
    @Transactional
    @Override
    public Transaction save(Transaction entity) {
        Transaction result = super.save(entity);
        
        TransactionMessage message = modelMapper.map(result, TransactionMessage.class);
        kafkaProducer.send(message);
        
        return result;
    }
}
