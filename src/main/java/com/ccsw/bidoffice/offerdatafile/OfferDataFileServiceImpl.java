package com.ccsw.bidoffice.offerdatafile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

@Service
public class OfferDataFileServiceImpl implements OfferDataFileService {

    @Autowired
    private OfferDataFileRepository offerDataFileRepository;

    @Override
    public List<OfferDataFileEntity> findByOfferId(Long id) {

        return offerDataFileRepository.findByOfferId(id);

    }

}
