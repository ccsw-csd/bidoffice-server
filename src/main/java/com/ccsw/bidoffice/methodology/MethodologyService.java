package com.ccsw.bidoffice.methodology;

import java.util.List;

import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;

public interface MethodologyService {

    MethodologyEntity get(Long id);

    List<MethodologyEntity> findAllMethodologyOrderPriority();

    void save(Long id, MethodologyDto dto) throws AlreadyExistsException;
}
