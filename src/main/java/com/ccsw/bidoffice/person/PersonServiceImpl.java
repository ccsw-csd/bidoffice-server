package com.ccsw.bidoffice.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.criteria.TernarySearchCriteria;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.person.model.PersonEntity;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<PersonEntity> findFirst15Filter(String filter) {

        PersonSpecification active = new PersonSpecification(
                new TernarySearchCriteria("active", null, null, ":", true));

        PersonSpecification usernameNameLastname = new PersonSpecification(
                new TernarySearchCriteria("username", "name", "lastname", "concat concat :", filter));

        Specification<PersonEntity> specification = Specification.where(active).and(usernameNameLastname);

        return this.personRepository.findAll(specification, PageRequest.of(0, 15)).getContent();
    }

    @Override
    public PersonEntity findPersonByUsername(String username) throws EntityNotFoundException {

        if (!this.personRepository.existsByUsernameAndActive(username, true))
            throw new EntityNotFoundException();

        return this.personRepository.findByUsernameAndActive(username, true);
    }

}
