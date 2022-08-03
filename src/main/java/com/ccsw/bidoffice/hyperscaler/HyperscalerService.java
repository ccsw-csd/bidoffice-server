package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

public interface HyperscalerService {

    public List<HyperscalerEntity> getAllDataFromHyperscaler();

    public void delete(Long id) throws AlreadyExistsException;

    public void saveItem(HyperscalerDto hyperscalerDto) throws AlreadyExistsException;

    public boolean checkIfExistsPriority(Long priority);

    public boolean checkIfExistsName(String name);

    public void checkWhenNamesAreEquals(HyperscalerDto hyperscalerDto) throws AlreadyExistsException;

    public void checkWhenPriorityIsEqual(HyperscalerDto hyperscalerDto) throws AlreadyExistsException;

    public void checkWhenAttributesAreDifferent(HyperscalerDto hyperscalerDto) throws AlreadyExistsException;

}
