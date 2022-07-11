package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

public interface HyperscalerRepository extends CrudRepository<HyperscalerEntity, Long> {

    List<HyperscalerEntity> findAllByOrderByPriorityAsc();
}
