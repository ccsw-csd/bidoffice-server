package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offering.model.OfferingEntity;

public interface OfferingRepository extends CrudRepository<OfferingEntity, Long> {

    List<OfferingEntity> findAllByOrderByPriorityAsc();
}
