package com.ccsw.bidoffice.methodology;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;

public interface MethodologyService {

    MethodologyEntity get(Long id) throws EntityNotFoundException;

    List<MethodologyEntity> findAllMethodologyOrderPriority();

    void save(MethodologyDto dto) throws AlreadyExistsException, EntityNotFoundException;
    
    void delete(Long id) throws AlreadyExistsException;
}
