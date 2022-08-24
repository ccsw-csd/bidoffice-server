package com.ccsw.bidoffice.offering;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offering.model.OfferingDto;
import com.ccsw.bidoffice.offering.model.OfferingEntity;

public interface OfferingService {

    public List<OfferingEntity> findAllOfferingOrderPriority();
    
    public OfferingEntity getById(Long id) throws EntityNotFoundException;

    public void delete(Long id) throws AlreadyExistsException;

    public void save(OfferingDto offeringDto) throws AlreadyExistsException, EntityNotFoundException;
}
