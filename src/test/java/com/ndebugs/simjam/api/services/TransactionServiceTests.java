package com.ndebugs.simjam.api.services;

import com.ndebugs.simjam.api.entities.Transaction;
import com.ndebugs.simjam.api.repositories.TransactionRepository;
import com.ndebugs.simjam.api.services.impl.TransactionServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {
    
    @Mock
    TransactionRepository repository;
    
    @InjectMocks
    TransactionServiceImpl service;
    
    @Test
    void whenSave_shouldReturnEntity() {
        Transaction mockEntity = new Transaction();
        mockEntity.setId(1);
        mockEntity.setAmount(BigDecimal.ONE);
        
        when(repository.save(any(Transaction.class))).thenReturn(mockEntity);
        
        Transaction entity = service.save(mockEntity);
        
        assertEquals(mockEntity, entity);
    }
    
    @Test
    void whenRemoveById_shouldReturnEntity() {
        Transaction mockEntity = new Transaction();
        mockEntity.setId(1);
        mockEntity.setAmount(BigDecimal.ONE);
        
        when(repository.findById(anyInt())).thenReturn(Optional.of(mockEntity));
        
        Transaction entity = service.removeById(mockEntity.getId());
        
        verify(repository, times(1)).delete(any(Transaction.class));
        assertEquals(mockEntity, entity);
    }
    
    @Test
    void whenFindById_shouldReturnEntity() {
        Transaction mockEntity = new Transaction();
        mockEntity.setId(1);
        mockEntity.setAmount(BigDecimal.ONE);
        
        when(repository.findById(anyInt())).thenReturn(Optional.of(mockEntity));
        
        Transaction entity = service.findById(mockEntity.getId());
        
        assertEquals(mockEntity, entity);
    }
    
    @Test
    void whenFindAll_shouldReturnEmptyList() {
        when(repository.findAll()).thenReturn(List.of());
        
        List<Transaction> entities = service.findAll();
        assertTrue(entities.isEmpty());
    }
}
