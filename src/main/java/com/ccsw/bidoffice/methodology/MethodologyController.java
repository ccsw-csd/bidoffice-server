package com.ccsw.bidoffice.methodology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;

@RequestMapping(value = "/methodology")
@RestController
public class MethodologyController {

    @Autowired
    private MethodologyService methodologyService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<MethodologyDto> findAllMethodologyOrderPriority() {

        return this.beanMapper.mapList(this.methodologyService.findAllMethodologyOrderPriority(), MethodologyDto.class);
    }
}
