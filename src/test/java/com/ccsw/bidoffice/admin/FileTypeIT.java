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

    public static final Long NEW_FILETYPE_ID = 10L;
    public static final Long DELETE_FILETYPE_ID = 2L;
    public static final Long EXISTING_FILETYPE_ID = 3L;
    public static final String NEW_FILETYPE_NAME = "TEST NAME";
    public static final String EXISTING_FILETYPE_NAME = "user1";
    public static final String SAME_FILETYPE_NAME = "user3";
    public static final Long NEW_FILETYPE_PRIORITY = 7L;
    public static final Long EXISTING_FILETYPE_PRIORITY = 1L;
    public static final Long SAME_FILETYPE_PRIORITY = 3L;

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
    public void deleteWithNotExistsIdShouldThrowException() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + EXISTING_FILETYPE_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void saveWithEverythingOk() {
        FileTypeDto dto = new FileTypeDto();
        dto.setName(NEW_FILETYPE_NAME);
        dto.setPriority(NEW_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity, Void.class);

        ResponseEntity<List<FileTypeDto>> responseList = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseType);

        FileTypeDto fileTypeDto = responseList.getBody().stream()
                .filter(item -> item.getName().equals(NEW_FILETYPE_NAME)).findFirst().orElse(null);

        assertNotNull(fileTypeDto);
        assertEquals(NEW_FILETYPE_NAME, fileTypeDto.getName());
        assertEquals(NEW_FILETYPE_PRIORITY, fileTypeDto.getPriority());

    }

    @Test
    public void saveWithExistingNameShouldThrowException() {
        FileTypeDto dto = new FileTypeDto();
        dto.setName(EXISTING_FILETYPE_NAME);
        dto.setPriority(NEW_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void saveWithExistingPriorityShouldThrowException() {
        FileTypeDto dto = new FileTypeDto();
        dto.setName(NEW_FILETYPE_NAME);
        dto.setPriority(EXISTING_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void editWithEverythingOkShouldEdit() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(NEW_FILETYPE_NAME);
        dto.setPriority(NEW_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity, Void.class);

        ResponseEntity<List<FileTypeDto>> responseList = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseType);

        FileTypeDto fileTypeDto = responseList.getBody().stream()
                .filter(item -> item.getId().equals(EXISTING_FILETYPE_ID)).findFirst().orElse(null);

        assertNotNull(fileTypeDto);
        assertEquals(NEW_FILETYPE_NAME, fileTypeDto.getName());
        assertEquals(NEW_FILETYPE_PRIORITY, fileTypeDto.getPriority());
    }

    @Test
    public void editWithExistingNameShouldThrowException() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(EXISTING_FILETYPE_NAME);
        dto.setPriority(NEW_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void editWithExistingPriorityShouldThrowException() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(NEW_FILETYPE_NAME);
        dto.setPriority(EXISTING_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void editSameNamePriorityOkShouldEdit() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(SAME_FILETYPE_NAME);
        dto.setPriority(NEW_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity, Void.class);

        ResponseEntity<List<FileTypeDto>> responseList = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseType);

        FileTypeDto fileTypeDto = responseList.getBody().stream()
                .filter(item -> item.getId().equals(EXISTING_FILETYPE_ID)).findFirst().orElse(null);

        assertNotNull(fileTypeDto);
        assertEquals(SAME_FILETYPE_NAME, fileTypeDto.getName());
        assertEquals(NEW_FILETYPE_PRIORITY, fileTypeDto.getPriority());
    }

    @Test
    public void editSamePriorityNameOkShouldEdit() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(NEW_FILETYPE_NAME);
        dto.setPriority(SAME_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity, Void.class);

        ResponseEntity<List<FileTypeDto>> responseList = restTemplate
                .exchange(LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseType);

        FileTypeDto fileTypeDto = responseList.getBody().stream()
                .filter(item -> item.getName().equals(NEW_FILETYPE_NAME)).findFirst().orElse(null);

        assertNotNull(fileTypeDto);
        assertEquals(NEW_FILETYPE_NAME, fileTypeDto.getName());
        assertEquals(SAME_FILETYPE_PRIORITY, fileTypeDto.getPriority());
    }

    @Test
    public void editSameNamePriorityWrongShouldThrowException() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(SAME_FILETYPE_NAME);
        dto.setPriority(EXISTING_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void editSamePriorityNameWrongShouldThrowException() {
        FileTypeDto dto = new FileTypeDto();
        dto.setId(EXISTING_FILETYPE_ID);
        dto.setName(EXISTING_FILETYPE_NAME);
        dto.setPriority(SAME_FILETYPE_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(dto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + "/filetype", HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
