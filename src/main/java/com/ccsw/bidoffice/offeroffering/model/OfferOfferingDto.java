package com.ccsw.bidoffice.offeroffering.model;

import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offering.model.OfferingDto;

public class OfferOfferingDto {

    private Long id;

    private OfferDto offer;

    private OfferingDto offering;

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

    public OfferingDto getOffering() {
        return offering;
    }

    public void setOffering(OfferingDto offering) {
        this.offering = offering;
    }
}
