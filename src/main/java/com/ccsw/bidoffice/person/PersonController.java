package com.ccsw.bidoffice.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

@RequestMapping(value = "/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    public Page<PersonDto> persons(@RequestBody PersonSearchDto personSearchDto) {

        return this.beanMapper.mapPage(this.personService.persons(personSearchDto), PersonDto.class);
    }
}
