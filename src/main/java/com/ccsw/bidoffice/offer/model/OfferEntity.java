package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.opportunity_status.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunity_type.model.OpportunityTypeEntity;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.sector.model.SectorEntity;

@Entity
@Table(name = "offer")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "client", nullable = false)
    private String client;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "requested_by")
    private PersonEntity requestedBy;

    @Column(name = "requested_date", nullable = false)
    private LocalDate requestedDate;

    @ManyToOne
    @JoinColumn(name = "managed_by")
    private PersonEntity managedBy;

    @Column(name = "bdc_code", nullable = false)
    private String bdcCode;

    @ManyToOne
    @JoinColumn(name = "sector_id", nullable = false)
    private SectorEntity sector;

    @Column(name = "go_nogo_date")
    private LocalDate goNogoDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "opportunity_status_id", nullable = false)
    private OpportunityStatusEntity opportunityStatus;

    @ManyToOne
    @JoinColumn(name = "opportunity_type_id", nullable = false)
    private OpportunityTypeEntity opportunityType;

    @Column(name = "opportunity_win")
    private Integer opportunity_win;

    @Column(name = "observations")
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

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public SectorEntity getSector() {
        return sector;
    }

    public void setSector(SectorEntity sector) {
        this.sector = sector;
    }

    public OpportunityStatusEntity getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(OpportunityStatusEntity opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    public OpportunityTypeEntity getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(OpportunityTypeEntity opportunityType) {
        this.opportunityType = opportunityType;
    }
}
