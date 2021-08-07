package com.ndebugs.simjam.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.models.MemberModel;
import com.ndebugs.simjam.api.services.MemberService;
import java.time.LocalDate;
import java.time.Month;
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

@WebMvcTest(MemberController.class)
public class MemberControllerTests {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ModelMapper modelMapper;
    
    @MockBean
    MemberService service;
    
    @Test
    void whenAdd_shouldReturnEntity() throws Exception {
        MemberModel model = new MemberModel();
        model.setName("Member 1");
        model.setBirthDate(LocalDate.of(1991, Month.JANUARY, 1));
        model.setAddress("At home");
        
        ModelMapper mMapper = new ModelMapper();
        Member entity = mMapper.map(model, Member.class);
        
        when(modelMapper.map(any(MemberModel.class), any(Class.class))).thenReturn(entity);
        when(service.save(any(Member.class))).thenReturn(entity);
        when(modelMapper.map(any(Member.class), any(Class.class))).thenReturn(model);
        
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.registerModule(new JavaTimeModule());
        oMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(oMapper.writeValueAsString(model)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(jsonPath("$.data.name", is(model.getName())));
    }
    
    @Test
    void whenFindAll_shouldReturnEmptyList() throws Exception {
        when(service.findAll()).thenReturn(List.of());
        
        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(0)))
                .andExpect(jsonPath("$.data", empty()));
    }
}
