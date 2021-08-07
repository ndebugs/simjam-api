package com.ndebugs.simjam.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ndebugs.simjam.api.entities.Transaction;
import com.ndebugs.simjam.api.entities.TransactionType;
import com.ndebugs.simjam.api.models.TransactionModel;
import com.ndebugs.simjam.api.services.KafkaProducer;
import com.ndebugs.simjam.api.services.MemberService;
import com.ndebugs.simjam.api.services.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ModelMapper modelMapper;
    
    @MockBean
    TransactionService service;
    
    @MockBean
    MemberService memberService;
    
    @MockBean
    private KafkaProducer kafkaProducer;
    
    @Test
    void whenAdd_shouldReturnEntity() throws Exception {
        TransactionModel model = new TransactionModel();
        model.setMemberId(1);
        model.setType(TransactionType.DEPOSIT);
        model.setAmount(BigDecimal.ONE);
        model.setTime(LocalDateTime.now());
        
        ModelMapper mMapper = new ModelMapper();
        Transaction entity = mMapper.map(model, Transaction.class);
        
        when(modelMapper.map(any(TransactionModel.class), any(Class.class))).thenReturn(entity);
        when(service.save(any(Transaction.class))).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(model);
        
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.registerModule(new JavaTimeModule());
        oMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(oMapper.writeValueAsString(model)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(jsonPath("$.data.memberId", is(model.getMemberId())));
    }
    
    @Test
    void whenFindAll_shouldReturnEmptyList() throws Exception {
        when(service.findAll()).thenReturn(List.of());
        
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(jsonPath("$.data", empty()));
    }
}
