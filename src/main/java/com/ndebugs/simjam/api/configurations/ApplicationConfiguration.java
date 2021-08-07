package com.ndebugs.simjam.api.configurations;

import com.ndebugs.simjam.api.entities.Member;
import com.ndebugs.simjam.api.entities.Transaction;
import com.ndebugs.simjam.api.models.MemberModel;
import com.ndebugs.simjam.api.models.TransactionModel;
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
        modelMapper.typeMap(TransactionModel.class, Transaction.class).addMappings(mapper -> {
            mapper.skip(Transaction::setId);
        });

        return modelMapper;
    }
}
