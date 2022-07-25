package com.ccsw.bidoffice.offeroffering.model;

import com.ccsw.bidoffice.offering.model.OfferingDto;

public class OfferOfferingDto {

    private Long id;

    private OfferingDto offering;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferingDto getOffering() {
        return offering;
    }

    public void setOffering(OfferingDto offering) {
        this.offering = offering;
    }
}
