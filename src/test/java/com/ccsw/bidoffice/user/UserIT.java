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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";

    private static final String USERNAME = "username";
    private static final String FULLNAME = "name";

    private static final String EXIST_USERNAME = "USERNAME2";
    private static final String EXIST_FIRSTNAME = "NAME";
    private static final String EXIST_LASTNAME = "LASTNAME";
    private static final String EXIST_FIRSTNAME_LASTNAME = "NAME6 LASTNA";

    private UserSearchDto userSearchDto;

    ParameterizedTypeReference<Page<UserDto>> responseTypePage = new ParameterizedTypeReference<Page<UserDto>>() {
    };

    @BeforeEach
    public void setUp() {

        userSearchDto = new UserSearchDto();
    }

    private String getUrlWithParams(){
        return UriComponentsBuilder.fromHttpUrl(LOCALHOST + port + SERVICE_PATH + "get")
                .queryParam(USERNAME, "{" + USERNAME +"}")
                .queryParam(FULLNAME, "{" + FULLNAME +"}")
                .encode()
                .toUriString();
    }

    @Test
    public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

        userSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "get",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(5, response.getBody().getContent().size());

    }

    @Test
    public void findSecondPageWithFiveSizeShouldReturnLastResult() {

        int LAST_USER=1;

        userSearchDto.setPageable(PageRequest.of(1, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "get",
                HttpMethod.POST, httpEntity, responseTypePage);

        assertNotNull(response);
        assertEquals(LAST_USER, response.getBody().getContent().size());

    }

    @Test
    public void findFirstPageOfSizeTenWithExistingUsernameShouldReturnOneUser(){
        Map<String, Object> params = new HashMap<>();
        params.put(USERNAME, EXIST_USERNAME);
        params.put(FULLNAME, null);

        userSearchDto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, httpEntity, responseTypePage,params);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());

    }

    @Test
    public void findFirstPageOfSizeTenWithExistingFirstNameShouldReturnSixUsers(){
        Map<String, Object> params = new HashMap<>();
        params.put(USERNAME, null);
        params.put(FULLNAME, EXIST_FIRSTNAME);

        userSearchDto.setPageable(PageRequest.of(0, 10));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, httpEntity, responseTypePage,params);

        assertNotNull(response);
        assertEquals(6, response.getBody().getContent().size());

    }

    @Test
    public void findExistingUsernameAndFirstNameShouldReturnOneUser(){
        Map<String, Object> params = new HashMap<>();
        params.put(USERNAME, EXIST_USERNAME);
        params.put(FULLNAME, EXIST_FIRSTNAME);

        userSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, httpEntity, responseTypePage,params);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());

    }

    @Test
    public void findExistingUsernameAndLastnameShouldReturnOneUser(){
        Map<String, Object> params = new HashMap<>();
        params.put(USERNAME, EXIST_USERNAME);
        params.put(FULLNAME, EXIST_LASTNAME);

        userSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, httpEntity, responseTypePage,params);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());

    }

    @Test
    public void findExistingFirstNamePlusLastnameShouldReturnOneUser(){
        Map<String, Object> params = new HashMap<>();
        params.put(USERNAME, null);
        params.put(FULLNAME, EXIST_FIRSTNAME_LASTNAME);

        userSearchDto.setPageable(PageRequest.of(0, 5));

        HttpEntity<?> httpEntity = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> response = restTemplate.exchange(getUrlWithParams(), HttpMethod.POST, httpEntity, responseTypePage,params);

        assertNotNull(response);
        assertEquals(1, response.getBody().getContent().size());

    }

}
