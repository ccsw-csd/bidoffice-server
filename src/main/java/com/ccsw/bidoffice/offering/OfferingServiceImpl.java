package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offering.model.OfferingDto;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.offeroffering.OfferOfferingService;

@Service
public class OfferingServiceImpl implements OfferingService {

    @Autowired
    OfferingRepository offeringRepository;

    @Autowired
    OfferOfferingService offerOfferingService;

    @Override
    public List<OfferingEntity> findAllOfferingOrderPriority() {

        return this.offeringRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void save(OfferingDto offeringDto) throws AlreadyExistsException, EntityNotFoundException {

        checkIfAttributesAreWrong(offeringDto);

        OfferingEntity offeringEntity = null;

        if (offeringDto.getId() == null) {
            offeringEntity = new OfferingEntity();
        } else {
            offeringEntity = getById(offeringDto.getId());
        }

        offeringEntity.setName(offeringDto.getName());
        offeringEntity.setPriority(offeringDto.getPriority());

        this.offeringRepository.save(offeringEntity);
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {
        if (this.offerOfferingService.checkExistsByOfferingId(id))
            throw new AlreadyExistsException();

        this.offeringRepository.deleteById(id);
    }

    @Override
    public OfferingEntity getById(Long id) throws EntityNotFoundException {
        return this.offeringRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Comprueba que al guardar o editar un Offering, no existe otro registro con el
     * mismo nombre o prioridad.
     * 
     * @param dto Objeto DTO a cotejar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si ya existe otro registro
     *                                con el mismo nombre o prioridad.
     */
    private void checkIfAttributesAreWrong(OfferingDto dto) throws AlreadyExistsException {

        OfferingEntity compareOffering = this.offeringRepository.getByName(dto.getName());

        compareOfferingGetId(dto, compareOffering);

        compareOffering = this.offeringRepository.getByPriority(dto.getPriority());

        compareOfferingGetId(dto, compareOffering);
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
    private void compareOfferingGetId(OfferingDto dto, OfferingEntity compareOffering) throws AlreadyExistsException {

        if ((compareOffering != null) && (dto.getId() != compareOffering.getId()))
            throw new AlreadyExistsException();
    }

}
