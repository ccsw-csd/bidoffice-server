package com.ccsw.bidoffice.offerdatachapter;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterEntity;

public class OfferDataChapterServiceImpl implements OfferDataChapterService {

    @Autowired
    private OfferDataChapterRepository offerDataChapterRepository;

    @Override
    public OfferDataChapterEntity getOfferDataChapter(Long id) {

        return this.offerDataChapterRepository.findById(id).orElse(null);
    }

}
