package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.ccsw.bidoffice.common.converter.DateToLocalDateConverter;
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

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate deliveryDateStart;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate deliveryDateEnd;

    private Boolean genAi;

    private Boolean opportunityWin;

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

    public LocalDate getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public LocalDate getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateStart(LocalDate deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public void setDeliveryDateEnd(LocalDate deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public Boolean getGenAi() {
        return genAi;
    }

    public void setGenAi(Boolean genAi) {
        this.genAi = genAi;
    }

    public Boolean getOpportunityWin() {
        return opportunityWin;
    }

    public void setOpportunityWin(Boolean opportunityWin) {
        this.opportunityWin = opportunityWin;
    }
}
