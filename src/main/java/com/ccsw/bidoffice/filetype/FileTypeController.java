package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.filetype.model.FileTypeDto;

@RequestMapping(value = "/filetype")
@RestController
@CrossOrigin(origins = "*")
public class FileTypeController {

    @Autowired
    private FileTypeService fileTypeService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<FileTypeDto> getAllFromFileType() {

        return beanMapper.mapList(this.fileTypeService.getAllFromFileType(), FileTypeDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws AlreadyExistsException {

        this.fileTypeService.delete(id);
    }

}
