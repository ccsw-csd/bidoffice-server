package com.ccsw.bidoffice.person;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.person.model.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long>, JpaSpecificationExecutor<PersonEntity> {

    PersonEntity findByUsernameAndActive(String username, Boolean active);

    Boolean existsByUsernameAndActive(String username, Boolean active);
}
