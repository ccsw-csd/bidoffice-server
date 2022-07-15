package com.ccsw.bidoffice.offering;

import java.util.List;

import com.ccsw.bidoffice.offering.model.OfferingEntity;

public interface OfferingService {

    List<OfferingEntity> findAllOfferingOrderPriority();
}
