package com.ccsw.bidoffice.opportunitystatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;

@RequestMapping(value = "/opportunityStatus")
@RestController
public class OpportunityStatusController {

    @Autowired
    private OpportunityStatusService opportunityStatusService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<OpportunityStatusDto> findAllOpportunityStatusOrderPriority() {

        return this.beanMapper.mapList(this.opportunityStatusService.findAllOpportunityStatusOrderPriority(),
                OpportunityStatusDto.class);
    }
}
