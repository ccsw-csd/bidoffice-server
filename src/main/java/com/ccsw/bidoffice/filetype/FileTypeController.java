package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/checkOffers/{id}", method = RequestMethod.GET)
    public boolean getOffersWithSameId(@PathVariable("id") Long id) {

        return this.fileTypeService.checkIfOffersWithSameId(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        this.fileTypeService.delete(id);
    }

}
