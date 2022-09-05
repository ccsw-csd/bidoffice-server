package com.ccsw.bidoffice.sector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.AlreadyExistsException;
import com.ccsw.bidoffice.offer.OfferService;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    private OfferService offerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SectorEntity> findAllSectorOrderPriority() {

        return this.sectorRepository.findAll(Sort.by(Sort.Direction.ASC, "priority"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws AlreadyExistsException {

        if (this.offerService.checkIfSectorIsUsingInOfferBySectorId(id)) {
            throw new AlreadyExistsException();
        }

        this.sectorRepository.deleteById(id);
    }

}
