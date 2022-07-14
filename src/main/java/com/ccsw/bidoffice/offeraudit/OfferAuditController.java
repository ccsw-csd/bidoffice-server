package com.ccsw.bidoffice.offeraudit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offeraudit.model.OfferAuditDto;

@RequestMapping(value = "/offeraudit")
@RestController
public class OfferAuditController {

    @Autowired
    OfferAuditService offerAuditService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OfferAuditDto getOfferAudit(@PathVariable Long id) {

        return this.beanMapper.map(this.offerAuditService.getOfferAudit(id), OfferAuditDto.class);
    }
}
