package com.ccsw.bidoffice.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.admin.model.FileTypeDto;
import com.ccsw.bidoffice.config.BaseITAbstract;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FileTypeIT extends BaseITAbstract {

    public static final String LOCALHOST = "http://localhost:";
    public static final String SERVICE_PATH = "/admin/";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

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

        ResponseEntity<List<FileTypeDto>> response = restTemplate.exchange(LOCALHOST + port + SERVICE_PATH + "filetype",
                HttpMethod.GET, httpEntity, responseType);

        assertNotNull(response);
        assertEquals(3, response.getBody().size());
    }

}
