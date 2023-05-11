package com.ccsw.bidoffice.offer.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ccsw.bidoffice.common.converter.DateToLocalDateTimeConverter;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OfferSearchDto {

    private List<OpportunityStatusDto> status;

    private OpportunityTypeDto type;

    private SectorDto sector;

    private PersonDto requestedBy;

    private PersonDto managedBy;

    private PersonDto involved;

    private String client;

    @JsonDeserialize(converter = DateToLocalDateTimeConverter.class)
    private LocalDateTime startDateModification;

    @JsonDeserialize(converter = DateToLocalDateTimeConverter.class)
    private LocalDateTime endDateModification;

    private Pageable pageable;

    public OpportunityTypeDto getType() {
        return type;
    }

    public void setType(OpportunityTypeDto type) {
        this.type = type;
    }

    public SectorDto getSector() {
        return sector;
    }

    public List<OpportunityStatusDto> getStatus() {
        return status;
    }

    public void setStatus(List<OpportunityStatusDto> status) {
        this.status = status;
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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public LocalDateTime getStartDateModification() {
        return startDateModification;
    }

    public void setStartDateModification(LocalDateTime startDateModification) {
        this.startDateModification = startDateModification;
    }

    public LocalDateTime getEndDateModification() {
        return endDateModification;
    }

    public void setEndDateModification(LocalDateTime endDateModification) {
        this.endDateModification = endDateModification;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
