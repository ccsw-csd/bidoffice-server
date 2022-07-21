package com.ccsw.bidoffice.offerdatachapter.model;

import com.ccsw.bidoffice.offer.model.OfferDto;

public class OfferDataChapterDto {

    private Long id;

    private OfferDto offer;

    private Boolean presentation;

    private Boolean capabilities;

    private Boolean approach;

    private Boolean methodology;

    private Boolean workModel;

    private Boolean team;

    private Boolean planning;

    private Boolean valueAdded;

    private Boolean innovation;

    private Boolean references;

    private Boolean keyDocument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferDto getOffer() {
        return offer;
    }

    public void setOffer(OfferDto offer) {
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

    public Boolean getReferences() {
        return references;
    }

    public void setReferences(Boolean references) {
        this.references = references;
    }

    public Boolean getKeyDocument() {
        return keyDocument;
    }

    public void setKeyDocument(Boolean keyDocument) {
        this.keyDocument = keyDocument;
    }
}
