package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;

@RequestMapping(value = "/hyperscaler")
@RestController
@CrossOrigin(origins = "*")
public class HyperscalerController {

    @Autowired
    HyperscalerService hyperscalerService;

    @Autowired
    BeanMapper beanMapper;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<HyperscalerDto> getAllFromHyperscale() {
        return this.beanMapper.mapList(this.hyperscalerService.getAllDataFromHyperscaler(), HyperscalerDto.class);
    }
}
