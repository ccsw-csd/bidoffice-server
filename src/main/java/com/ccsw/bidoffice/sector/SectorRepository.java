package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.sector.model.SectorEntity;

public interface SectorRepository extends CrudRepository<SectorEntity, Long> {

    List<SectorEntity> findAll(Sort sort);

}
