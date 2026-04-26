package io.github.marrafon91.send_book_email_sb.step;

import com.sendgrid.helpers.mail.Mail;
import io.github.marrafon91.send_book_email_sb.domain.UserBookLoan;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SendEmailUserStepConfig {

    @Autowired
    @Qualifier("transactionManagerApp")
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step sendEmailUserStep(ItemReader<UserBookLoan> readUserWhithLoansCloseToReturnReader,
                                  ItemProcessor<UserBookLoan, Mail> processLoanNotificationEmailProcessor,
                                  ItemWriter<UserBookLoan> sendEmailRequestReturnWriter,
                                  JobRepository jobRepository) {

        return new StepBuilder("sendEmailUserStep", jobRepository)
                .<UserBookLoan, Mail>chunk(1, transactionManager)
                .reader(readUserWhithLoansCloseToReturnReader)
                .processor(processLoanNotificationEmailProcessor)
                .writer(sendEmailRequestReturnWriter)


                .build();
    }
}
