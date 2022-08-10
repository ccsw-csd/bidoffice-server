package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.offerdatatechnology.OfferDataTechnologyService;

@Service
@Transactional
public class HyperscalerServiceImpl implements HyperscalerService {

    @Autowired
    HyperscalerRepository hyperscalerRepository;

    @Autowired
    OfferDataTechnologyService offerDataTechnologyService;

    @Override
    public List<HyperscalerEntity> getAllDataFromHyperscaler() {

        return (List<HyperscalerEntity>) this.hyperscalerRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    @Override
    public void delete(Long id) throws AlreadyExistsException {
        if (this.offerDataTechnologyService.checkExistsByHyperscalerId(id))
            throw new AlreadyExistsException();

        this.hyperscalerRepository.deleteById(id);
    }

    private boolean checkIfExistsPriority(HyperscalerDto dto) {
        return this.hyperscalerRepository.existsByPriorityAndIdIsNot(dto.getPriority(), dto.getId());
    }

    private boolean checkIfExistsNewPriority(HyperscalerDto dto) {
        return this.hyperscalerRepository.existsByPriority(dto.getPriority());
    }

    private boolean checkIfExistsName(HyperscalerDto dto) {
        return this.hyperscalerRepository.existsByNameAndIdIsNot(dto.getName(), dto.getId());
    }

    private boolean checkIfExistsNewName(HyperscalerDto dto) {
        return this.hyperscalerRepository.existsByName(dto.getName());
    }

    @Override
    public HyperscalerEntity getById(Long id) {
        return this.hyperscalerRepository.findById(id).orElse(null);
    }

    private void checkWhenAttributesAreWrong(HyperscalerDto dto) throws AlreadyExistsException {
        boolean nameExists = false, priorityExists = false;

        if (dto.getId() == null) {
            nameExists = checkIfExistsNewName(dto);
            priorityExists = checkIfExistsNewPriority(dto);
        } else {
            nameExists = checkIfExistsName(dto);
            priorityExists = checkIfExistsPriority(dto);
        }

        if (nameExists || priorityExists)
            throw new AlreadyExistsException();

    }

    @Override
    public void saveItem(HyperscalerDto hyperscalerDto) throws AlreadyExistsException {
        HyperscalerEntity hyperscalerEntity = null;

        checkWhenAttributesAreWrong(hyperscalerDto);

        if (hyperscalerDto.getId() != null) {
            hyperscalerEntity = getById(hyperscalerDto.getId());

        } else {
            hyperscalerEntity = new HyperscalerEntity();
        }

        hyperscalerEntity.setName(hyperscalerDto.getName());
        hyperscalerEntity.setPriority(hyperscalerDto.getPriority());

        this.hyperscalerRepository.save(hyperscalerEntity);
    }
}
