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

    @Override
    public void saveItem(HyperscalerDto hyperscalerDto) throws AlreadyExistsException {

        HyperscalerEntity hyperscalerEntity = null;

        if (hyperscalerDto.getId() != null) {
            hyperscalerEntity = this.hyperscalerRepository.findById(hyperscalerDto.getId()).orElse(null);
            if (hyperscalerEntity.getName() == hyperscalerDto.getName()) {
                if (this.hyperscalerRepository.existsByPriority(hyperscalerDto.getPriority()))
                    throw new AlreadyExistsException();

            } else if (hyperscalerEntity.getPriority() == hyperscalerDto.getPriority()) {
                if (this.hyperscalerRepository.existsByName(hyperscalerDto.getName()))
                    throw new AlreadyExistsException();
            } else {
                if (this.hyperscalerRepository.existsByPriority(hyperscalerDto.getPriority())
                        || this.hyperscalerRepository.existsByName(hyperscalerDto.getName()))
                    throw new AlreadyExistsException();
            }
        } else {
            hyperscalerEntity = new HyperscalerEntity();
            if (this.hyperscalerRepository.existsByPriority(hyperscalerDto.getPriority())
                    || this.hyperscalerRepository.existsByName(hyperscalerDto.getName()))
                throw new AlreadyExistsException();
        }

        hyperscalerEntity.setName(hyperscalerDto.getName());
        hyperscalerEntity.setPriority(hyperscalerDto.getPriority());

        this.hyperscalerRepository.save(hyperscalerEntity);

    }

}
