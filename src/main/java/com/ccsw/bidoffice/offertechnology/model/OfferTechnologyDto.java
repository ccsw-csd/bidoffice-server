package com.ccsw.bidoffice.offertechnology.model;

import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.technology.model.TechnologyDto;

public class OfferTechnologyDto {

    private Long id;

    private OfferDto offer;

    private TechnologyDto technology;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferDto getOfferEntity() {
        return offer;
    }

    public void setOffer(OfferDto offer) {
        this.offer = offer;
    }

    public TechnologyDto getTechnology() {
        return technology;
    }

    public void setTechnology(TechnologyDto technology) {
        this.technology = technology;
    }
}
