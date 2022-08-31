package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offering.model.OfferingEntity;

public interface OfferingRepository extends CrudRepository<OfferingEntity, Long> {

    List<OfferingEntity> findAll(Sort sort);
    
    Boolean existsByPriority(Integer priority);

    Boolean existsByName(String name);

    Boolean existsByIdIsNotAndName(Long id, String name);

    Boolean existsByIdIsNotAndPriority(Long id, Integer priority);

}
