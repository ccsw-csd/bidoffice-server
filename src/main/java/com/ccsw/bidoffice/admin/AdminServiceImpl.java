package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.admin.model.FileTypeEntity;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<FileTypeEntity> getAllFromFileType() {

        return (List<FileTypeEntity>) this.adminRepository.findAllFileTypes();
    }
}
