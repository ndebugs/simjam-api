package com.ndebugs.simjam.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndebugs.simjam.api.configurations.ApplicationConfiguration;
import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.models.MemberModel;
import com.ndebugs.simjam.api.services.MemberService;
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
import org.springframework.test.context.ContextConfiguration;
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
        
        ModelMapper mMapper = new ModelMapper();
        Member entity = mMapper.map(model, Member.class);
        
        when(service.save(any(Member.class))).thenReturn(entity);
        
        ObjectMapper oMapper = new ObjectMapper();
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
