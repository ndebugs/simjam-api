package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.entities.Transaction;
import com.ndebugs.simjam.api.repositories.TransactionRepository;
import com.ndebugs.simjam.api.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl extends CRUDServiceImpl<Transaction, Integer> implements TransactionService {

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }
}
