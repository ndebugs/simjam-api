package com.ndebugs.simjam.api.services;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.repositories.MemberRepository;
import com.ndebugs.simjam.api.services.impl.MemberServiceImpl;
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
public class MemberServiceTests {
    
    @Mock
    MemberRepository repository;
    
    @InjectMocks
    MemberServiceImpl service;
    
    @Test
    void whenSave_shouldReturnEntity() {
        Member mockEntity = new Member();
        mockEntity.setId(1);
        mockEntity.setName("Member 1");
        
        when(repository.save(any(Member.class))).thenReturn(mockEntity);
        
        Member entity = service.save(mockEntity);
        
        assertEquals(mockEntity, entity);
    }
    
    @Test
    void whenRemoveById_shouldReturnEntity() {
        Member mockEntity = new Member();
        mockEntity.setId(1);
        mockEntity.setName("Member 1");
        
        when(repository.findById(anyInt())).thenReturn(Optional.of(mockEntity));
        
        Member entity = service.removeById(mockEntity.getId());
        
        verify(repository, times(1)).delete(any(Member.class));
        assertEquals(mockEntity, entity);
    }
    
    @Test
    void whenFindById_shouldReturnEntity() {
        Member mockEntity = new Member();
        mockEntity.setId(1);
        mockEntity.setName("Member 1");
        
        when(repository.findById(anyInt())).thenReturn(Optional.of(mockEntity));
        
        Member entity = service.findById(mockEntity.getId());
        
        assertEquals(mockEntity, entity);
    }
    
    @Test
    void whenFindAll_shouldReturnEmptyList() {
        when(repository.findAll()).thenReturn(List.of());
        
        List<Member> entities = service.findAll();
        assertTrue(entities.isEmpty());
    }
}
