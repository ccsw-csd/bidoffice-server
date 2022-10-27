package com.ccsw.bidoffice.formatdocument;

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
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FormatDocumentIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/formatdocument/";

    public static final Integer TOTAL_DATA = 2;

    public static final Long ID_EXIST = 1L;

    public static final Long NOT_USED_ID = 2L;

    public static final String NAME_EXIST = "docx";

    public static final String NEW_NAME = "txt";

    public static final Integer NEW_PRIORITY = 3;

    private FormatDocumentDto formatDocumentDto;

    ParameterizedTypeReference<List<FormatDocumentDto>> responseTypeFormatDocument = new ParameterizedTypeReference<List<FormatDocumentDto>>() {
    };

    @BeforeEach
    public void setUp() {
        this.formatDocumentDto = new FormatDocumentDto();
    }

    @Test
    public void getAllDataShouldReturnFormatDocumentList() {

        HttpEntity<?> httpEntity = new HttpEntity<>(this.formatDocumentDto, getHeaders());

        ResponseEntity<List<FormatDocumentDto>> response = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeFormatDocument);

        assertNotNull(response);
        assertEquals(TOTAL_DATA, response.getBody().size());

    }

    @Test
    public void deleteFormatDocumentWhenUsedShouldThrowException() {

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + ID_EXIST,
                HttpMethod.DELETE, httpEntity, Void.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void deleteFormatDocumentWhenNotUsedShouldDelete() {
        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + NOT_USED_ID,
                HttpMethod.DELETE, httpEntity, Void.class);

        ResponseEntity<List<FormatDocumentDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeFormatDocument);

        assertNotNull(response);
        assertNotNull(responseAfter);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(HttpStatus.OK, responseAfter.getStatusCode());
        assertEquals(TOTAL_DATA - 1, responseAfter.getBody().size());
    }

    @Test
    public void editWhenAttributesNotUsedInOtherFormatDocumentShouldEditedFormatDocument() {

        formatDocumentDto.setId(ID_EXIST);
        formatDocumentDto.setName(NEW_NAME);
        formatDocumentDto.setPriority(NEW_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        ResponseEntity<List<FormatDocumentDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeFormatDocument);

        FormatDocumentDto editedFormatDocumentDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals("txt")).findFirst().orElse(null);

        assertNotNull(responseAfter);
        assertEquals(TOTAL_DATA, responseAfter.getBody().size());
        assertEquals(NEW_NAME, editedFormatDocumentDto.getName());
        assertEquals(NEW_PRIORITY, editedFormatDocumentDto.getPriority());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void editWhenNameUsedInOtherFormatDocumentShouldThrowException() {

        formatDocumentDto.setId(ID_EXIST);
        formatDocumentDto.setName(NAME_EXIST);
        formatDocumentDto.setPriority(NEW_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void editWhenNameNotUsedInOtherFormatDocumentShouldEditedFormatDocument() {

        formatDocumentDto.setId(1L);
        formatDocumentDto.setName(NEW_NAME);
        formatDocumentDto.setPriority(1);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<FormatDocumentDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeFormatDocument);

        FormatDocumentDto editedFormatDocument = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals(NEW_NAME)).findFirst().orElse(null);

        assertNotNull(editedFormatDocument);
        assertEquals(TOTAL_DATA, responseAfter.getBody().size());
        assertEquals(NEW_NAME, editedFormatDocument.getName());
        assertEquals(1, editedFormatDocument.getPriority());
    }

    @Test
    public void editWhenPriorityUsedInOtherFormatDocumentShouldThrowException() {

        formatDocumentDto.setId(1L);
        formatDocumentDto.setName(NEW_NAME);
        formatDocumentDto.setPriority(2);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void editWhenPriorityNotUsedInOtherFormatDocumentShouldEditedFormatDocument() {

        formatDocumentDto.setId(NOT_USED_ID);
        formatDocumentDto.setName(NAME_EXIST);
        formatDocumentDto.setPriority(NEW_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<FormatDocumentDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeFormatDocument);

        FormatDocumentDto editedFormatDocumentDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals(NAME_EXIST)).findFirst().orElse(null);

        assertEquals(TOTAL_DATA, responseAfter.getBody().size());
        assertEquals(NAME_EXIST, editedFormatDocumentDto.getName());
        assertEquals(NEW_PRIORITY, editedFormatDocumentDto.getPriority());

    }

    @Test
    public void saveNewFormatDocumentWithExistingNameShouldCreate() {

        formatDocumentDto.setName(NEW_NAME);
        formatDocumentDto.setPriority(NEW_PRIORITY);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<List<FormatDocumentDto>> responseAfter = restTemplate.exchange(
                LOCALHOST + port + SERVICE_PATH + "findAll", HttpMethod.GET, httpEntity, responseTypeFormatDocument);

        FormatDocumentDto newFormatDocumentDto = responseAfter.getBody().stream()
                .filter(element -> element.getName().equals(NEW_NAME)).findFirst().orElse(null);

        assertEquals(TOTAL_DATA + 1, responseAfter.getBody().size());
        assertEquals(NEW_NAME, newFormatDocumentDto.getName());
        assertEquals(NEW_PRIORITY, newFormatDocumentDto.getPriority());
    }

    @Test
    public void saveNewFormatDocumentWithExistingNameShouldThrowException() {

        formatDocumentDto.setName(NAME_EXIST);
        formatDocumentDto.setPriority(3);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    public void saveNewFormatDocumentWithExistingPriorityShouldThrowException() {

        formatDocumentDto.setName(NEW_NAME);
        formatDocumentDto.setPriority(1);
        HttpEntity<?> httpEntity = new HttpEntity<>(formatDocumentDto, getHeaders());

        ResponseEntity<?> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH, HttpMethod.PUT, httpEntity,
                Void.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

}
