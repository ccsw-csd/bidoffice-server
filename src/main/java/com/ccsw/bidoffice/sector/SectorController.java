package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
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
