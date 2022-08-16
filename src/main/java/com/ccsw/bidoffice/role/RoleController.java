package com.ccsw.bidoffice.role;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.role.model.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/role")
@RestController
public class RoleController {

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    RoleService roleService;

    @RequestMapping(path="/findAll", method = RequestMethod.GET)
    public List<RoleDto> getAllFromRole(){
        return this.beanMapper.mapList(this.roleService.getAllDataFromRole(), RoleDto.class);
    }

}
