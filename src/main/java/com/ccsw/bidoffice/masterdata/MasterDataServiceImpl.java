package com.ccsw.bidoffice.masterdata;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.bidoffice.common.criteria.SearchCriteria;
import com.ccsw.bidoffice.file_type.FileTypeRepository;
import com.ccsw.bidoffice.file_type.model.FileTypeEntity;
import com.ccsw.bidoffice.hyperscaler.HyperscalerRepository;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.methodology.MethodologyRepository;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offer.OfferRepository;
import com.ccsw.bidoffice.offer.model.ClientsOnly;
import com.ccsw.bidoffice.offering.OfferingRepository;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.opportunity_status.OpportunityStatusRepository;
import com.ccsw.bidoffice.opportunity_status.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunity_type.OpportunityTypeRepository;
import com.ccsw.bidoffice.opportunity_type.model.OpportunityTypeEntity;
import com.ccsw.bidoffice.person.PersonRepository;
import com.ccsw.bidoffice.person.PersonSpecification;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.person.model.PersonSearchDto;
import com.ccsw.bidoffice.project_type.ProjectTypeRepository;
import com.ccsw.bidoffice.project_type.model.ProjectTypeEntity;
import com.ccsw.bidoffice.sector.SectorRepository;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@Service
public class MasterDataServiceImpl implements MasterDataService {

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private OpportunityTypeRepository opportunityTypeRepository;

    @Autowired
    private OpportunityStatusRepository opportunityStatusRepository;

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private HyperscalerRepository hyperscalerRepository;

    @Autowired
    private MethodologyRepository methodologyRepository;

    @Autowired
    private OfferingRepository offeringRepository;

    @Autowired
    private ProjectTypeRepository projectTypeRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<SectorEntity> sectors() {

        return this.sectorRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<OpportunityTypeEntity> types() {

        return this.opportunityTypeRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<OpportunityStatusEntity> status() {

        return this.opportunityStatusRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<FileTypeEntity> fileTypes() {

        return this.fileTypeRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<HyperscalerEntity> hyperscalers() {

        return this.hyperscalerRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<MethodologyEntity> methodologys() {

        return this.methodologyRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<OfferingEntity> offerings() {

        return this.offeringRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<ProjectTypeEntity> projectTypes() {

        return this.projectTypeRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public Page<PersonEntity> persons(PersonSearchDto personSearchDto) {

        PersonSpecification username = new PersonSpecification(
                new SearchCriteria("username", ":", personSearchDto.getUsername()));

        PersonSpecification name = new PersonSpecification(new SearchCriteria("name", ":", personSearchDto.getName()));

        PersonSpecification lastname = new PersonSpecification(
                new SearchCriteria("lastname", ":", personSearchDto.getLastname()));

        PersonSpecification active = new PersonSpecification(new SearchCriteria("active", ":", true));

        Specification<PersonEntity> specification = Specification.where(username).and(name).and(lastname).and(active);

        return this.personRepository.findAll(specification, PageRequest.of(0, 15));
    }

    @Override
    public List<String> clients(String filter) {

        return this.offerRepository.findFirst15DistinctByClientIgnoreCaseContaining(filter).stream()
                .map(ClientsOnly::getClient).collect(Collectors.toList());
    }
}
