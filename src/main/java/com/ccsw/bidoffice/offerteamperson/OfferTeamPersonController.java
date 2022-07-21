package com.ccsw.bidoffice.offerteamperson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonDto;

@RequestMapping(value = "/offerteamperson")
@RestController
public class OfferTeamPersonController {

    @Autowired
    private OfferTeamPersonService offerTeamPersonService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferTeamPerson/{id}", method = RequestMethod.GET)
    public List<OfferTeamPersonDto> getOfferOffering(@PathVariable Long id) {

        return this.beanMapper.mapList(this.offerTeamPersonService.findByOfferId(id), OfferTeamPersonDto.class);
    }
}
