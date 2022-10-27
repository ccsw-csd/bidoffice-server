package com.ccsw.bidoffice.formatdocument;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentDto;

@RequestMapping(value = "/formatdocument")
@RestController
public class FormatDocumentController {

    @Autowired
    private FormatDocumentService formatDocumentService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<FormatDocumentDto> getAll() {
        return this.beanMapper.mapList(this.formatDocumentService.findAllFormatDocumentOrderPriority(),
                FormatDocumentDto.class);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public FormatDocumentDto save(@RequestBody FormatDocumentDto dto)
            throws AlreadyExistsException, EntityNotFoundException {
        return this.beanMapper.map(this.formatDocumentService.save(dto), FormatDocumentDto.class);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) throws AlreadyExistsException {
        this.formatDocumentService.delete(id);
    }
}
