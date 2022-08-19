package com.ccsw.bidoffice.offering;

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
import com.ccsw.bidoffice.offering.model.OfferingDto;

@RequestMapping(value = "/offering")
@RestController
public class OfferingController {

    @Autowired
    private OfferingService offeringService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<OfferingDto> findAllOfferingOrderPriority() {

        return this.beanMapper.mapList(this.offeringService.findAllOfferingOrderPriority(), OfferingDto.class);
    }
    
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") Long id) throws AlreadyExistsException {
        this.offeringService.delete(id);
    }

    @RequestMapping(path = { "" }, method = RequestMethod.PUT)
    public void save(@RequestBody OfferingDto offeringDto)
            throws AlreadyExistsException, EntityNotFoundException {
        this.offeringService.save(offeringDto);
    }
}
