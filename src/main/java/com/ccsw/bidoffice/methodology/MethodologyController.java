package com.ccsw.bidoffice.methodology;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;

@RequestMapping(value = "/methodology")
@RestController
public class MethodologyController {

    @Autowired
    private MethodologyService methodologyService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<MethodologyDto> findAllMethodologyOrderPriority() {
        return this.beanMapper.mapList(this.methodologyService.findAllMethodologyOrderPriority(), MethodologyDto.class);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void save(@RequestBody MethodologyDto dto) throws AlreadyExistsException, EntityNotFoundException {
        this.methodologyService.save(dto);
    }
}
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) throws AlreadyExistsException {
        this.methodologyService.delete(id);

    }
}
