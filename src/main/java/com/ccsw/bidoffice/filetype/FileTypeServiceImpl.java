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

    /**
     * Comprueba que al guardar o editar un FileType, no existe otro registro con el
     * mismo nombre o prioridad.
     * 
     * @param dto Objeto DTO a cotejar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si ya existe otro registro
     *                                con el mismo nombre o prioridad.
     */
    private void checkIfValuesAreDuped(FileTypeDto dto) throws AlreadyExistsException {

        FileTypeEntity compareFileEntity = this.fileTypeRepository.getByName(dto.getName());

        compareFileTipeGetId(dto, compareFileEntity);

        compareFileEntity = this.fileTypeRepository.getByPriority(dto.getPriority());

        compareFileTipeGetId(dto, compareFileEntity);
    }

    /**
     * Método que compara el ID del registro que se está editando con el existente
     * en la base de datos.
     * 
     * @param dto               Registro que se está editando.
     * @param compareTechnology Registro de la base de datos.
     * 
     * @throws AlreadyExistsException Excepción lanzada si hay error.
     */
    private void compareFileTipeGetId(FileTypeDto dto, FileTypeEntity compareFileEntity) throws AlreadyExistsException {

        if ((compareFileEntity != null) && (dto.getId() != compareFileEntity.getId())) {
            throw new AlreadyExistsException();
        }
    }

}
