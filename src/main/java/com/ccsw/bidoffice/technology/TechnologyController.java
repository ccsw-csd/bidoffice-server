package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.technology.model.TechnologyDto;

@RequestMapping(value = "/technology")
@RestController
@CrossOrigin(origins = "*")
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private BeanMapper beanMapper;

    /**
     * Obtiene un listado de las tecnologías existentes en la base de datos.
     * 
     * @return List conteniendo todas las tecnologías.
     */
    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<TechnologyDto> findAllTechnologyOrderPriority() {

        return this.beanMapper.mapList(this.technologyService.findAllOrderByPriority(), TechnologyDto.class);

    }

    /**
     * Guarda o modifica una tecnología en la base de datos.
     * 
     * @param dto Objeto DTO de la tecnología a guardar o editar.
     * 
     * @throws AlreadyExistsException  Excepción si la tecnología o su prioridad ya
     *                                 existen.
     * @throws EntityNotFoundException Excepción si al modificar la tecnología, no
     *                                 existe.
     */
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public void save(@RequestBody TechnologyDto dto) throws AlreadyExistsException, EntityNotFoundException {

        this.technologyService.saveTechnology(dto);

    }

    /**
     * Borra una tecnología de la base de datos.
     * 
     * @param id Identificador tipo Long de la tecnología a borrar.
     * 
     * @throws AlreadyExistsException En caso de que la tecnología se utilice en al
     *                                menos una oferta.
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws AlreadyExistsException {

        this.technologyService.delete(id);

    }

}
