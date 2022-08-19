package com.ccsw.bidoffice.offerdataproject;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectEntity;

public interface OfferDataProjectRepository extends CrudRepository<OfferDataProjectEntity, Long> {

    boolean existsByProjectTypeId(Long id);

}
