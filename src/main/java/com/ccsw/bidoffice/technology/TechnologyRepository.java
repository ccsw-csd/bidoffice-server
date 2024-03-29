package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.technology.model.TechnologyEntity;

public interface TechnologyRepository extends CrudRepository<TechnologyEntity, Long> {

    List<TechnologyEntity> findAll(Sort sort);

    TechnologyEntity getByName(String name);

    TechnologyEntity getByPriority(int existsPriority);

}
