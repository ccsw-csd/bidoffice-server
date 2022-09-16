package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.UpdateConflictException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.sector.model.SectorDto;

@RequestMapping(value = "/sector")
@RestController
@CrossOrigin(origins = "*")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @Autowired
    private BeanMapper beanMapper;

    /**
     * Obtiene un listado de los sectores existentes en la base de datos.
     * 
     * @return List conteniendo todas los sectores.
     */
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<SectorDto> findAllSectorOrderPriority() {

        return this.beanMapper.mapList(this.sectorService.findAllSectorOrderPriority(), SectorDto.class);
    }

    /**
     * Guarda o modifica un sector de la base de datos.
     * 
     * @param dto Objeto DTO del sector a modificar.
     * 
     * @throws AlreadyExistsException  Excepción lanzada si el nombre o prioridad ya
     *                                 existen.
     * @throws EntityNotFoundException Excepción lanzada si el objeto Entity no es
     *                                 encontrado.
     * @throws UpdateConflictException Excepción lanzada si la fecha de creación de
     *                                 oferta está fuera del rango de fechas del
     *                                 sector.
     */
    @ResponseBody
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void save(@RequestBody SectorDto dto)
            throws AlreadyExistsException, EntityNotFoundException, UpdateConflictException {

        this.sectorService.saveSector(dto);
    }

    /**
     * Borra un sector de la base de datos.
     * 
     * @param id Identificador tipo Long del sector a borrar.
     * 
     * @throws AlreadyExistsException En caso de que el sector se utilice en al
     *                                menos una oferta.
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws AlreadyExistsException {

        this.sectorService.delete(id);

    }

}
