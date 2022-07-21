package com.ccsw.bidoffice.offerdataproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectDto;

@RequestMapping(value = "/offerdataproject")
@RestController
public class OfferDataProjectController {

    @Autowired
    private OfferDataProjectService offerDataProjectService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferDataProject/{id}", method = RequestMethod.GET)
    public OfferDataProjectDto findByOfferId(@PathVariable Long id) {

        return this.beanMapper.map(this.offerDataProjectService.findByOfferId(id), OfferDataProjectDto.class);
    }
}
