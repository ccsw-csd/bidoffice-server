package com.ccsw.bidoffice.offerdataproject.model;

import com.ccsw.bidoffice.offer.model.OfferEntity;

public class OfferDataProjectDto {

    private Long id;

    private OfferEntity offerEntity;

    private Long projectTypeId;

    private Double amount;

    private Double ftes;

    private Double months;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferEntity getOfferEntity() {
        return offerEntity;
    }

    public void setOfferEntity(OfferEntity offerEntity) {
        this.offerEntity = offerEntity;
    }

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFtes() {
        return ftes;
    }

    public void setFtes(Double ftes) {
        this.ftes = ftes;
    }

    public Double getMonths() {
        return months;
    }

    public void setMonths(Double months) {
        this.months = months;
    }
}
