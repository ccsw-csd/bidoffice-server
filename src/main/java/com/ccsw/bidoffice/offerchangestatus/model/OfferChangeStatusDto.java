package com.ccsw.bidoffice.offerchangestatus.model;

import java.time.LocalDate;

import com.ccsw.bidoffice.common.converter.DateToLocalDateConverter;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OfferChangeStatusDto {

    private Long id;

    private String username;

    private OpportunityStatusDto opportunityStatus;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OpportunityStatusDto getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(OpportunityStatusDto opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
