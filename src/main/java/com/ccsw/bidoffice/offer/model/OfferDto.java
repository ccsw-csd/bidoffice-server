package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.ccsw.bidoffice.common.converter.DateToLocalDateConverter;
import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusDto;
import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterDto;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;
import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectDto;
import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamDto;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyDto;
import com.ccsw.bidoffice.offering.model.OfferingDto;
import com.ccsw.bidoffice.offertracing.model.OfferTracingDto;
import com.ccsw.bidoffice.offertradetracking.model.OfferTradeTrackingDto;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.sector.model.SectorDto;
import com.ccsw.bidoffice.technology.model.TechnologyDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OfferDto {

    private Long id;

    private String client;

    private String name;

    private PersonDto requestedBy;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate requestedDate;

    private PersonDto managedBy;

    private String bdcCode;

    private SectorDto sector;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate goNogoDate;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDate deliveryDate;

    private OpportunityStatusDto opportunityStatus;

    private OpportunityTypeDto opportunityType;

    private Boolean opportunityWin;

    private String observations;

    private LocalDateTime lastModification;

    private LocalDate userLastUpdate;

    private OfferDataChapterDto dataChapter;

    private Set<OfferDataFileDto> dataFiles;

    private OfferDataProjectDto dataProject;

    private OfferDataTeamDto dataTeam;

    private OfferDataTechnologyDto dataTechnology;

    private List<OfferingDto> offerings;

    private List<PersonDto> teamPerson;

    private List<TechnologyDto> technologies;

    private Set<OfferTracingDto> tracings;

    private Set<OfferChangeStatusDto> changeStatus;

    private Set<OfferTradeTrackingDto> tradeTrackings;

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

    public PersonDto getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(PersonDto requestedBy) {
        this.requestedBy = requestedBy;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public PersonDto getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(PersonDto managedBy) {
        this.managedBy = managedBy;
    }

    public String getBdcCode() {
        return bdcCode;
    }

    public void setBdcCode(String bdcCode) {
        this.bdcCode = bdcCode;
    }

    public SectorDto getSector() {
        return sector;
    }

    public void setSector(SectorDto sector) {
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

    public OpportunityStatusDto getOpportunityStatus() {
        return opportunityStatus;
    }

    public void setOpportunityStatus(OpportunityStatusDto opportunityStatus) {
        this.opportunityStatus = opportunityStatus;
    }

    public OpportunityTypeDto getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(OpportunityTypeDto opportunityType) {
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

    public LocalDate getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setUserLastUpdate(LocalDate userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public Set<OfferChangeStatusDto> getChangeStatus() {
        return changeStatus;
    }

    public void setChangeStatus(Set<OfferChangeStatusDto> changeStatus) {
        this.changeStatus = changeStatus;
    }

    public OfferDataChapterDto getDataChapter() {
        return dataChapter;
    }

    public void setDataChapter(OfferDataChapterDto dataChapter) {
        this.dataChapter = dataChapter;
    }

    public Set<OfferDataFileDto> getDataFiles() {
        return dataFiles;
    }

    public void setDataFiles(Set<OfferDataFileDto> dataFiles) {
        this.dataFiles = dataFiles;
    }

    public OfferDataProjectDto getDataProject() {
        return dataProject;
    }

    public void setDataProject(OfferDataProjectDto dataProject) {
        this.dataProject = dataProject;
    }

    public OfferDataTeamDto getDataTeam() {
        return dataTeam;
    }

    public void setDataTeam(OfferDataTeamDto dataTeam) {
        this.dataTeam = dataTeam;
    }

    public OfferDataTechnologyDto getDataTechnology() {
        return dataTechnology;
    }

    public void setDataTechnology(OfferDataTechnologyDto dataTechnology) {
        this.dataTechnology = dataTechnology;
    }

    public List<OfferingDto> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<OfferingDto> offerings) {
        this.offerings = offerings;
    }

    public List<PersonDto> getTeamPerson() {
        return teamPerson;
    }

    public void setTeamPerson(List<PersonDto> teamPerson) {
        this.teamPerson = teamPerson;
    }

    public List<TechnologyDto> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyDto> technologies) {
        this.technologies = technologies;
    }

    public Set<OfferTracingDto> getTracings() {
        return tracings;
    }

    public void setTracings(Set<OfferTracingDto> tracings) {
        this.tracings = tracings;
    }

    public Set<OfferTradeTrackingDto> getTradeTrackings() {
        return tradeTrackings;
    }

    public void setTradeTrackings(Set<OfferTradeTrackingDto> tradeTrackings) {
        this.tradeTrackings = tradeTrackings;
    }
}
