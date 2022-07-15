package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.filetype.model.FileTypeEntity;

@Service
public class FileTypeServiceImpl implements FileTypeService {

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Override
    public List<FileTypeEntity> getAllFromFileType() {

        return this.fileTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }
}
