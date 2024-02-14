package com.ccsw.bidoffice.offer.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "offer_export_data")
public class OfferDataExportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "client")
    private String client;
    @Column(name = "opportunity_name")
    private String name;
    @Column(name = "observations")
    private String observations;
    @Column(name = "sector")
    private String sector;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    @Column(name = "requested_date")
    private LocalDate requestedDate;
    @Column(name = "requested_by")
    private String requestedBy;
    @Column(name = "opportunity_type")
    private String opportunityType;
    @Column(name = "modernization")
    private String modernization;
    @Column(name = "end_to_end")
    private String endToEnd;
    @Column(name = "devops")
    private String devops;
    @Column(name = "rpa")
    private String rpa;
    @Column(name = "project_office")
    private String projectOffice;
    @Column(name = "lowcode")
    private String lowcode;
    @Column(name = "integrations")
    private String integrations;
    @Column(name = "services")
    private String services;
    @Column(name = "migration")
    private String migration;
    @Column(name = "software_houses")
    private String softwareHouses;
    @Column(name = "architecture")
    private String architecture;
    @Column(name = "principal_technology")
    private String principalTechnology;
    @Column(name = "more_technology")
    private String moreTechnology;
    @Column(name = "cloud")
    private String cloud;
    @Column(name = "ia")
    private String ia;
    @Column(name = "agile")
    private String agile;
    @Column(name = "project_type")
    private String projectType;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "ftes")
    private Double ftes;
    @Column(name = "months")
    private Double months;
    @Column(name = "rate_card")
    private String rateCard;
    @Column(name = "presentation")
    private String presentation;
    @Column(name = "capabilities")
    private String capabilities;
    @Column(name = "approach")
    private String approach;
    @Column(name = "methodology")
    private String methodology;
    @Column(name = "work_model")
    private String workModel;
    @Column(name = "team")
    private String team;
    @Column(name = "planning")
    private String planning;
    @Column(name = "value_added")
    private String valueAdded;
    @Column(name = "innovation")
    private String innovation;
    @Column(name = "sostenibility")
    private String sostenibility;
    @Column(name = "reference")
    private String reference;
    @Column(name = "cca_leader")
    private String ccaLeader;
    @Column(name = "multitower")
    private String multitower;
    @Column(name = "practices")
    private String practices;
    @Column(name = "person")
    private String person;
    @Column(name = "win")
    private String win;
    @Column(name = "key_document")
    private String keyDocument;
    @Column(name = "comercial_reference")
    private String comercialReference;
    @Column(name = "comments")
    private String comments;

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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getOpportunityType() {
        return opportunityType;
    }

    public void setOpportunityType(String opportunityType) {
        this.opportunityType = opportunityType;
    }

    public String getModernization() {
        return modernization;
    }

    public void setModernization(String modernization) {
        this.modernization = modernization;
    }

    public String getEndToEnd() {
        return endToEnd;
    }

    public void setEndToEnd(String endToEnd) {
        this.endToEnd = endToEnd;
    }

    public String getDevops() {
        return devops;
    }

    public void setDevops(String devops) {
        this.devops = devops;
    }

    public String getRpa() {
        return rpa;
    }

    public void setRpa(String rpa) {
        this.rpa = rpa;
    }

    public String getProjectOffice() {
        return projectOffice;
    }

    public void setProjectOffice(String projectOffice) {
        this.projectOffice = projectOffice;
    }

    public String getLowcode() {
        return lowcode;
    }

    public void setLowcode(String lowcode) {
        this.lowcode = lowcode;
    }

    public String getIntegrations() {
        return integrations;
    }

    public void setIntegrations(String integrations) {
        this.integrations = integrations;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getMigration() {
        return migration;
    }

    public void setMigration(String migration) {
        this.migration = migration;
    }

    public String getSoftwareHouses() {
        return softwareHouses;
    }

    public void setSoftwareHouses(String softwareHouses) {
        this.softwareHouses = softwareHouses;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getPrincipalTechnology() {
        return principalTechnology;
    }

    public void setPrincipalTechnology(String principalTechnology) {
        this.principalTechnology = principalTechnology;
    }

    public String getMoreTechnology() {
        return moreTechnology;
    }

    public void setMoreTechnology(String moreTechnology) {
        this.moreTechnology = moreTechnology;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }

    public String getAgile() {
        return agile;
    }

    public void setAgile(String agile) {
        this.agile = agile;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
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

    public String getRateCard() {
        return rateCard;
    }

    public void setRateCard(String rateCard) {
        this.rateCard = rateCard;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getApproach() {
        return approach;
    }

    public void setApproach(String approach) {
        this.approach = approach;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getWorkModel() {
        return workModel;
    }

    public void setWorkModel(String workModel) {
        this.workModel = workModel;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public String getValueAdded() {
        return valueAdded;
    }

    public void setValueAdded(String valueAdded) {
        this.valueAdded = valueAdded;
    }

    public String getInnovation() {
        return innovation;
    }

    public void setInnovation(String innovation) {
        this.innovation = innovation;
    }

    public String getSostenibility() {
        return sostenibility;
    }

    public void setSostenibility(String sostenibility) {
        this.sostenibility = sostenibility;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCcaLeader() {
        return ccaLeader;
    }

    public void setCcaLeader(String ccaLeader) {
        this.ccaLeader = ccaLeader;
    }

    public String getMultitower() {
        return multitower;
    }

    public void setMultitower(String multitower) {
        this.multitower = multitower;
    }

    public String getPractices() {
        return practices;
    }

    public void setPractices(String practices) {
        this.practices = practices;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getKeyDocument() {
        return keyDocument;
    }

    public void setKeyDocument(String keyDocument) {
        this.keyDocument = keyDocument;
    }

    public String getComercialReference() {
        return comercialReference;
    }

    public void setComercialReference(String comercialReference) {
        this.comercialReference = comercialReference;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
