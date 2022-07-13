package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.admin.model.FileTypeDto;
import com.ccsw.bidoffice.admin.model.FileTypeEntity;
import com.ccsw.bidoffice.config.mapper.BeanMapper;

@RequestMapping(value = "/admin")
@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/filetype", method = RequestMethod.GET)
    public List<FileTypeDto> getAllFromFileType() {

        List<FileTypeEntity> filetype = this.adminService.getAllFromFileType();

        return beanMapper.mapList(filetype, FileTypeDto.class);
    }

}
