package com.ccsw.bidoffice.formatdocument;

import java.util.List;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentDto;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentEntity;

public interface FormatDocumentService {

    List<FormatDocumentEntity> findAllFormatDocumentOrderPriority();

    FormatDocumentEntity save(FormatDocumentDto dto) throws AlreadyExistsException, EntityNotFoundException;

    void delete(Long id) throws AlreadyExistsException;
}
