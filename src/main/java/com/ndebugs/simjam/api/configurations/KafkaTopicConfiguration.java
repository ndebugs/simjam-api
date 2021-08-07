package com.ndebugs.simjam.api.configurations;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {
    
    public static final String TRANSACTION_TOPIC = "transaction";
    
    @Value(value = "${kafka.host}")
    private String host;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topic() {
         return new NewTopic(TRANSACTION_TOPIC, 1, (short) 1);
    }
}
