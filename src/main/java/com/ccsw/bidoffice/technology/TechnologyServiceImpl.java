package com.ccsw.bidoffice.technology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offertechnology.OfferTechnologyService;
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
}
