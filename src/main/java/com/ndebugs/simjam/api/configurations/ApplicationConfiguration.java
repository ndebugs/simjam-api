package com.ndebugs.simjam.api.configurations;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.models.MemberModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(MemberModel.class, Member.class).addMappings(mapper -> {
            mapper.skip(Member::setId);
        });
        
        return modelMapper;
    }
}
