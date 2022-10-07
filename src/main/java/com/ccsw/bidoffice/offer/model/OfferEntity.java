package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusEntity;
import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterEntity;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;
import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectEntity;
import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamEntity;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyEntity;
import com.ccsw.bidoffice.offering.model.OfferingEntity;
import com.ccsw.bidoffice.offertracing.model.OfferTracingEntity;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusEntity;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeEntity;
import com.ccsw.bidoffice.person.model.PersonEntity;
import com.ccsw.bidoffice.sector.model.SectorEntity;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@Entity
@Table(name = "offer")
public class OfferEntity {

    public static final String ATT_SECTOR = "sector";
    public static final String ATT_OPP_STATUS = "opportunityStatus";
    public static final String ATT_OPP_TYPE = "opportunityType";
    public static final String ATT_LAST_MODIFICATION = "lastModification";
    public static final String ATT_REQUESTED_BY = "requestedBy";
    public static final String ATT_MANAGED_BY = "managedBy";
    public static final String ATT_TEAM_PERSON = "teamPerson";

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

    @Column(name = "bdc_code")
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

    @Column(name = "last_modification", nullable = false)
    private LocalDateTime lastModification;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private OfferDataChapterEntity dataChapter;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<OfferDataFileEntity> dataFiles;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private OfferDataProjectEntity dataProject;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private OfferDataTeamEntity dataTeam;

    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private OfferDataTechnologyEntity dataTechnology;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "offer_offering", joinColumns = @JoinColumn(name = "offer_id"), inverseJoinColumns = @JoinColumn(name = "offering_id"))
    private List<OfferingEntity> offerings;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "offer_team_person", joinColumns = @JoinColumn(name = "offer_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<PersonEntity> teamPerson;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "offer_technology", joinColumns = @JoinColumn(name = "offer_id"), inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<TechnologyEntity> technologies;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<OfferTracingEntity> tracings;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<OfferChangeStatusEntity> changeStatus;

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

    public LocalDateTime getLastModification() {
        return lastModification;
    }

    public void setLastModification(LocalDateTime lastModification) {
        this.lastModification = lastModification;
    }

    public OfferDataChapterEntity getDataChapter() {
        return dataChapter;
    }

    public void setDataChapter(OfferDataChapterEntity dataChapter) {
        this.dataChapter = dataChapter;
        this.dataChapter.setOffer(this);
    }

    public Set<OfferDataFileEntity> getDataFiles() {
        return dataFiles;
    }

    public void setDataFiles(Set<OfferDataFileEntity> dataFiles) {
        this.dataFiles = dataFiles;
        this.dataFiles.stream().peek(item -> item.setOffer(this)).collect(Collectors.toSet());
    }

    public OfferDataProjectEntity getDataProject() {
        return dataProject;
    }

    public void setDataProject(OfferDataProjectEntity dataProject) {
        this.dataProject = dataProject;
        this.dataProject.setOffer(this);
    }

    public OfferDataTeamEntity getDataTeam() {
        return dataTeam;
    }

    public void setDataTeam(OfferDataTeamEntity dataTeam) {
        this.dataTeam = dataTeam;
        this.dataTeam.setOffer(this);
    }

    public OfferDataTechnologyEntity getDataTechnology() {
        return dataTechnology;
    }

    public void setDataTechnology(OfferDataTechnologyEntity dataTechnology) {
        this.dataTechnology = dataTechnology;
        this.dataTechnology.setOffer(this);
    }

    public List<OfferingEntity> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<OfferingEntity> offerings) {
        this.offerings = offerings;
    }

    public List<PersonEntity> getTeamPerson() {
        return teamPerson;
    }

    public void setTeamPerson(List<PersonEntity> teamPerson) {
        this.teamPerson = teamPerson;
    }

    public List<TechnologyEntity> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyEntity> technologies) {
        this.technologies = technologies;
    }

    public Set<OfferTracingEntity> getTracings() {
        return tracings;
    }

    public void setTracings(Set<OfferTracingEntity> tracings) {
        this.tracings = tracings;
        this.tracings.stream().peek(item -> item.setOffer(this)).collect(Collectors.toSet());
    }

    public Set<OfferChangeStatusEntity> getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Set<OfferChangeStatusEntity> changeStatus) {
        this.changeStatus = changeStatus;
        this.changeStatus.stream().peek(item -> item.setOffer(this)).collect(Collectors.toSet());
    }

}
