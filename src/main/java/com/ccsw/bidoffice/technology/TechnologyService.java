package com.ccsw.bidoffice.technology;

import java.util.List;

import com.ccsw.bidoffice.technology.model.TechnologyEntity;

public interface TechnologyService {

    List<TechnologyEntity> findAllOrderByPriority();
}
