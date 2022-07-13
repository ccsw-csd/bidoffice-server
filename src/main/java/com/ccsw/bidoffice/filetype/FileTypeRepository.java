package com.ccsw.bidoffice.file_type;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.file_type.model.FileTypeEntity;

public interface FileTypeRepository extends CrudRepository<FileTypeEntity, Long> {

    List<FileTypeEntity> findAllByOrderByPriorityAsc();
}
