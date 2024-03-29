package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offertechnology.OfferTechnologyService;
import com.ccsw.bidoffice.technology.model.TechnologyDto;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyRepository technologyRepository;

    @Autowired
    private OfferTechnologyService offerTechnologyService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TechnologyEntity> findAllOrderByPriority() {

        return (List<TechnologyEntity>) this.technologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerTechnologyService.checkExistsByTechnologyId(id))
            throw new AlreadyExistsException();

        this.technologyRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TechnologyEntity getById(Long id) throws EntityNotFoundException {

        return this.technologyRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }

    /**
     * Guarda o modifica una tecnología existente en la base de datos.
     * 
     * @param dto Objeto DTO de la tecnología a guardar.
     * 
     * @throws EntityNotFoundException Si no encuentra la tecnología a modificar.
     * @throws AlreadyExistException   Si la tecnología ya existe al guardar.
     */
    @Override
    public void saveTechnology(TechnologyDto dto) throws AlreadyExistsException, EntityNotFoundException {

        this.checkWhenTechAttributesAlreadyUsed(dto);

        TechnologyEntity technologyEntity = null;

        if (dto.getId() != null) {
            technologyEntity = getById(dto.getId());
        } else {
            technologyEntity = new TechnologyEntity();
        }

        technologyEntity.setName(dto.getName());
        technologyEntity.setPriority(dto.getPriority());

        this.technologyRepository.save(technologyEntity);

    }

    /**
     * Comprueba que no exista el mismo nombre de una tecnologia o la misma
     * prioridad.
     * 
     * @param dto Objeto DTO de la tecnología a comprobar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si el nombre o la prioridad
     *                                coinciden con algún registro de la BBDD.
     */
    private void checkWhenTechAttributesAlreadyUsed(TechnologyDto dto) throws AlreadyExistsException {

        TechnologyEntity compareTechnology = this.technologyRepository.getByName(dto.getName());

        compareTechnologyGetId(dto, compareTechnology);

        compareTechnology = this.technologyRepository.getByPriority(dto.getPriority());

        compareTechnologyGetId(dto, compareTechnology);

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
    private void compareTechnologyGetId(TechnologyDto dto, TechnologyEntity compareTechnology)
            throws AlreadyExistsException {

        if ((compareTechnology != null) && (dto.getId() != compareTechnology.getId()))
            throw new AlreadyExistsException();
    }
}
