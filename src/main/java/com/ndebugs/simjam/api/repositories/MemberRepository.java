package com.ndebugs.simjam.api.repositories;

import com.ndebugs.simjam.api.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{
    
}
