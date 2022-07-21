package com.ccsw.bidoffice.offertracing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offertracing.model.OfferTracingDto;

@RequestMapping(value = "/offertracing")
@RestController
public class OfferTracingController {

    @Autowired
    private OfferTracingService offerTracingService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferTracing/{id}", method = RequestMethod.GET)
    public List<OfferTracingDto> getOfferOffering(@PathVariable Long id) {

        return this.beanMapper.mapList(this.offerTracingService.findByOfferId(id), OfferTracingDto.class);
    }

}
