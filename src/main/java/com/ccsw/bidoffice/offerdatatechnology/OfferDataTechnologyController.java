package com.ccsw.bidoffice.offerdatatechnology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyDto;

@RequestMapping(value = "/offerdatatechnology")
@RestController
public class OfferDataTechnologyController {

    @Autowired
    private OfferDataTechnologyService offerDataTechnologyService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferDataTechnology/{id}", method = RequestMethod.GET)
    public OfferDataTechnologyDto getOfferofferDataTechnology(@PathVariable Long id) {

        return this.beanMapper.map(this.offerDataTechnologyService.findByOfferId(id),
                OfferDataTechnologyDto.class);
    }
}
