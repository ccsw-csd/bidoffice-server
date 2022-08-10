package com.ccsw.bidoffice.methodology;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;

public interface MethodologyService {

    List<MethodologyEntity> findAllMethodologyOrderPriority();

    void delete(Long id) throws AlreadyExistsException;
}
