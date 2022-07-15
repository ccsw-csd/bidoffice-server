package com.ccsw.bidoffice.person;

import java.util.List;

import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

public interface PersonService {

    List<PersonEntity> findFirst15Filter(PersonSearchDto dto);
}
