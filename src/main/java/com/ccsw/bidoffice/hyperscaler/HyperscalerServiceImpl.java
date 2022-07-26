package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

@Service
public class HyperscalerServiceImpl implements HyperscalerService {

    @Autowired
    HyperscalerRepository hyperscalerRepository;

    @Override
    public List<HyperscalerEntity> getAllDataFromHyperscaler() {

        return (List<HyperscalerEntity>) this.hyperscalerRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void deleteItemFromHyperscaler(Long id) {

        this.hyperscalerRepository.deleteById(id);
    }

    @Override
    public List<HyperscalerEntity> getDataWithOffersFromHyperscaler(Long id) {
        // TODO Auto-generated method stub
        // select * from offer_data_technology odt ; Seleccionamos esta tabla
        return (List<HyperscalerEntity>) this.hyperscalerRepository.getDataFromOfferDataTechnology(id);
    }

}
