package com.ccsw.bidoffice.methodology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyService;

@Service
public class MethodologyServiceImpl implements MethodologyService {

    @Autowired
    MethodologyRepository methodologyRepository;

    @Autowired
    OfferDataTechnologyService offerDataService;

    @Override
    public MethodologyEntity get(Long id) throws EntityNotFoundException {
        return this.methodologyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<MethodologyEntity> findAllMethodologyOrderPriority() {

        return this.methodologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    /**
     * Comprueba que al guardar o editar un Methodology, no existe otro registro con
     * el mismo nombre o prioridad.
     * 
     * @param dto Objeto DTO a cotejar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si ya existe otro registro
     *                                con el mismo nombre o prioridad.
     */
    private void checkIfValuesAreDuped(MethodologyDto dto) throws AlreadyExistsException {

        MethodologyEntity compareMethodology = this.methodologyRepository.getByName(dto.getName());

        if (compareMethodology != null) {
            if (dto.getId() != compareMethodology.getId()) {
                throw new AlreadyExistsException();
            }
        }

        compareMethodology = this.methodologyRepository.getByPriority(dto.getPriority());

        if (compareMethodology != null) {
            if (dto.getId() != compareMethodology.getId()) {
                throw new AlreadyExistsException();
            }
        }
    }

    @Override
    public void save(MethodologyDto dto) throws AlreadyExistsException, EntityNotFoundException {
        MethodologyEntity methodology = null;

        checkIfValuesAreDuped(dto);

        if (dto.getId() == null) {
            methodology = new MethodologyEntity();
        } else {
            methodology = this.get(dto.getId());
        }

        methodology.setName(dto.getName());
        methodology.setPriority(dto.getPriority());
        this.methodologyRepository.save(methodology);
    }

    public void delete(Long id) throws AlreadyExistsException {
        if (this.offerDataService.checkIfExistsByMethodologyId(id))
            throw new AlreadyExistsException();

        this.methodologyRepository.deleteById(id);
    }
}
