package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offerdatafile.OfferDataFileService;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    TechnologyRepository technologyRepository;

    @Autowired
    private OfferDataFileService offerDataFileService;

    /**
     * Devuelve todas las tecnologías de la base de datos en un listado, ordenadas
     * por prioridad.
     */
    @Override
    public List<TechnologyEntity> findAllOrderByPriority() {

        return (List<TechnologyEntity>) this.technologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));

    }

    /**
     * Borra una tecnología de la base de datos.
     * 
     * @param id Long Id de la tecnología a borrar.
     * 
     * @throws AlreadyExistsException Excepción lanzada si esta tecnología está
     *                                siendo utilizada en alguna oferta.
     */
    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerDataFileService.checkExistsByFileTypeId(id))
            throw new AlreadyExistsException();

        this.technologyRepository.deleteById(id);
    }
}
