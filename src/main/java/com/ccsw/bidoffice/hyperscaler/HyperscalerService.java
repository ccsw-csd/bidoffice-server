package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

public interface HyperscalerService {

    public List<HyperscalerEntity> getAllDataFromHyperscaler();

    public HyperscalerEntity getById(Long id) throws EntityNotFoundException;

    public void delete(Long id) throws AlreadyExistsException;

    public void saveItem(HyperscalerDto hyperscalerDto) throws AlreadyExistsException, EntityNotFoundException;

}
