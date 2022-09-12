package com.ccsw.bidoffice.person;

import java.util.List;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.person.model.PersonEntity;

public interface PersonService {

    List<PersonEntity> findFirst15Filter(String filter);

    PersonEntity findPersonByUsername(String username) throws EntityNotFoundException;
}
