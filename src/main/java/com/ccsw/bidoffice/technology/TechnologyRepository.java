package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.technology.model.TechnologyEntity;

public interface TechnologyRepository extends CrudRepository<TechnologyEntity, Long> {

    List<TechnologyEntity> findAll(Sort sort);

    boolean existsByPriority(Long priority);

    boolean existsByName(String name);

    boolean existsByIdIsNotAndName(Long id, String name);

    boolean existsByIdIsNotAndPriority(Long id, Long priority);

}
