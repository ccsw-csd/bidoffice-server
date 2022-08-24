package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.filetype.model.FileTypeDto;
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
    public FileTypeEntity getFileTypeById(Long id) throws EntityNotFoundException {
        return this.fileTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerDataFileService.checkExistsByFileTypeId(id))
            throw new AlreadyExistsException();

        this.fileTypeRepository.deleteById(id);
    }

    @Override
    public void save(FileTypeDto data) throws AlreadyExistsException, EntityNotFoundException {

        checkIfValuesAreDuped(data);

        FileTypeEntity file = null;

        if (data.getId() == null) {
            file = new FileTypeEntity();
        } else {
            file = this.getFileTypeById(data.getId());
        }

        BeanUtils.copyProperties(data, file, "id");
        this.fileTypeRepository.save(file);
    }

    private void checkIfValuesAreDuped(FileTypeDto dto) throws AlreadyExistsException {
        Boolean dupeName, dupePriority;

        if (dto.getId() != null) {
            dupeName = this.fileTypeRepository.existsByIdIsNotAndName(dto.getId(), dto.getName());
            dupePriority = this.fileTypeRepository.existsByIdIsNotAndPriority(dto.getId(), dto.getPriority());
        } else {
            dupeName = this.fileTypeRepository.existsByName(dto.getName());
            dupePriority = this.fileTypeRepository.existsByPriority(dto.getPriority());
        }

        if (dupeName || dupePriority)
            throw new AlreadyExistsException();
    }

}
