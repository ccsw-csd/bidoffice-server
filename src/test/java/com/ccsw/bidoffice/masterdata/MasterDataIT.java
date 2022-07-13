package com.ccsw.bidoffice.masterdata;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.test.annotation.DirtiesContext;

import com.ccsw.bidoffice.config.BaseITAbstract;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MasterDataIT extends BaseITAbstract {

    public static final String SERVICE_PATH = "/masterdata/";

    public static final Integer TOTAL_DATA_PRIORITY = 4;

    public static final Integer TOTAL_PERSON_ACTIVE = 2;

    public static final Integer TOTAL_DISTINCT_CLIENTS = 4;

    public static final Integer EMPTY_DATA = 0;

    ParameterizedTypeReference<Page<PersonDto>> responseTypePagePerson = new ParameterizedTypeReference<Page<PersonDto>>() {
    };

    private HttpEntity<?> httpEntity;

    private PersonSearchDto personSearchDto;

    @BeforeEach
    public void setUp() {
        personSearchDto = new PersonSearchDto();
        httpEntity = new HttpEntity<>(getHeaders());
    }

}
