package com.ccsw.bidoffice.offerdataproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_data_project")
public class OfferDataProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "offer_id", nullable = false)
    private Long offerId;

    @Column(name = "project_type_id", nullable = false)
    private Long projectTypeId;

    @Column(name = "project_type_id")
    private Double amount;

    @Column(name = "ftes")
    private Double ftes;

    @Column(name = "months")
    private Double months;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
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
