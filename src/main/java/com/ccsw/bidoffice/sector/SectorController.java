package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
