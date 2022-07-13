package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.admin.model.HyperscaleDto;
import com.ccsw.bidoffice.config.mapper.BeanMapper;

@RequestMapping(value = "/admin")
@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    BeanMapper beanMapper;

    @RequestMapping(path = "/hyperscaler", method = RequestMethod.GET)
    public List<HyperscaleDto> getAllFromHyperscale() {
        return this.beanMapper.mapList(this.adminService.getAllDataFromHyperscale(), HyperscaleDto.class);
    }
}
