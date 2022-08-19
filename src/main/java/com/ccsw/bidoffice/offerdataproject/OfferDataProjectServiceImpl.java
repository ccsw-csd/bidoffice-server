package com.ccsw.bidoffice.offerdataproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferDataProjectServiceImpl implements OfferDataProjectService{

    @Autowired
    private OfferDataProjectRepository offerDataProjectRepository;

    @Override
    public boolean checkExistsByProjectTypeId(Long id) {
        return this.offerDataProjectRepository.existsByProjectTypeId(id);
    }

}
