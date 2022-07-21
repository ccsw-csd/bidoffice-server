package com.ccsw.bidoffice.offerdatateam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamDto;

@RequestMapping(value = "/offerdatateam")
@RestController
public class OfferDataTeamController {

    @Autowired
    private OfferDataTeamService offerDataTeamService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferDataTeam/{id}", method = RequestMethod.GET)
    public OfferDataTeamDto findByOfferId(@PathVariable Long id) {

        return this.beanMapper.map(this.offerDataTeamService.findByOfferId(id), OfferDataTeamDto.class);
    }

}
