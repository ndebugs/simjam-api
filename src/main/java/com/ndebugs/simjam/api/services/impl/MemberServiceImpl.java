package com.ndebugs.simjam.api.services.impl;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.repositories.MemberRepository;
import com.ndebugs.simjam.api.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends CRUDServiceImpl<Member, Integer> implements MemberService {

    @Autowired
    private MemberRepository repository;
    
    @Override
    protected JpaRepository<Member, Integer> getRepository() {
        return repository;
    }
}
