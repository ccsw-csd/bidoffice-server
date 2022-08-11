package com.ccsw.bidoffice.methodology;

import java.util.ArrayList;
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

    private void checkIfValuesAreDuped(MethodologyDto dto) throws AlreadyExistsException {
        Boolean dupeName, dupePriority;

        if (dto.getId() != null) {
            dupeName = this.methodologyRepository.existsByIdIsNotAndName(dto.getId(), dto.getName());
            dupePriority = this.methodologyRepository.existsByIdIsNotAndPriority(dto.getId(), dto.getPriority());
        } else {
            dupeName = this.methodologyRepository.existsByName(dto.getName());
            dupePriority = this.methodologyRepository.existsByPriority(dto.getPriority());
        }

        if (dupeName || dupePriority) {
            throw new AlreadyExistsException();
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
