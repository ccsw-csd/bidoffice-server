package com.ccsw.bidoffice.masterdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.model.BaseClassDto;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.person.model.PersonSearchDto;

@RequestMapping(value = "/masterdata")
@RestController
public class MasterDataController {

    @Autowired
    private MasterDataService masterDataService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/sectors", method = RequestMethod.GET)
    public List<BaseClassDto> sectors() {

        return this.beanMapper.mapList(masterDataService.sectors(), BaseClassDto.class);
    }

    @RequestMapping(path = "/types", method = RequestMethod.GET)
    public List<BaseClassDto> types() {

        return this.beanMapper.mapList(masterDataService.types(), BaseClassDto.class);
    }

    @RequestMapping(path = "status", method = RequestMethod.GET)
    public List<BaseClassDto> status() {

        return this.beanMapper.mapList(masterDataService.status(), BaseClassDto.class);
    }

    @RequestMapping(path = "/fileTypes", method = RequestMethod.GET)
    public List<BaseClassDto> fileTypes() {

        return this.beanMapper.mapList(this.masterDataService.fileTypes(), BaseClassDto.class);
    }

    @RequestMapping(path = "/hyperscalers", method = RequestMethod.GET)
    public List<BaseClassDto> hyperscalers() {

        return this.beanMapper.mapList(this.masterDataService.hyperscalers(), BaseClassDto.class);
    }

    @RequestMapping(path = "/methodologys", method = RequestMethod.GET)
    public List<BaseClassDto> methodologys() {

        return this.beanMapper.mapList(this.masterDataService.methodologys(), BaseClassDto.class);
    }

    @RequestMapping(path = "/offerings", method = RequestMethod.GET)
    public List<BaseClassDto> offerings() {

        return this.beanMapper.mapList(this.masterDataService.offerings(), BaseClassDto.class);
    }

    @RequestMapping(path = "/projectTypes", method = RequestMethod.GET)
    public List<BaseClassDto> projectTypes() {

        return this.beanMapper.mapList(this.masterDataService.projectTypes(), BaseClassDto.class);
    }

    @RequestMapping(path = "/persons", method = RequestMethod.POST)
    public Page<PersonDto> persons(@RequestBody PersonSearchDto personSearchDto) {

        return this.beanMapper.mapPage(this.masterDataService.persons(personSearchDto), PersonDto.class);
    }

    @RequestMapping(path = "/clients/{filter}", method = RequestMethod.GET)
    public List<String> clients(@PathVariable String filter) {

        return this.masterDataService.clients(filter);
    }

}
