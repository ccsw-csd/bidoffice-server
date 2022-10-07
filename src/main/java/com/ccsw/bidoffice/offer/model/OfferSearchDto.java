package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;

import com.ccsw.bidoffice.common.converter.DateToLocalDateConverter;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OfferSearchDto {

    private OpportunityStatusDto status;

    private OpportunityTypeDto type;

    private SectorDto sector;

    private PersonDto requestedBy;

    private PersonDto managedBy;

    private PersonDto involved;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate startDateModification;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate endDateModification;

    private Pageable pageable;

    public OpportunityStatusDto getStatus() {
        return status;
    }

    public void setStatus(OpportunityStatusDto status) {
        this.status = status;
    }

    public OpportunityTypeDto getType() {
        return type;
    }

    public void setType(OpportunityTypeDto type) {
        this.type = type;
    }

    public SectorDto getSector() {
        return sector;
    }

    public void setSector(SectorDto sector) {
        this.sector = sector;
    }

    public PersonDto getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(PersonDto requestedBy) {
        this.requestedBy = requestedBy;
    }

    public PersonDto getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(PersonDto managedBy) {
        this.managedBy = managedBy;
    }

    public PersonDto getInvolved() {
        return involved;
    }

    public void setInvolved(PersonDto involved) {
        this.involved = involved;
    }

    public LocalDate getStartDateModification() {
        return startDateModification;
    }

    public void setStartDateModification(LocalDate startDateModification) {
        this.startDateModification = startDateModification;
    }

    public LocalDate getEndDateModification() {
        return endDateModification;
    }

    public void setEndDateModification(LocalDate endDateModification) {
        this.endDateModification = endDateModification;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
