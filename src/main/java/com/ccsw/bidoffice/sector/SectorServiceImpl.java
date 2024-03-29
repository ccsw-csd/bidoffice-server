package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.UpdateConflictException;
import com.ccsw.bidoffice.offer.OfferService;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    private OfferService offerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectorEntity> findAllSectorOrderPriority() {

        return this.sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerService.checkIfSectorIsUsingInOfferBySectorId(id)) {
            throw new AlreadyExistsException();
        }

        this.sectorRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SectorEntity getById(Long id) throws EntityNotFoundException {

        return this.sectorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveSector(SectorDto dto)
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException {

        boolean result = false;

        this.checkWhenSectorAttributesAlreadyUsed(dto);

        SectorEntity sectorEntity = null;

        if (dto.getId() != null) {
            sectorEntity = getById(dto.getId());
            result = this.offerService.checkIfDateOfAllOfferAreInRange(dto);

            if (result == true) {
                throw new UpdateConflictException(
                        "La fecha de creación de alguna oferta usada en este sector está fuera de rango.");
            }
        } else {
            sectorEntity = new SectorEntity();
        }

        sectorEntity.setName(dto.getName());
        sectorEntity.setPriority(dto.getPriority());
        sectorEntity.setStartDate(dto.getStartDate());
        sectorEntity.setEndDate(dto.getEndDate());

        this.sectorRepository.save(sectorEntity);

    }

// Funciones privadas.

    /**
     * Comprueba que no existe el mismo nombre de un sector o la misma prioridad.
     * 
     * @param dto Objeto DTO del sector a comprobar.
     * @throws UpdateConflictException Excepción lanzada si el nombre o la prioridad
     *                                 de un sector coinciden con algún registro de
     *                                 la BBDD.
     */
    private void checkWhenSectorAttributesAlreadyUsed(SectorDto dto)
            throws AlreadyExistsException, UpdateConflictException {

        SectorEntity compareSector = this.sectorRepository.getByName(dto.getName());

        compareSectorGetId(dto, compareSector);

        compareSector = this.sectorRepository.getByPriority(dto.getPriority());

        compareSectorGetId(dto, compareSector);
    }

    /**
     * Método que compara el ID del registro que se está editando con el existente
     * en la base de datos.
     * 
     * @param dto           Registro que se está editando.
     * @param compareSector Registro de la base de datos.
     * 
     * @throws UpdateConflictException Excepción lanzada si hay error.
     */
    private void compareSectorGetId(SectorDto dto, SectorEntity compareSector) throws AlreadyExistsException {

        if ((compareSector != null) && (dto.getId() != compareSector.getId()))
            throw new AlreadyExistsException();
    }
}
