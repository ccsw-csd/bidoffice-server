package com.ccsw.bidoffice.formatdocument;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentDto;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentEntity;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileService;

@Service
public class FormatDocumentServiceImpl implements FormatDocumentService {

    @Autowired
    private FormatDocumentRepository formatDocumentRepository;

    @Autowired
    private OfferDataFileService offerDataFileService;

    @Override
    public List<FormatDocumentEntity> findAllFormatDocumentOrderPriority() {

        return this.formatDocumentRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public FormatDocumentEntity save(FormatDocumentDto dto) throws AlreadyExistsException, EntityNotFoundException {

        this.validateFormatDocument(dto);

        if (dto.getId() == null)
            return this.saveNewFormatDocument(dto);

        return this.modifyFormatDocument(dto);
    }

    private FormatDocumentEntity saveNewFormatDocument(FormatDocumentDto dto) throws AlreadyExistsException {

        FormatDocumentEntity formatDocumentEntity = new FormatDocumentEntity();
        BeanUtils.copyProperties(dto, formatDocumentEntity);

        return this.formatDocumentRepository.save(formatDocumentEntity);
    }

    private FormatDocumentEntity modifyFormatDocument(FormatDocumentDto dto) throws EntityNotFoundException {

        FormatDocumentEntity formatDocumentEntity = this.formatDocumentRepository.findById(dto.getId())
                .orElseThrow(EntityNotFoundException::new);

        formatDocumentEntity.setName(dto.getName());
        formatDocumentEntity.setPriority(dto.getPriority());

        return this.formatDocumentRepository.save(formatDocumentEntity);
    }

    private void validateFormatDocument(FormatDocumentDto dto) throws AlreadyExistsException {

        isFormatDocumentAlreadyExist(dto, formatDocumentRepository.findByNameIgnoreCaseContaining(dto.getName()));
        isFormatDocumentAlreadyExist(dto, formatDocumentRepository.findByPriority(dto.getPriority()));
    }

    private void isFormatDocumentAlreadyExist(FormatDocumentDto dto, FormatDocumentEntity formatDocumentEntity)
            throws AlreadyExistsException {

        if (formatDocumentEntity != null && dto.getId() != formatDocumentEntity.getId())
            throw new AlreadyExistsException();
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerDataFileService.checkExistsByFormatDocumentId(id))
            throw new AlreadyExistsException();

        this.formatDocumentRepository.deleteById(id);

    }

}
