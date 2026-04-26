package io.github.marrafon91.send_book_email_sb.reader;

import io.github.marrafon91.send_book_email_sb.domain.UserBookLoan;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ReadUserWhithLoansCloseToReturnReaderConfig {

    int numDastoNotifyReturn = 6;

    @Bean
    public ItemReader<UserBookLoan> readUserWhithLoansCloseToReturnReader(
            @Qualifier("appDS") DataSource dataSource
    ) {
        return new JdbcCursorItemReaderBuilder<UserBookLoan>()
                .name("readUserWhithLoansCloseToReturnReader")
                .dataSource(dataSource)
                .sql("""
                        SELECT
                            user.id as user_id,
                            user.name as user_name,
                            user.email as user_email,
                            book.id as book_id,
                            book.name as book_name,
                            loan.loan_date
                        FROM tb_user_book_loan as loan
                        INNER JOIN tb_user as user ON loan.user_id = user.id
                        INNER JOIN tb_book as book ON loan.book_id = book.id
                        WHERE DATE_ADD(loan.loan_date, INTERVAL %d DAY) = DATE(NOW())
                        """.formatted(numDastoNotifyReturn))
                .rowMapper(rowMapper())
                .build();
    }
}
