package com.ccsw.bidoffice.sector;

import java.util.List;

import com.ccsw.bidoffice.sector.model.SectorEntity;

public interface SectorService {

    List<SectorEntity> findAllSectorOrderPriority();
}
