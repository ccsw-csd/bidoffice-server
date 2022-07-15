package com.ccsw.bidoffice.projecttype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;

@RequestMapping(value = "/projecttype")
@RestController
public class PprojectTypeController {

    @Autowired
    private ProjectTypeService projectTypeService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<ProjectTypeDto> findAllProjectTypeOrderPriority() {

        return this.beanMapper.mapList(this.projectTypeService.findAllProjectTypeOrderPriority(), ProjectTypeDto.class);
    }
}
