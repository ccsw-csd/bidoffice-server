package com.ccsw.bidoffice.offerdatafile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferDataFileServiceImpl implements OfferDataFileService {

    @Autowired
    private OfferDataFileRepository offerDataFileRepository;

    @Override
    public boolean checkExistsByFileTypeId(Long id) {
        return this.offerDataFileRepository.existsByFileTypeId(id);
    }

}