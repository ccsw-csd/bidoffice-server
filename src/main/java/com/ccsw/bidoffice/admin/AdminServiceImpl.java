package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.admin.model.HyperscalerEntity;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<HyperscalerEntity> getAllDataFromHyperscale() {
        return (List<HyperscalerEntity>) this.adminRepository.findDataFromHyperscaler();
    }

}
