package com.ccsw.bidoffice.technology;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

public interface TechnologyService {

    List<TechnologyEntity> findAllOrderByPriority();

    void delete(Long id) throws AlreadyExistsException;
}
