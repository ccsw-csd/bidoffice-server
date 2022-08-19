package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.sector.model.SectorEntity;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    SectorRepository sectorRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectorEntity> findAllSectorOrderPriority() {

        return this.sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

}
