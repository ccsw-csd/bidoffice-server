package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.offering.model.OfferingEntity;

@Service
public class OfferingServiceImpl implements OfferingService {

    @Autowired
    OfferingRepository offeringRepository;

    @Override
    public List<OfferingEntity> findAllOfferingOrderPriority() {

        return this.offeringRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

}
