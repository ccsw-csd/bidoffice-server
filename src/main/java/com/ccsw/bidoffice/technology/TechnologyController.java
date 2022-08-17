package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.technology.model.TechnologyDto;

@RequestMapping(value = "/technology")
@RestController
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<TechnologyDto> findAllTechnologyOrderPriority() {

        return this.beanMapper.mapList(this.technologyService.findAllTechnologyOrderPriority(), TechnologyDto.class);
    }
}
