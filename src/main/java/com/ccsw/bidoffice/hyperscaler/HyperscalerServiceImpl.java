package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyRepository;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;

@Service
public class HyperscalerServiceImpl implements HyperscalerService {

    @Autowired
    HyperscalerRepository hyperscalerRepository;

    @Autowired
    OfferDataTechnologyRepository offerDataTechRepository;

    @Override
    public List<HyperscalerEntity> getAllDataFromHyperscaler() {

        return (List<HyperscalerEntity>) this.hyperscalerRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void deleteItemFromHyperscaler(Long id) {

        this.hyperscalerRepository.deleteById(id);
    }

    @Override
    public boolean getDataWithOffersFromHyperscaler(Long id) {
        boolean exists = false;

        List<OfferDataTechnologyEntity> offerData = (List<OfferDataTechnologyEntity>) this.offerDataTechRepository
                .findAllByHyperscalerId(id);
        if (offerData.size() > 0)
            exists = true;

        return exists;
    }

}
