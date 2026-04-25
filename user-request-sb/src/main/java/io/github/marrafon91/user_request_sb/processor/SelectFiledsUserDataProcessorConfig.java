package io.github.marrafon91.user_request_sb.processor;

import io.github.marrafon91.user_request_sb.dto.UserDTO;
import io.github.marrafon91.user_request_sb.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelectFiledsUserDataProcessorConfig {

    private static Logger logger = LoggerFactory.getLogger(SelectFiledsUserDataProcessorConfig.class);

    private int counter = 0;

    @Bean
    public ItemProcessor<UserDTO, User> selectFiledsUserDataProcessor() {
        return new ItemProcessor<UserDTO, User>() {

            @Override
            public User process(UserDTO item) throws Exception {
                User user = new User();
                user.setLogin(item.getLogin());
                user.setName(item.getName());
                user.setAvatarUrl(item.getAvatarUrl());
                counter++;
                logger.info("[PROCESSOR STEP] select user fields {} - {}", counter, user);
                return user;

            }
        };
    }
}
