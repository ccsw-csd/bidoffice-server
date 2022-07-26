package com.ccsw.bidoffice.offerdataproject.model;

import com.ccsw.bidoffice.projecttype.model.ProjectTypeDto;

public class OfferDataProjectDto {

    private Long id;

    private ProjectTypeDto projectType;

    private Double amount;

    private Double ftes;

    private Double months;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectTypeDto getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectTypeDto projectType) {
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
}
