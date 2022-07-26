package com.ccsw.bidoffice.offerteamperson.model;

import com.ccsw.bidoffice.person.model.PersonDto;

public class OfferTeamPersonDto {

    private Long id;

    private PersonDto person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }
}
