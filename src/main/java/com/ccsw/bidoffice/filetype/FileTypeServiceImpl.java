package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.filetype.model.FileTypeEntity;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileRepository;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

@Service
public class FileTypeServiceImpl implements FileTypeService {

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private OfferDataFileRepository offerDataFileRepository;

    @Override
    public List<FileTypeEntity> getAllFromFileType() {

        return this.fileTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void delete(Long id) {

        this.fileTypeRepository.deleteById(id);

    }

    @Override
    public boolean checkIfOffersWithSameId(Long id) {
        boolean res = false;

        List<OfferDataFileEntity> offerList = null;

        offerList = (List<OfferDataFileEntity>) this.offerDataFileRepository.findAllByFileTypeId(id);

        if (offerList.size() > 0)
            res = true;

        return res;
    }
}
