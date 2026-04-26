package io.github.marrafon91.send_book_email_sb.writer;

import io.github.marrafon91.send_book_email_sb.domain.UserBookLoan;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendEmailRequestReturnWriterConfig {

    @Bean
    public ItemWriter<UserBookLoan>  sendEmailRequestReturnWriter() {
        return items -> items.forEach(System.out::println);
    }
}
