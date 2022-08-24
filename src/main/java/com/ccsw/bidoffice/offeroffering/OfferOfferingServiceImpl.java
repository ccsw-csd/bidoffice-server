package com.ccsw.bidoffice.offeroffering;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OfferOfferingServiceImpl implements OfferOfferingService {

    @Autowired
    OfferOfferingRepository offerOfferingRepository;

    
    @Override
    public boolean checkExistsByOfferingId(Long id) {
        return this.offerOfferingRepository.existsByOfferingId(id);
    }

}
