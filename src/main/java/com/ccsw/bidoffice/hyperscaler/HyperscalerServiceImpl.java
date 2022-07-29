package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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
    public void deleteItemFromHyperscaler(Long id) throws AlreadyExistsException {
        if (this.offerDataTechnologyService.checkExistsByHyperscalerId(id))
            throw new AlreadyExistsException();

        this.hyperscalerRepository.deleteById(id);
    }

    @Override
    public void saveItem(Long id, HyperscalerDto hyperscalerDto) {

        HyperscalerEntity hyperscalerEntity = null;

        if (id != null) {
            this.hyperscalerRepository.findById(id).orElse(null);
        } else {
            hyperscalerEntity = new HyperscalerEntity();
        }

        BeanUtils.copyProperties(hyperscalerDto, hyperscalerEntity, "id");

        this.hyperscalerRepository.save(hyperscalerEntity);

    }

}
