package com.ccsw.bidoffice.methodology;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.methodology.model.MethodologyEntity;

public interface MethodologyRepository extends CrudRepository<MethodologyEntity, Long> {

    List<MethodologyEntity> findAllByOrderByPriorityAsc();
}
