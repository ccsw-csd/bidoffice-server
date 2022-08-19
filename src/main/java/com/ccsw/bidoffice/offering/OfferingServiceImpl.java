package com.ccsw.bidoffice.offering;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.offering.model.OfferingDto;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.offeroffering.OfferOfferingService;

@Service
public class OfferingServiceImpl implements OfferingService {

    @Autowired
    OfferingRepository offeringRepository;
    
    @Autowired
    OfferOfferingService offerOfferingService;

    @Override
    public List<OfferingEntity> findAllOfferingOrderPriority() {

        return this.offeringRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }
    
    @Override
    public void save(OfferingDto offeringDto) throws AlreadyExistsException, EntityNotFoundException {
    	OfferingEntity offeringEntity = null;
        
    	checkWhenAttributesAreWrong(offeringDto);

        if (offeringDto.getId() == null) {
        	offeringEntity = new OfferingEntity();
        } else {
            offeringEntity = getById(offeringDto.getId());
        }

        offeringEntity.setName(offeringDto.getName());
        offeringEntity.setPriority(offeringDto.getPriority());

        this.offeringRepository.save(offeringEntity);
    }
    
    @Override
    public void delete(Long id) throws AlreadyExistsException {
        if (this.offerOfferingService.checkExistsByOfferingId(id))
            throw new AlreadyExistsException();

        this.offeringRepository.deleteById(id);
    }

    @Override
    public OfferingEntity getById(Long id) throws EntityNotFoundException {
        return this.offeringRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void checkWhenAttributesAreWrong(OfferingDto dto) throws AlreadyExistsException {
        Boolean nameExists = false;
        Boolean priorityExists = false;

        if (dto.getId() == null) {
            nameExists = this.offeringRepository.existsByName(dto.getName());
            priorityExists = this.offeringRepository.existsByPriority(dto.getPriority());
        } else {
            nameExists = this.offeringRepository.existsByIdIsNotAndName(dto.getId(), dto.getName());
            priorityExists = this.offeringRepository.existsByIdIsNotAndPriority(dto.getId(), dto.getPriority());
        }

        if (nameExists || priorityExists)
            throw new AlreadyExistsException();

    }

}
