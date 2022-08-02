package com.ccsw.bidoffice.offerdatatechnology;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OfferDataTechnologyServiceImpl implements OfferDataTechnologyService {

    @Autowired
    OfferDataTechnologyRepository offerDataTechnologyRepository;

    @Override
    public boolean checkExistsByHyperscalerId(Long id) {
        return this.offerDataTechnologyRepository.existsByHyperscalerId(id);
    }

}
