package com.ccsw.bidoffice.filetype;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
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
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerDataFileService.checkExistsByFileTypeId(id))
            throw new AlreadyExistsException();

        this.fileTypeRepository.deleteById(id);
    }

    @Override
    public void save(FileTypeDto data) throws AlreadyExistsException {

        FileTypeEntity file = null;

        if (data.getId() != null) {
            file = this.fileTypeRepository.findById(data.getId()).orElse(null);
            if (file.getName().equals(data.getName())) {
                this.checkIfPriorityExists(data.getPriority());
            } else if (file.getPriority().equals(data.getPriority())) {
                this.checkIfNameExists(data.getName());
            } else {
                this.checkIfPriorityExists(data.getPriority());
                this.checkIfNameExists(data.getName());
            }
            BeanUtils.copyProperties(data, file, "id");
        } else {
            this.checkIfPriorityExists(file.getPriority());
            this.checkIfNameExists(file.getName());
            file = new FileTypeEntity();
            BeanUtils.copyProperties(data, file, "id");
        }

        this.fileTypeRepository.save(file);
    }

    public void checkIfNameExists(String name) throws AlreadyExistsException {
        if (this.fileTypeRepository.existsByName(name))
            throw new AlreadyExistsException();
    }

    public void checkIfPriorityExists(Long priority) throws AlreadyExistsException {
        if (this.fileTypeRepository.existsByPriority(priority))
            throw new AlreadyExistsException();
    }

}
