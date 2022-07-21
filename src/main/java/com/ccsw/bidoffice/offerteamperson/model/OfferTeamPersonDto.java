package com.ccsw.bidoffice.offerteamperson.model;

import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.person.model.PersonDto;

public class OfferTeamPersonDto {

    private Long id;

    private OfferDto offer;

    private PersonDto person;

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

    public PersonDto getPerson() {
        return person;
    }

    public void setPersonEntity(PersonDto person) {
        this.person = person;
    }
}
