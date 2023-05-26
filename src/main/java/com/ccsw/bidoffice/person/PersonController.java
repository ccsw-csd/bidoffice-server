package com.ccsw.bidoffice.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.person.model.PersonDto;

@RequestMapping(value = "/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/{filter}", method = RequestMethod.GET)
    public List<PersonDto> findFirst15Filter(@PathVariable String filter) {

        return this.beanMapper.mapList(this.personService.findFirst15Filter(filter), PersonDto.class);
    }

    @RequestMapping(path = "/username/{username}", method = RequestMethod.GET)
    public PersonDto findByUsername(@PathVariable String username) {

        try {
            return this.beanMapper.map(this.personService.findPersonByUsername(username), PersonDto.class);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
}
