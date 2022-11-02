package com.ccsw.bidoffice.formatdocument;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.formatdocument.model.FormatDocumentEntity;

public interface FormatDocumentRepository extends CrudRepository<FormatDocumentEntity, Long> {

    List<FormatDocumentEntity> findAll(Sort sort);

    FormatDocumentEntity findByNameIgnoreCaseContaining(String name);

    FormatDocumentEntity findByPriority(Integer priority);
}
