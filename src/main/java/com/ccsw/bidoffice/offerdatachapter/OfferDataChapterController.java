package com.ccsw.bidoffice.offerdatachapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterDto;

@RequestMapping(value = "/offerdatachapter")
@RestController
public class OfferDataChapterController {

    @Autowired
    private OfferDataChapterService offerDataChapterService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/findOfferDataChapter/{id}", method = RequestMethod.GET)
    public OfferDataChapterDto findByOfferId(@PathVariable Long id) {

        return this.beanMapper.map(this.offerDataChapterService.findByOfferId(id), OfferDataChapterDto.class);
    }

}
