package io.github.marrafon91.user_request_sb.processor;

import io.github.marrafon91.user_request_sb.dto.UserDTO;
import io.github.marrafon91.user_request_sb.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelectFiledsUserDataProcessorConfig {

    private static Logger logger = LoggerFactory.getLogger(SelectFiledsUserDataProcessorConfig.class);

    @Bean
    public ItemProcessor<UserDTO, User> selectFiledsUserDataProcessor() {
        
    }
}
