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

@Service
public class MethodologyServiceImpl implements MethodologyService {

    @Autowired
    MethodologyRepository methodologyRepository;

    @Override
    public MethodologyEntity get(Long id) throws EntityNotFoundException {
        return this.methodologyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<MethodologyEntity> findAllMethodologyOrderPriority() {

        return this.methodologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void save(MethodologyDto dto) throws AlreadyExistsException, EntityNotFoundException {
        MethodologyEntity methodology = null;

        Boolean dupeName = this.methodologyRepository.existsByNameAndIdIsNot(dto.getName(), dto.getId());
        Boolean dupePriority = this.methodologyRepository.existsByPriorityAndIdIsNot(dto.getPriority(), dto.getId());

        if (dupeName || dupePriority) {
            throw new AlreadyExistsException();
        } else {

            if (dto.getId() == null) {
                methodology = new MethodologyEntity();
            } else {
                methodology = this.get(dto.getId());
            }

            methodology.setName(dto.getName());
            methodology.setPriority(dto.getPriority());
            this.methodologyRepository.save(methodology);
        }
    }
}
