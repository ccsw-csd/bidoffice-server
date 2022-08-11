package com.ccsw.bidoffice.methodology;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.methodology.model.MethodologyEntity;

public interface MethodologyRepository extends CrudRepository<MethodologyEntity, Long> {

    List<MethodologyEntity> findAll(Sort sort);

    Boolean existsByIdIsNotAndName(Long id, String name);

    Boolean existsByIdIsNotAndPriority(Long id, Integer priority);

    Boolean existsByName(String name);

    Boolean existsByPriority(Integer priority);
}
