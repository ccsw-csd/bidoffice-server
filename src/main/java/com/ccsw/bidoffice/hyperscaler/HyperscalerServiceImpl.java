package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyService;

@Service
@Transactional
public class HyperscalerServiceImpl implements HyperscalerService {

    @Autowired
    HyperscalerRepository hyperscalerRepository;

    @Autowired
    OfferDataTechnologyService offerDataTechnologyService;

    @Override
    public List<HyperscalerEntity> getAllDataFromHyperscaler() {

        return (List<HyperscalerEntity>) this.hyperscalerRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {
        if (this.offerDataTechnologyService.checkExistsByHyperscalerId(id))
            throw new AlreadyExistsException();

        this.hyperscalerRepository.deleteById(id);
    }

    @Override
    public HyperscalerEntity getById(Long id) throws EntityNotFoundException {
        return this.hyperscalerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveItem(HyperscalerDto hyperscalerDto) throws AlreadyExistsException, EntityNotFoundException {

        checkWhenAttributesAreWrong(hyperscalerDto);

        HyperscalerEntity hyperscalerEntity = null;

        if (hyperscalerDto.getId() != null) {
            hyperscalerEntity = getById(hyperscalerDto.getId());

        } else {
            hyperscalerEntity = new HyperscalerEntity();
        }

        hyperscalerEntity.setName(hyperscalerDto.getName());
        hyperscalerEntity.setPriority(hyperscalerDto.getPriority());

        this.hyperscalerRepository.save(hyperscalerEntity);
    }

    /**
     * Comprueba que al guardar o editar un Hyperscaler, no existe otro registro con
     * el mismo nombre o prioridad.
     * 
     * @param dto Objeto DTO a cotejar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si ya existe otro registro
     *                                con el mismo nombre o prioridad.
     */
    private void checkWhenAttributesAreWrong(HyperscalerDto dto) throws AlreadyExistsException {

        HyperscalerEntity compareHyperscaler = this.hyperscalerRepository.getByName(dto.getName());

        compareHyperScalerGetId(dto, compareHyperscaler);

        compareHyperscaler = this.hyperscalerRepository.getByPriority(dto.getPriority());

        compareHyperScalerGetId(dto, compareHyperscaler);
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
    private void compareHyperScalerGetId(HyperscalerDto dto, HyperscalerEntity compareHyperscaler)
            throws AlreadyExistsException {

        if ((compareHyperscaler != null) && (dto.getId() != compareHyperscaler.getId()))
            throw new AlreadyExistsException();
    }

}
