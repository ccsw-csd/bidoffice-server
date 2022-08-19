package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offering.model.OfferingEntity;

public interface OfferingRepository extends CrudRepository<OfferingEntity, Long> {

    List<OfferingEntity> findAll(Sort sort);
    
    boolean existsByPriority(Long priority);

    boolean existsByName(String name);

    boolean existsByIdIsNotAndName(Long id, String name);

    boolean existsByIdIsNotAndPriority(Long id, Long priority);

}
