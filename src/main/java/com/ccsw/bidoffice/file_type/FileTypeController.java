package com.ccsw.bidoffice.file_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.file_type.model.FileTypeDto;
import com.ccsw.bidoffice.file_type.model.FileTypeEntity;

@RequestMapping(value = "/filetype")
@RestController
@CrossOrigin(origins = "*")
public class FileTypeController {

    @Autowired
    private FileTypeService fileTypeService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<FileTypeDto> getAllFromFileType() {

        List<FileTypeEntity> filetype = this.fileTypeService.getAllFromFileType();

        return beanMapper.mapList(filetype, FileTypeDto.class);
    }

}
