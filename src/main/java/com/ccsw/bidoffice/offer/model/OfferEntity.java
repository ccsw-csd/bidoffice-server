package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterEntity;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;
import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectEntity;
import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamEntity;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;
import com.ccsw.bidoffice.offeroffering.model.OfferOfferingEntity;
import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonEntity;
import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyEntity;
import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by")
    private PersonEntity requestedBy;

    @Column(name = "requested_date", nullable = false)
    private LocalDate requestedDate;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_status_id", nullable = false)
    private OpportunityStatusEntity opportunityStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_type_id", nullable = false)
    private OpportunityTypeEntity opportunityType;

    @Column(name = "opportunity_win")
    private Boolean opportunityWin;

    @Column(name = "observations")
    private String observations;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OfferDataChapterEntity dataChapter;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OfferDataFileEntity> dataFiles;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OfferDataProjectEntity dataProject;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OfferDataTeamEntity dataTeam;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private OfferDataTechnologyEntity dataTechnology;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OfferOfferingEntity> offerings;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OfferTeamPersonEntity> teamPerson;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OfferTechnologyEntity> technologies;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OfferTracingEntity> tracings;

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

    public PersonEntity getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(PersonEntity requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public PersonEntity getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(PersonEntity managedBy) {
        this.managedBy = managedBy;
    }

    public String getBdcCode() {
        return bdcCode;
    }

    public void setBdcCode(String bdcCode) {
        this.bdcCode = bdcCode;
    }

    public SectorEntity getSector() {
        return sector;
    }

    public void setSector(SectorEntity sector) {
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

    public OfferDataChapterEntity getDataChapter() {
        return dataChapter;
    }

    public void setDataChapter(OfferDataChapterEntity dataChapter) {
        this.dataChapter = dataChapter;
    }

    public Set<OfferDataFileEntity> getDataFiles() {
        return dataFiles;
    }

    public void setDataFiles(Set<OfferDataFileEntity> dataFiles) {
        this.dataFiles = dataFiles;
    }

    public OfferDataProjectEntity getDataProject() {
        return dataProject;
    }

    public void setDataProject(OfferDataProjectEntity dataProject) {
        this.dataProject = dataProject;
    }

    public OfferDataTeamEntity getDataTeam() {
        return dataTeam;
    }

    public void setDataTeam(OfferDataTeamEntity dataTeam) {
        this.dataTeam = dataTeam;
    }

    public OfferDataTechnologyEntity getDataTechnology() {
        return dataTechnology;
    }

    public void setDataTechnology(OfferDataTechnologyEntity dataTechnology) {
        this.dataTechnology = dataTechnology;
    }

    public Set<OfferOfferingEntity> getOfferings() {
        return offerings;
    }

    public void setOfferings(Set<OfferOfferingEntity> offerings) {
        this.offerings = offerings;
    }

    public Set<OfferTeamPersonEntity> getTeamPerson() {
        return teamPerson;
    }

    public void setTeamPerson(Set<OfferTeamPersonEntity> teamPerson) {
        this.teamPerson = teamPerson;
    }

    public Set<OfferTechnologyEntity> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<OfferTechnologyEntity> technologies) {
        this.technologies = technologies;
    }

    public Set<OfferTracingEntity> getTracings() {
        return tracings;
    }

    public void setTracings(Set<OfferTracingEntity> tracings) {
        this.tracings = tracings;
    }
}
