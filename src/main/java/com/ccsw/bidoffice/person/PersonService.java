package com.ccsw.bidoffice.person;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

public interface PersonService {

    Page<PersonEntity> persons(PersonSearchDto dto);
}
