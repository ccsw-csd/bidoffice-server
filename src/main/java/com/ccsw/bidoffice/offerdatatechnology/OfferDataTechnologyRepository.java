package com.ccsw.bidoffice.offerdatatechnology;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

public interface OfferDataTechnologyRepository extends CrudRepository<OfferDataTechnologyEntity, Long> {

    List<OfferDataTechnologyEntity> findAllByHyperscalerId(Long id);
}
