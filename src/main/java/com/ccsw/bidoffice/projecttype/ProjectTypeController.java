package com.ccsw.bidoffice.projecttype;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;

@RequestMapping(value = "/projecttype")
@RestController
public class ProjectTypeController {

    @Autowired
    private ProjectTypeService projectTypeService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<ProjectTypeDto> findAllProjectTypeOrderPriority() {

        return this.beanMapper.mapList(this.projectTypeService.findAllProjectTypeOrderPriority(), ProjectTypeDto.class);
    }

    @RequestMapping(path="/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws AlreadyExistsException {
        this.projectTypeService.delete(id);
    }

    @RequestMapping(path="", method = RequestMethod.PUT)
    public ProjectTypeDto saveProjectType(@RequestBody ProjectTypeDto projectTypeDto) throws AlreadyExistsException, EntityNotFoundException {
        return this.beanMapper.map(projectTypeService.saveProjectType(projectTypeDto), ProjectTypeDto.class);
    }
}
