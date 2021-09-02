package com.ndebugs.simjam.api.controllers;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.entities.Transaction;
import com.ndebugs.simjam.api.models.TransactionModel;
import com.ndebugs.simjam.api.services.MemberService;
import com.ndebugs.simjam.api.services.TransactionService;
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
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public TransactionModel add(@RequestBody @Valid TransactionModel model) {
        Transaction entity = modelMapper.map(model, Transaction.class);
        
        Member member = memberService.findById(model.getMemberId());
        entity.setMember(member);
        
        Transaction result = service.save(entity);
        return modelMapper.map(result, TransactionModel.class);
    }

    @GetMapping
    public List<TransactionModel> findAll() {
        List<Transaction> result = service.findAll();
        
        return result.stream()
                .map(entity -> modelMapper.map(entity, TransactionModel.class))
                .collect(Collectors.toList());
    }
}
