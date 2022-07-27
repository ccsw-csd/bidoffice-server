package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.filetype.model.FileTypeEntity;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileService;

@Service
public class FileTypeServiceImpl implements FileTypeService {

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private OfferDataFileService offerDataFileService;

    @Override
    public List<FileTypeEntity> getAllFromFileType() {

        return this.fileTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerDataFileService.checkExistsById(id))
            throw new AlreadyExistsException();

        this.fileTypeRepository.deleteById(id);
    }

}
