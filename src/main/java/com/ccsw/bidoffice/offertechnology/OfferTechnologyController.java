package com.ccsw.bidoffice.offertechnology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyDto;

@RequestMapping(value = "/offertechnology")
@RestController
public class OfferTechnologyController {

    @Autowired
    private OfferTechnologyService offerTechnologyService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferTechnology/{id}", method = RequestMethod.GET)
    public List<OfferTechnologyDto> getOfferOffering(@PathVariable Long id) {

        return this.beanMapper.mapList(this.offerTechnologyService.findByOfferId(id), OfferTechnologyDto.class);
    }

}
