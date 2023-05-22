package com.ccsw.bidoffice.offerchangestatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusDto;

@RequestMapping(value = "/offerchangestatus")
@RestController
public class OfferChangeStatusController {

    @Autowired
    private OfferChangeStatusService offerChangeStatusService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<OfferChangeStatusDto> findByOfferId(@PathVariable Long id) {
        return this.beanMapper.mapList(this.offerChangeStatusService.findByOfferId(id), OfferChangeStatusDto.class);
    }
}
