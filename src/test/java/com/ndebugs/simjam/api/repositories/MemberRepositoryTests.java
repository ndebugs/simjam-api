package com.ndebugs.simjam.api.repositories;

import com.ndebugs.simjam.api.entities.Member;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTests {
    
    @Autowired
    MemberRepository repository;
    
    @Test
    void whenFindAll_shouldReturnNonEmptyList() {
        List<Member> entities = repository.findAll();
        assertFalse(entities.isEmpty());
    }
}
