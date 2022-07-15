package com.ccsw.bidoffice.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(path = "/findFilter", method = RequestMethod.POST)
    public List<PersonDto> findFirst15Filter(@RequestBody PersonSearchDto personSearchDto) {

        return this.beanMapper.mapList(this.personService.findFirst15Filter(personSearchDto), PersonDto.class);
    }
}
