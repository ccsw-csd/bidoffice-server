package com.ccsw.bidoffice.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.criteria.SearchCriteria;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<PersonEntity> findFirst15Filter(PersonSearchDto personSearchDto) {

        PersonSpecification username = new PersonSpecification(
                new SearchCriteria("username", ":", personSearchDto.getUsername()));

        PersonSpecification name = new PersonSpecification(new SearchCriteria("name", ":", personSearchDto.getName()));

        PersonSpecification lastname = new PersonSpecification(
                new SearchCriteria("lastname", ":", personSearchDto.getLastname()));

        PersonSpecification active = new PersonSpecification(new SearchCriteria("active", ":", true));

        Specification<PersonEntity> specification = Specification.where(username).and(name).and(lastname).and(active);

        return this.personRepository.findAll(specification, PageRequest.of(0, 15)).getContent();
    }

}
