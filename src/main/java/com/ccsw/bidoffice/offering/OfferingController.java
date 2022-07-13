package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
