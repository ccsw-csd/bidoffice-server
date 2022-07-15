package com.ccsw.bidoffice.opportunitytype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;

@RequestMapping(value = "/opportunitytype")
@RestController
public class OpportunityTypeController {

    @Autowired
    private OpportunityTypeService opportunityTypeService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<OpportunityTypeDto> findAllOpportunityTypeOrderPriority() {

        return this.beanMapper.mapList(this.opportunityTypeService.findAllOpportunityTypeOrderPriority(),
                OpportunityTypeDto.class);
    }
}
