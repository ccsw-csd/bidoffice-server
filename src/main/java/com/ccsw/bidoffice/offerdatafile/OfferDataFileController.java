package com.ccsw.bidoffice.offerdatafile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;

@RequestMapping(value = "/offerdatafile")
@RestController
public class OfferDataFileController {

    @Autowired
    private OfferDataFileService offerDataFileService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferDataFile/{id}", method = RequestMethod.GET)
    public List<OfferDataFileDto> findByOfferId(@PathVariable Long id) {

        return this.beanMapper.mapList(this.offerDataFileService.findByOfferId(id), OfferDataFileDto.class);
    }
}
