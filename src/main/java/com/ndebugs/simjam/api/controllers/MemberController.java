package com.ndebugs.simjam.api.controllers;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.models.MemberModel;
import com.ndebugs.simjam.api.models.ResponseMessage;
import com.ndebugs.simjam.api.services.MemberService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    
    @Autowired
    private MemberService service;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    public ResponseMessage<Member> add(@RequestBody MemberModel model) {
        Member entity = modelMapper.map(model, Member.class);
        
        Member result = service.save(entity);
        return ResponseMessage.success(result);
    }
    
    @GetMapping
    public ResponseMessage<List<Member>> findAll() {
        return ResponseMessage.success(service.findAll());
    }
}
