package com.ndebugs.simjam.api.repositories;

import com.ndebugs.simjam.api.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    
}
