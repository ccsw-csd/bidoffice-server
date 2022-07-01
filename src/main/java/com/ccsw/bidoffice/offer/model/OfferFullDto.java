package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;

import com.ccsw.bidoffice.opportunity_status.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunity_type.model.OpportunityTypeDto;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.sector.model.SectorDto;

public class OfferFullDto {
    private Long id;

    private String client;

    private String name;

    private PersonDto requestedBy;

    private LocalDate requestedDate;

    private PersonDto managedBy;

    private String bdcCode;

    private SectorDto sector;

    private LocalDate goNogoDate;

    private LocalDate deliveryDate;

    private OpportunityStatusDto opportunityStatus;

    private OpportunityTypeDto opportunityType;

    private Integer opportunityWin;

    private String observations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonDto getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(PersonDto requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public PersonDto getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(PersonDto managedBy) {
        this.managedBy = managedBy;
    }

    public String getBdcCode() {
        return bdcCode;
    }

    public void setBdcCode(String bdcCode) {
        this.bdcCode = bdcCode;
    }

    public SectorDto getSector() {
        return sector;
    }

    public void setSector(SectorDto sector) {
        this.sector = sector;
    }

    public LocalDate getGoNogoDate() {
        return goNogoDate;
    }

    public void setGoNogoDate(LocalDate goNogoDate) {
        this.goNogoDate = goNogoDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OpportunityStatusDto getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(OpportunityStatusDto opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    public OpportunityTypeDto getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(OpportunityTypeDto opportunityType) {
        this.opportunityType = opportunityType;
    }

    public Integer getOpportunityWin() {
        return opportunityWin;
    }

    public void setOpportunityWin(Integer opportunityWin) {
        this.opportunityWin = opportunityWin;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
