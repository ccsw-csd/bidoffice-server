package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

public interface HyperscalerService {

    public List<HyperscalerEntity> getAllDataFromHyperscaler();

    public void deleteItemFromHyperscaler(Long id);

    public List<HyperscalerEntity> getDataWithOffersFromHyperscaler(Long id);
}
