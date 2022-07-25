package com.ccsw.bidoffice.offertracing.model;

import java.time.LocalDate;

import com.ccsw.bidoffice.person.model.PersonDto;

public class OfferTracingDto {

    private Long id;

    private PersonDto person;

    private String comment;

    private LocalDate date;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
