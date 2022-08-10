package com.ccsw.bidoffice.methodology;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyService;

@Service
public class MethodologyServiceImpl implements MethodologyService {

    @Autowired
    MethodologyRepository methodologyRepository;

    @Autowired
    OfferDataTechnologyService offerDataService;

    @Override
    public List<MethodologyEntity> findAllMethodologyOrderPriority() {

        return this.methodologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {
        if (this.offerDataService.checkIfExistsByMethodologyId(id))
            throw new AlreadyExistsException();

        this.methodologyRepository.deleteById(id);
    }

}
