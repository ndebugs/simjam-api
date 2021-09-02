package com.ndebugs.simjam.api.controllers;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.models.MemberModel;
import com.ndebugs.simjam.api.services.MemberService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
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
    public MemberModel add(@RequestBody @Valid MemberModel model) {
        Member entity = modelMapper.map(model, Member.class);
        
        Member result = service.save(entity);
        return modelMapper.map(result, MemberModel.class);
    }
    
    @GetMapping
    public List<MemberModel> findAll() {
        List<Member> result = service.findAll();
        
        return result.stream()
                .map(entity -> modelMapper.map(entity, MemberModel.class))
                .collect(Collectors.toList());
    }
}
