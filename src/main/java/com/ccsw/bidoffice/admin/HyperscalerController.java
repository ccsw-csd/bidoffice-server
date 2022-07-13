package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.admin.model.HyperscalerDto;
import com.ccsw.bidoffice.config.mapper.BeanMapper;

@RequestMapping(value = "/admin")
@RestController
@CrossOrigin(origins = "*")
public class HyperscalerController {

    @Autowired
    HyperscalerService hyperscalerService;

    @Autowired
    BeanMapper beanMapper;

    @RequestMapping(path = "/hyperscaler", method = RequestMethod.GET)
    public List<HyperscalerDto> getAllFromHyperscale() {
        return this.beanMapper.mapList(this.hyperscalerService.getAllDataFromHyperscaler(), HyperscalerDto.class);
    }
}
