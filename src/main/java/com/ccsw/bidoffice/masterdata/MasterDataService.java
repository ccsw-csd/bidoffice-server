package com.ccsw.bidoffice.masterdata;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.file_type.model.FileTypeEntity;
import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.opportunity_status.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunity_type.model.OpportunityTypeEntity;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.person.model.PersonSearchDto;
import com.ccsw.bidoffice.project_type.model.ProjectTypeEntity;
import com.ccsw.bidoffice.sector.model.SectorEntity;

public interface MasterDataService {

    List<SectorEntity> sectors();

    List<OpportunityTypeEntity> types();

    List<OpportunityStatusEntity> status();

    List<FileTypeEntity> fileTypes();

    List<HyperscalerEntity> hyperscalers();

    List<MethodologyEntity> methodologys();

    List<OfferingEntity> offerings();

    List<ProjectTypeEntity> projectTypes();

    Page<PersonEntity> persons(PersonSearchDto personSearchDto);

    List<String> clients(String filter);

}
