package com.ccsw.bidoffice.offerdatateam.model;

import com.ccsw.bidoffice.offer.model.OfferDto;

public class OfferDataTeamDto {

    private Long id;

    private OfferDto offer;

    private Boolean cca;

    private Boolean multitower;

    private String practices;

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

    public Boolean getCca() {
        return cca;
    }

    public void setCca(Boolean cca) {
        this.cca = cca;
    }

    public Boolean getMultitower() {
        return multitower;
    }

    public void setMultitower(Boolean multitower) {
        this.multitower = multitower;
    }

    public String getPractices() {
        return practices;
    }

    public void setPractices(String practices) {
        this.practices = practices;
    }
}
