package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.filetype.model.FileTypeEntity;

public interface FileTypeRepository extends CrudRepository<FileTypeEntity, Long> {

    List<FileTypeEntity> findAll(Sort sort);

}
