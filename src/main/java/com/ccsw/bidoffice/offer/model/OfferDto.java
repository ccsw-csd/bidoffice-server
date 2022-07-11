package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;

import com.ccsw.bidoffice.common.model.BaseClassDto;
import com.ccsw.bidoffice.person.model.PersonDto;

public class OfferDto {

    private Long id;

    private String client;

    private String name;

    private PersonDto requestedBy;

    private LocalDate requestedDate;

    private PersonDto managedBy;

    private String bdcCode;

    private BaseClassDto sector;

    private LocalDate goNogoDate;

    private LocalDate deliveryDate;

    private BaseClassDto opportunityStatus;

    private BaseClassDto opportunityType;

    private Boolean opportunityWin;

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

    public BaseClassDto getSector() {
        return sector;
    }

    public void setSector(BaseClassDto sector) {
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

    public BaseClassDto getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(BaseClassDto opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    public BaseClassDto getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(BaseClassDto opportunityType) {
        this.opportunityType = opportunityType;
    }

    public Boolean getOpportunityWin() {
        return opportunityWin;
    }

    public void setOpportunityWin(Boolean opportunityWin) {
        this.opportunityWin = opportunityWin;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
