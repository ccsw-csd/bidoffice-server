package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;

import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusDto;
import com.ccsw.bidoffice.offertracing.model.OfferTracingDto;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;

public class ModifyStatusDto {

    private Long id;

    private LocalDate goNogoDate;

    private LocalDate deliveryDate;

    private OfferChangeStatusDto changeStatus;

    private OfferTracingDto tracing;

    private OpportunityStatusDto opportunityStatus;

    private Boolean win;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OfferChangeStatusDto getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(OfferChangeStatusDto changeStatus) {
        this.changeStatus = changeStatus;
    }

    public OfferTracingDto getTracing() {
        return tracing;
    }

    public void setTracing(OfferTracingDto tracing) {
        this.tracing = tracing;
    }

    public OpportunityStatusDto getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(OpportunityStatusDto opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }
}
