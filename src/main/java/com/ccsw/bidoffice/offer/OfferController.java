package com.ccsw.bidoffice.offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.InvalidDataException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offer.model.ModifyStatusDto;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferItemListDto;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

@RequestMapping(value = "/offer")
@RestController
public class OfferController {

    @Autowired
    private OfferService offerService;

    @Autowired
    private BeanMapper beanMapper;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OfferDto getOffer(@PathVariable Long id) throws EntityNotFoundException {

        return this.beanMapper.map(this.offerService.getOffer(id), OfferDto.class);
    }

    @RequestMapping(path = "/client/{filter}", method = RequestMethod.GET)
    public List<String> findFirst15DistinctClientLikeFilter(@PathVariable String filter) {

        return this.offerService.findFirst15DistinctClientLikeFilter(filter);
    }

    @RequestMapping(path = "/findPage", method = RequestMethod.POST)
    public Page<OfferItemListDto> findPage(@RequestBody OfferSearchDto dto) throws InvalidDataException {

        return this.beanMapper.mapPage(this.offerService.findPage(dto), OfferItemListDto.class);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public OfferDto save(@RequestBody OfferDto dto) throws InvalidDataException, EntityNotFoundException {

        return this.beanMapper.map(this.offerService.save(dto), OfferDto.class);
    }

    @RequestMapping(path = "/status", method = RequestMethod.PUT)
    public OfferItemListDto modifyStatus(@RequestBody ModifyStatusDto dto)
            throws InvalidDataException, EntityNotFoundException {

        return this.beanMapper.map(this.offerService.modifyStatus(dto), OfferItemListDto.class);
    }
}
