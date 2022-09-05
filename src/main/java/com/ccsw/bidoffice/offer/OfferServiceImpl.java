package com.ccsw.bidoffice.offer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.InvalidDataException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.offer.model.Clients;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;
import com.ccsw.bidoffice.offertracing.model.OfferTracingDto;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private BeanMapper beanMapper;

    private OfferEntity offerEntity;

    @Override
    public OfferEntity getOffer(Long id) throws EntityNotFoundException {

        return this.offerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<String> findFirst15DistinctClientLikeFilter(String filter) {

        return this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(filter).stream()
                .map(Clients::getClient).collect(Collectors.toList());
    }

    @Override
    public Page<OfferEntity> findPage(OfferSearchDto dto) {

        return this.offerRepository.findAll(dto.getPageable());
    }

    @Override
    public OfferEntity save(OfferDto dto) throws InvalidDataException, EntityNotFoundException {

        OfferEntity offerEntity = null;

        if (!isValidOffer(dto))
            throw new InvalidDataException();

        if (dto.getId() != null)
            offerEntity = modifyOffer(dto);

        else
            offerEntity = saveNewOffer(dto);

        return offerEntity;
    }

    @Override
    public boolean checkIfExistsOffer(Long id) {
        return this.offerRepository.existsByOpportunityTypeId(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkIfSectorIsUsingInOfferBySectorId(Long id) {

        return this.offerRepository.existsBySectorId(id);
    }

    private Boolean isValidOffer(OfferDto dto) {

        if (isNullOrEmpty(dto.getName()) || isNullOrEmpty(dto.getClient()) || dto.getRequestedDate() == null)
            return false;

        if (dto.getSector() == null || dto.getOpportunityType() == null || dto.getOpportunityStatus() == null)
            return false;

        if (dto.getTracings() != null && dto.getTracings().stream().anyMatch(item -> validateTracing(item)))
            return false;

        if (dto.getDataFiles() != null && dto.getDataFiles().stream().anyMatch(item -> validateDataFiles(item)))
            return false;

        return true;
    }

    private Boolean isNullOrEmpty(String item) {

        return item == null || item.equals("");
    }

    private Boolean validateDataFiles(OfferDataFileDto dataFileDto) {

        return dataFileDto.getFileType() == null || isNullOrEmpty(dataFileDto.getName())
                || isNullOrEmpty(dataFileDto.getLink());
    }

    private Boolean validateTracing(OfferTracingDto tracingDto) {

        return isNullOrEmpty(tracingDto.getComment()) || tracingDto.getDate() == null || tracingDto.getPerson() == null;
    }

    private OfferEntity saveNewOffer(OfferDto dto) {

        offerEntity = new OfferEntity();

        offerEntity = this.beanMapper.map(dto, OfferEntity.class);
        this.mappingEntitiesOffer();

        return this.offerRepository.save(offerEntity);
    }

    private OfferEntity modifyOffer(OfferDto dto) throws EntityNotFoundException {

        offerEntity = getOffer(dto.getId());

        offerEntity = this.beanMapper.map(dto, OfferEntity.class);
        this.mappingEntitiesOffer();

        return this.offerRepository.save(offerEntity);
    }

    private void mappingEntitiesOffer() {

        if (offerEntity.getDataChapter() != null)
            offerEntity.getDataChapter().setOffer(offerEntity);

        if (offerEntity.getDataFiles() != null)
            offerEntity.setDataFiles(offerEntity.getDataFiles().stream().peek(item -> item.setOffer(offerEntity))
                    .collect(Collectors.toSet()));

        if (offerEntity.getDataProject() != null)
            offerEntity.getDataProject().setOffer(offerEntity);

        if (offerEntity.getDataTeam() != null)
            offerEntity.getDataTeam().setOffer(offerEntity);

        if (offerEntity.getDataTechnology() != null)
            offerEntity.getDataTechnology().setOffer(offerEntity);

        if (offerEntity.getOfferings() != null)
            offerEntity.setOfferings(offerEntity.getOfferings().stream().peek(item -> item.setOffer(offerEntity))
                    .collect(Collectors.toSet()));

        if (offerEntity.getTeamPerson() != null)
            offerEntity.setTeamPerson(offerEntity.getTeamPerson().stream().peek(item -> item.setOffer(offerEntity))
                    .collect(Collectors.toSet()));

        if (offerEntity.getTechnologies() != null)
            offerEntity.setTechnologies(offerEntity.getTechnologies().stream().peek(item -> item.setOffer(offerEntity))
                    .collect(Collectors.toSet()));

        if (offerEntity.getTracings() != null)
            offerEntity.setTracings(offerEntity.getTracings().stream().peek(item -> item.setOffer(offerEntity))
                    .collect(Collectors.toSet()));
    }

}
