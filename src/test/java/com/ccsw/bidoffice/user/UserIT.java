package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.user.model.UserDto;
import com.ccsw.bidoffice.user.model.UserSearchDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";

    private static final String EXIST_USERNAME = "USERNAME2";
    private static final String EXIST_FIRSTNAME = "NAME";
    private static final String EXIST_LASTNAME = "LASTNAME";
    private static final String EXIST_FIRSTNAME_LASTNAME = "LASTNAME6";

    private UserSearchDto userSearchDto;

    ParameterizedTypeReference<Page<UserDto>> responseTypePage = new ParameterizedTypeReference<Page<UserDto>>() {
    };

    @BeforeEach
    public void setUp() {

        userSearchDto = new UserSearchDto();
    }

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

        userSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(5, response.getBody().getContent().size());

    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        userSearchDto.setPageable(PageRequest.of(1, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());

    }

    @Test
    void findFirstPageOfSizeTenWithExistingUsernameShouldReturnOneUser(){
        userSearchDto.setPageable(PageRequest.of(0, 10));
        userSearchDto.setUsername(EXIST_USERNAME);


        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void findFirstPageOfSizeTenWithExistingFirstnameShouldReturnSixUsers(){
        userSearchDto.setPageable(PageRequest.of(0, 10));
        userSearchDto.setName(EXIST_FIRSTNAME);

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(6, response.getBody().getContent().size());
    }

    @Test
    void findExistingUsernameAndFirstnameShouldReturnOneUser(){
        userSearchDto.setPageable(PageRequest.of(0, 5));
        userSearchDto.setUsername(EXIST_USERNAME);
        userSearchDto.setName(EXIST_FIRSTNAME);

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void findExistingUsernameAndLastnameShouldReturnCeroUsers(){
        userSearchDto.setPageable(PageRequest.of(0, 5));
        userSearchDto.setUsername(EXIST_USERNAME);
        userSearchDto.setName(EXIST_LASTNAME);

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    void findExistingNamePlusLastnameShouldReturnOneUser(){
        userSearchDto.setPageable(PageRequest.of(0, 5));
        userSearchDto.setName(EXIST_FIRSTNAME_LASTNAME);

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());
    }

}
