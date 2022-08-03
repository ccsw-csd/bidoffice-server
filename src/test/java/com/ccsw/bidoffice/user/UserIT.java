package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.role.model.RoleDto;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/user/";

    public static final Integer TOTAL_USER = 1;
    public static final Long NOT_EXISTS_ID_USER = 0L;
    public static final Long EXISTS_ID_USER = 2L;

    private static final String EXIST_USERNAME = "USERNAME2";
    private static final String EXIST_FIRSTNAME = "NAME";
    private static final String EXIST_LASTNAME = "LASTNAME";
    private static final String EXIST_FIRSTNAME_LASTNAME = "LASTNAME6";

    private UserSearchDto userSearchDto;

    ParameterizedTypeReference<List<UserDto>> responseTypeList = new ParameterizedTypeReference<List<UserDto>>() {
    };

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

    @Test
    public void modifyWithNotExistIdShouldThrowException() {

        UserDto dto = new UserDto();
        dto.setId(NOT_EXISTS_ID_USER);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                UserDto.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void modifyWithExistIdShouldModifyUser() {
        RoleDto role = new RoleDto();
        role.setId(2L);

        UserDto dto = new UserDto();
        dto.setId(EXISTS_ID_USER);
        dto.setUsername(EXIST_USERNAME);
        dto.setFirstName("");
        dto.setLastName("");
        dto.setEmail("");
        dto.setRole(role);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<UserDto> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT,
                httpEntity, UserDto.class);
        assertNotNull(response.getBody());

        userSearchDto.setPageable(PageRequest.of(0, 5));
        userSearchDto.setUsername(EXIST_USERNAME);
        HttpEntity<?> httpEntity2 = new HttpEntity<>(userSearchDto, getHeaders());

        ResponseEntity<Page<UserDto>> responsePage = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findPage",
                HttpMethod.POST, httpEntity2, responseTypePage);

        assertEquals(TOTAL_USER, responsePage.getBody().getContent().size());

        UserDto userDto = responsePage.getBody().getContent().stream().filter(item -> item.getId().equals(EXISTS_ID_USER))
                .findFirst().orElse(null);

        assertNotNull(userDto);
        assertEquals(response.getBody().getUsername(), userDto.getUsername());
        assertEquals(response.getBody().getFirstName(), userDto.getFirstName());
        assertEquals(response.getBody().getLastName(), userDto.getLastName());
        assertEquals(response.getBody().getEmail(), userDto.getEmail());
    }

}
