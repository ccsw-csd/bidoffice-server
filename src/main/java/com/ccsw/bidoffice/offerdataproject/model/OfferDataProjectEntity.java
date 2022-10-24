package com.ccsw.bidoffice.offerdataproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.projecttype.model.ProjectTypeEntity;

@Entity
@Table(name = "offer_data_project")
public class OfferDataProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "project_type_id", nullable = false)
    private ProjectTypeEntity projectType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "ftes")
    private Double ftes;

    @Column(name = "months")
    private Double months;

    @Column(name = "contribution_margin")
    private Double contributionMargin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public ProjectTypeEntity getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectTypeEntity projectType) {
        this.projectType = projectType;
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

    public Double getContributionMargin() {
        return contributionMargin;
    }

    public void setContributionMargin(Double contributionMargin) {
        this.contributionMargin = contributionMargin;
    }
}
