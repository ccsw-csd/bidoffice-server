package com.ccsw.bidoffice.offerdatachapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterEntity;

@Service
public class OfferDataChapterServiceImpl implements OfferDataChapterService {

    @Autowired
    private OfferDataChapterRepository offerDataChapterRepository;

    @Override
    public OfferDataChapterEntity findByOfferId(Long id) {

        return offerDataChapterRepository.findByOfferId(id);
    }

}
