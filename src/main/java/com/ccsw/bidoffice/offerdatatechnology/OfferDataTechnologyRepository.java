package com.ccsw.bidoffice.offerdatatechnology;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

public interface OfferDataTechnologyRepository extends CrudRepository<OfferDataTechnologyEntity, Long> {

    boolean existsByHyperscalerId(Long id);

    boolean existsByMethodologyId(Long id);
}
