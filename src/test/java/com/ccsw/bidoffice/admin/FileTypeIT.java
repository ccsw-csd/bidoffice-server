package com.ccsw.bidoffice.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.filetype.model.FileTypeDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FileTypeIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/filetype/";

    private FileTypeDto fileTypeDto;

    ParameterizedTypeReference<List<FileTypeDto>> responseType = new ParameterizedTypeReference<List<FileTypeDto>>() {
    };

    @BeforeEach
    public void setUp() {
        fileTypeDto = new FileTypeDto();

        this.fileTypeDto.setId(7L);
        this.fileTypeDto.setName("Holaquetal");
        this.fileTypeDto.setPriority(7L);
    }

    @Test
    public void findAllFileTypesShouldReturnAll() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<List<FileTypeDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }

    public static final Long NEW_FILETYPE_ID = 10L;
    public static final Long DELETE_FILETYPE_ID = 2L;
    public static final Long EXISTING_FILETYPE_ID = 1L;

    @Test
    public void deleteWithExistsIdShouldDeleteFileType() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + DELETE_FILETYPE_ID, HttpMethod.DELETE, httpEntity,
                Void.class);

        ResponseEntity<List<FileTypeDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "findAll",
                HttpMethod.GET, httpEntity, responseType);

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void deleteWithNotExistsIdShouldInternalError() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NEW_FILETYPE_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void checkExistsIdShouldReturnTrue() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ParameterizedTypeReference<Boolean> responseType = new ParameterizedTypeReference<Boolean>() {
        };

        ResponseEntity<Boolean> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "checkOffers/" + EXISTING_FILETYPE_ID, HttpMethod.GET, httpEntity,
                responseType);

        assertNotNull(response);
        assertEquals(true, response.getBody());
    }

    @Test
    public void checkNotExistIdShouldReturnFalse() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ParameterizedTypeReference<Boolean> responseType = new ParameterizedTypeReference<Boolean>() {
        };

        ResponseEntity<Boolean> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "checkOffers/" + NEW_FILETYPE_ID, HttpMethod.GET, httpEntity,
                responseType);

        assertNotNull(response);
        assertEquals(false, response.getBody());
    }

}
