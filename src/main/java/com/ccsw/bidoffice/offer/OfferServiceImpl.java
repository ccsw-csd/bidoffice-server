package com.ccsw.bidoffice.offer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.criteria.BinarySearchCriteria;
import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.InvalidDataException;
import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.config.security.UserUtils;
import com.ccsw.bidoffice.offer.model.Clients;
import com.ccsw.bidoffice.offer.model.ModifyStatusDto;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;
import com.ccsw.bidoffice.offerchangestatus.enums.StatusEnum;
import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusDto;
import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusEntity;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;
import com.ccsw.bidoffice.offertracing.model.OfferTracingDto;
import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;
import com.ccsw.bidoffice.person.PersonService;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private PersonService personService;

    private OfferEntity offerEntity;

    @Override
    public OfferEntity getOffer(Long id) throws EntityNotFoundException {

        return this.offerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<String> findFirst15DistinctClientLikeFilter(String filter) {

        return this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(filter).stream().map(Clients::getClient).collect(Collectors.toList());
    }

    @Override
    public Page<OfferEntity> findPage(OfferSearchDto dto) throws Exception {

        boolean isAdmin = UserUtils.hasRole("ADMIN");

        OfferSpecification status = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_OPP_STATUS, "isMember", this.beanMapper.mapList(dto.getStatus(), OpportunityStatusEntity.class)));

        OfferSpecification type = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_OPP_TYPE, ":", this.beanMapper.map(dto.getType(), OpportunityTypeEntity.class)));

        OfferSpecification sector = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_SECTOR, ":", this.beanMapper.map(dto.getSector(), SectorEntity.class)));

        OfferSpecification deliveryDate = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_DELIVERY_DATE, "between", dto.getDeliveryDateStart(), dto.getDeliveryDateEnd()));

        OfferSpecification requestdBy = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_REQUESTED_BY, ":", this.beanMapper.map(dto.getRequestedBy(), PersonEntity.class)));

        OfferSpecification managedBy = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_MANAGED_BY, ":", this.beanMapper.map(dto.getManagedBy(), PersonEntity.class)));

        OfferSpecification client = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_CLIENT, "like", this.beanMapper.map(dto.getClient(), String.class)));

        OfferSpecification involvedRequestdBy = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_REQUESTED_BY, ":", this.beanMapper.map(dto.getInvolved(), PersonEntity.class)));

        OfferSpecification involvedManagedBy = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_MANAGED_BY, ":", this.beanMapper.map(dto.getInvolved(), PersonEntity.class)));

        OfferSpecification involvedTeamPerson = new OfferSpecification(new BinarySearchCriteria(OfferEntity.ATT_TEAM_PERSON, ":", this.beanMapper.map(dto.getInvolved(), PersonEntity.class)));

        Specification<OfferEntity> specification = Specification.where(client).and(type).and(sector).and(deliveryDate).and(managedBy).and(requestdBy).and(status).and(involvedRequestdBy.or(involvedManagedBy).or(involvedTeamPerson));

        return this.offerRepository.findAll(specification, dto.getPageable());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkIfDateOfAllOfferAreInRange(SectorDto dto) {

        boolean result = false;
        LocalDate endDay = LocalDate.of(2999, 12, 31);

        if (dto.getEndDate() != null)
            endDay = dto.getEndDate();

        if ((dto.getStartDate() == null) && (dto.getEndDate() == null)) {

            result = false;
        } else {

            int offers = this.offerRepository.countBySectorId(dto.getId());
            int offersInRange = this.offerRepository.countByRequestedDateBetween(dto.getStartDate(), endDay);

            if (offers > offersInRange)
                result = true;
        }

        return result;

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

        return dataFileDto.getFileType() == null || isNullOrEmpty(dataFileDto.getName()) || isNullOrEmpty(dataFileDto.getLink());
    }

    private Boolean validateTracing(OfferTracingDto tracingDto) {

        return isNullOrEmpty(tracingDto.getComment()) || tracingDto.getDate() == null || tracingDto.getPerson() == null;
    }

    private OfferEntity saveNewOffer(OfferDto dto) throws EntityNotFoundException {

        offerEntity = new OfferEntity();

        offerEntity = this.beanMapper.map(dto, OfferEntity.class);
        offerEntity.setLastModification(LocalDateTime.now());
        offerEntity.setCreationDate(LocalDate.now());
        offerEntity.setUserLastUpdate(this.personService.findPersonByUsername(UserUtils.getUserDetails().getUsername()));

        return this.offerRepository.save(offerEntity);
    }

    private OfferEntity modifyOffer(OfferDto dto) throws EntityNotFoundException {

        if (!this.offerRepository.existsById(dto.getId()))
            throw new EntityNotFoundException();

        OfferEntity offerEntity = this.beanMapper.map(dto, OfferEntity.class);
        offerEntity.setChangeStatus(this.getOffer(dto.getId()).getChangeStatus());
        offerEntity.setLastModification(LocalDateTime.now());
        offerEntity.setUserLastUpdate(this.personService.findPersonByUsername(UserUtils.getUserDetails().getUsername()));

        return this.offerRepository.save(offerEntity);
    }

    @Override
    public OfferEntity modifyStatus(ModifyStatusDto dto) throws InvalidDataException, EntityNotFoundException {

        offerEntity = this.getOffer(dto.getId());

        if (!StatusEnum.isValidChangeStatus(offerEntity.getOpportunityStatus().getName(), dto.getOpportunityStatus().getName())) {
            throw new InvalidDataException();
        }

        this.mappingStatusDtoToOfferEntity(dto);
        offerEntity.setLastModification(LocalDateTime.now());

        return this.offerRepository.save(offerEntity);
    }

    private void mappingStatusDtoToOfferEntity(ModifyStatusDto dto) throws EntityNotFoundException {

        this.mappingChangeStatusDtoToEntity(dto.getChangeStatus());

        if (dto.getTracing() != null)
            this.mappingTrcingDtoToEntity(dto.getTracing(), dto.getChangeStatus().getUsername());

        if (dto.getDeliveryDate() != null)
            offerEntity.setDeliveryDate(dto.getDeliveryDate());

        if (dto.getGoNogoDate() != null)
            offerEntity.setGoNogoDate(dto.getGoNogoDate());

        if (dto.getWin() != null)
            offerEntity.setOpportunityWin(dto.getWin());
    }

    private void mappingTrcingDtoToEntity(OfferTracingDto dto, String username) throws EntityNotFoundException {
        OfferTracingEntity tracingEntity = new OfferTracingEntity();
        BeanUtils.copyProperties(dto, tracingEntity);

        if (tracingEntity.getComment() != null) {
            tracingEntity.setPerson(this.personService.findPersonByUsername(username));
            tracingEntity.setOffer(offerEntity);
            if (offerEntity.getTracings() != null)
                offerEntity.getTracings().add(tracingEntity);
            else {
                Set<OfferTracingEntity> tracings = new HashSet<>(Arrays.asList(tracingEntity));
                offerEntity.setTracings(tracings);
            }
        }
    }

    private void mappingChangeStatusDtoToEntity(OfferChangeStatusDto dto) {
        OfferChangeStatusEntity changeStatusEntity = new OfferChangeStatusEntity();
        OpportunityStatusEntity opportunityStatusEntity = new OpportunityStatusEntity();

        BeanUtils.copyProperties(dto, changeStatusEntity);
        BeanUtils.copyProperties(dto.getOpportunityStatus(), opportunityStatusEntity);
        changeStatusEntity.setOpportunityStatus(opportunityStatusEntity);
        changeStatusEntity.setOffer(offerEntity);
        offerEntity.setOpportunityStatus(opportunityStatusEntity);
        if (offerEntity.getChangeStatus() != null) {
            offerEntity.getChangeStatus().add(changeStatusEntity);
        } else {
            Set<OfferChangeStatusEntity> changes = new HashSet<>(Arrays.asList(changeStatusEntity));
            offerEntity.setChangeStatus(changes);
        }
    }
}
