package com.ccsw.bidoffice.offeroffering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offeroffering.model.OfferOfferingDto;

@RequestMapping(value = "/offeroffering")
@RestController
public class OfferOfferingController {

    @Autowired
    private OfferOfferingService offerOfferingService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferOffering/{id}", method = RequestMethod.GET)
    public List<OfferOfferingDto> getOfferOffering(@PathVariable Long id) {

        return this.beanMapper.mapList(offerOfferingService.findByOfferId(id), OfferOfferingDto.class);
    }

}
