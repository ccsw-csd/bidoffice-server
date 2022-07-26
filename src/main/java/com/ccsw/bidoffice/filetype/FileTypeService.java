package com.ccsw.bidoffice.filetype;

import java.util.List;

import com.ccsw.bidoffice.filetype.model.FileTypeEntity;

public interface FileTypeService {

    List<FileTypeEntity> getAllFromFileType();

    void delete(Long id);

    boolean checkIfOffersWithSameId(Long id);

}
