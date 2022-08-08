package com.ccsw.bidoffice.methodology;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;

@Service
public class MethodologyServiceImpl implements MethodologyService {

    @Autowired
    MethodologyRepository methodologyRepository;

    @Override
    public MethodologyEntity get(Long id) {
        return this.methodologyRepository.findById(id).orElse(null);
    }

    @Override
    public List<MethodologyEntity> findAllMethodologyOrderPriority() {

        return this.methodologyRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void save(Long id, MethodologyDto dto) throws AlreadyExistsException {
        MethodologyEntity methodology = null;

        if (id == null) {
            methodology = new MethodologyEntity();
        } else {
            methodology = this.get(id);
        }

        Boolean dupeName = this.methodologyRepository.existsByNameAndIdIsNot(dto.getName(), dto.getId());
        Boolean dupePriority = this.methodologyRepository.existsByPriorityAndIdIsNot(dto.getPriority(), dto.getId());

        if (dupeName || dupePriority) {
            throw new AlreadyExistsException();
        } else {
            methodology.setName(dto.getName());
            methodology.setPriority(dto.getPriority());
            this.methodologyRepository.save(methodology);
        }
    }
}
