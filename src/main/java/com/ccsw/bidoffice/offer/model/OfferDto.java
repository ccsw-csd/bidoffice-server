package com.ccsw.bidoffice.offer.model;

import java.time.LocalDate;
import java.util.Set;

import com.ccsw.bidoffice.offerdatachapter.model.OfferDataChapterDto;
import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileDto;
import com.ccsw.bidoffice.offerdataproject.model.OfferDataProjectDto;
import com.ccsw.bidoffice.offerdatateam.model.OfferDataTeamDto;
import com.ccsw.bidoffice.offerdatatechnology.model.OfferDataTechnologyDto;
import com.ccsw.bidoffice.offeroffering.model.OfferOfferingDto;
import com.ccsw.bidoffice.offerteamperson.model.OfferTeamPersonDto;
import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyDto;
import com.ccsw.bidoffice.offertracing.model.OfferTracingDto;
import com.ccsw.bidoffice.opportunitystatus.model.OpportunityStatusDto;
import com.ccsw.bidoffice.opportunitytype.model.OpportunityTypeDto;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.ccsw.bidoffice.sector.model.SectorDto;

public class OfferDto {

    private Long id;

    private String client;

    private String name;

    private PersonDto requestedBy;

    private LocalDate requestedDate;

    private PersonDto managedBy;

    private String bdcCode;

    private SectorDto sector;

    private LocalDate goNogoDate;

    private LocalDate deliveryDate;

    private OpportunityStatusDto opportunityStatus;

    private OpportunityTypeDto opportunityType;

    private Boolean opportunityWin;

    private String observations;

    private OfferDataChapterDto offerDataChapter;

    private Set<OfferDataFileDto> offerDataFiles;

    private OfferDataProjectDto offerDataProject;

    private OfferDataTeamDto offerDataTeam;

    private OfferDataTechnologyDto offerDataTechnology;

    private Set<OfferOfferingDto> offerOfferings;

    private Set<OfferTeamPersonDto> offerTeamPersons;

    private Set<OfferTechnologyDto> offerTechnologys;

    private Set<OfferTracingDto> offerTracings;

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

    public OfferDataChapterDto getOfferDataChapter() {
        return offerDataChapter;
    }

    public void setOfferDataChapter(OfferDataChapterDto offerDataChapter) {
        this.offerDataChapter = offerDataChapter;
    }

    public Set<OfferDataFileDto> getOfferDataFiles() {
        return offerDataFiles;
    }

    public void setOfferDataFiles(Set<OfferDataFileDto> offerDataFiles) {
        this.offerDataFiles = offerDataFiles;
    }

    public OfferDataProjectDto getOfferDataProject() {
        return offerDataProject;
    }

    public void setOfferDataProject(OfferDataProjectDto offerDataProject) {
        this.offerDataProject = offerDataProject;
    }

    public OfferDataTeamDto getOfferDataTeam() {
        return offerDataTeam;
    }

    public void setOfferDataTeam(OfferDataTeamDto offerDataTeam) {
        this.offerDataTeam = offerDataTeam;
    }

    public OfferDataTechnologyDto getOfferDataTechnology() {
        return offerDataTechnology;
    }

    public void setOfferDataTechnology(OfferDataTechnologyDto offerDataTechnology) {
        this.offerDataTechnology = offerDataTechnology;
    }

    public Set<OfferOfferingDto> getOfferOfferings() {
        return offerOfferings;
    }

    public void setOfferOfferings(Set<OfferOfferingDto> offerOfferings) {
        this.offerOfferings = offerOfferings;
    }

    public Set<OfferTeamPersonDto> getOfferTeamPersons() {
        return offerTeamPersons;
    }

    public void setOfferTeamPersons(Set<OfferTeamPersonDto> offerTeamPersons) {
        this.offerTeamPersons = offerTeamPersons;
    }

    public Set<OfferTechnologyDto> getOfferTechnologys() {
        return offerTechnologys;
    }

    public void setOfferTechnologys(Set<OfferTechnologyDto> offerTechnologys) {
        this.offerTechnologys = offerTechnologys;
    }

    public Set<OfferTracingDto> getOfferTracings() {
        return offerTracings;
    }

    public void setOfferTracings(Set<OfferTracingDto> offerTracings) {
        this.offerTracings = offerTracings;
    }
}
