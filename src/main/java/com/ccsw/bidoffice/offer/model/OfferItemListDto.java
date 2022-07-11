package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;

import com.ccsw.bidoffice.common.model.BaseClassDto;

public class OfferItemListDto {

    private Long id;

    private String client;

    private String name;

    private BaseClassDto sector;

    private LocalDate requestedDate;

    private BaseClassDto opportunityType;

    private BaseClassDto opportunityStatus;

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

    public BaseClassDto getSector() {
        return sector;
    }

    public void setSector(BaseClassDto sector) {
        this.sector = sector;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public BaseClassDto getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(BaseClassDto opportunityType) {
        this.opportunityType = opportunityType;
    }

    public BaseClassDto getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(BaseClassDto opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }
}
