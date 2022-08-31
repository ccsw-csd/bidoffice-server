package com.ccsw.bidoffice.offerdatachapter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.offer.model.OfferEntity;

@Entity
@Table(name = "offer_data_chapter")
public class OfferDataChapterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @Column(name = "presentation", columnDefinition = "boolean default false")
    private Boolean presentation;

    @Column(name = "capabilities", columnDefinition = "boolean default false")
    private Boolean capabilities;

    @Column(name = "approach", columnDefinition = "boolean default false")
    private Boolean approach;

    @Column(name = "methodology", columnDefinition = "boolean default false")
    private Boolean methodology;

    @Column(name = "work_model", columnDefinition = "boolean default false")
    private Boolean workModel;

    @Column(name = "team", columnDefinition = "boolean default false")
    private Boolean team;

    @Column(name = "planning", columnDefinition = "boolean default false")
    private Boolean planning;

    @Column(name = "value_added", columnDefinition = "boolean default false")
    private Boolean valueAdded;

    @Column(name = "innovation", columnDefinition = "boolean default false")
    private Boolean innovation;

    @Column(name = "reference", columnDefinition = "boolean default false")
    private Boolean reference;

    @Column(name = "key_document", columnDefinition = "boolean default false")
    private Boolean keyDocument;

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

    public Boolean getPresentation() {
        return presentation;
    }

    public void setPresentation(Boolean presentation) {
        this.presentation = presentation;
    }

    public Boolean getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Boolean capabilities) {
        this.capabilities = capabilities;
    }

    public Boolean getApproach() {
        return approach;
    }

    public void setApproach(Boolean approach) {
        this.approach = approach;
    }

    public Boolean getMethodology() {
        return methodology;
    }

    public void setMethodology(Boolean methodology) {
        this.methodology = methodology;
    }

    public Boolean getWorkModel() {
        return workModel;
    }

    public void setWorkModel(Boolean workModel) {
        this.workModel = workModel;
    }

    public Boolean getTeam() {
        return team;
    }

    public void setTeam(Boolean team) {
        this.team = team;
    }

    public Boolean getPlanning() {
        return planning;
    }

    public void setPlanning(Boolean planning) {
        this.planning = planning;
    }

    public Boolean getValueAdded() {
        return valueAdded;
    }

    public void setValueAdded(Boolean valueAdded) {
        this.valueAdded = valueAdded;
    }

    public Boolean getInnovation() {
        return innovation;
    }

    public void setInnovation(Boolean innovation) {
        this.innovation = innovation;
    }

    public Boolean getReference() {
        return reference;
    }

    public void setReference(Boolean reference) {
        this.reference = reference;
    }

    public Boolean getKeyDocument() {
        return keyDocument;
    }

    public void setKeyDocument(Boolean keyDocument) {
        this.keyDocument = keyDocument;
    }
}
