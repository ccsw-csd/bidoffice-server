package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.admin.model.FileTypeEntity;

public interface AdminRepository extends CrudRepository<FileTypeEntity, Long> {

    @Query("select ft from FileTypeEntity ft order by priority")
    List<FileTypeEntity> findAllFileTypes();

}
