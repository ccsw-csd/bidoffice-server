package com.ccsw.bidoffice.filetype;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.filetype.model.FileTypeEntity;

public interface FileTypeService {

    List<FileTypeEntity> getAllFromFileType();

    void delete(Long id) throws AlreadyExistsException;

}
