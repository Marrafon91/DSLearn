package io.github.marrafon91.user_request_sb.reader;

import io.github.marrafon91.user_request_sb.domain.ResponseUser;
import io.github.marrafon91.user_request_sb.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FetchUserDataReaderConfig implements ItemReader<UserDTO> {

    public static Logger logger = LoggerFactory.getLogger(FetchUserDataReaderConfig.class);

    private final String BASE_URL = "http://localhost:8081";
    private RestTemplate restTemplate = new RestTemplate();

    private int page = 0;

    private List<UserDTO> users = new ArrayList<>();
    private int userIndex = 0;

    @Value("${pageSize}")
    private int pageSize = 10;

    @Value("${chunkSize}")
    private int chunkSize = 10;

    @Override
    public UserDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        UserDTO user;
        if (userIndex < users.size()) {
            user = users.get(userIndex);
        } else {
            user = null;
        }

        userIndex++;
        return user;
    }

    private List<UserDTO> fetchUserDataFromAPI() throws Exception {

        String uri = BASE_URL + "/clients/pagedData?page=%d&Size=%d";

        logger.info("[READER STEP] Fetching user data from API...");
        logger.info("[READER STEP] Requesting uri: {}", String.format(uri, getPage(), pageSize));

        ResponseEntity<ResponseUser> response = restTemplate.exchange(String.format(uri, getPage(), pageSize),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseUser>() {
                });

        assert response.getBody() != null;
        List<UserDTO> result = response.getBody().getContent();
        return result;
    }

    public int getPage() {
        return page;
    }

    public void incrementPage() {
        this.page++;
    }

    @BeforeChunk
    public  void beforeChunk(ChunkContext context) throws Exception {
        for (int i = 0; i < chunkSize; i += pageSize) {
            users.addAll(fetchUserDataFromAPI());
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) throws Exception {
        logger.info("[READER STEP] Finished processing chunk. Incrementing page and resetting user index...");
        incrementPage();
        userIndex = 0;
        users = new ArrayList<>();
    }

}
