package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.admin.model.HyperscalerEntity;

@Service
public class HyperscalerServiceImpl implements HyperscalerService {

    @Autowired
    HyperscalerRepository hyperscalerRepository;

    @Override
    public List<HyperscalerEntity> getAllDataFromHyperscaler() {
        return (List<HyperscalerEntity>) this.hyperscalerRepository.findByOrderByPriority();
    }

}
