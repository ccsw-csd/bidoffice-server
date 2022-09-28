package com.ccsw.bidoffice.offertracing.model;

import java.time.LocalDateTime;

import com.ccsw.bidoffice.common.converter.DateToLocalDateConverter;
import com.ccsw.bidoffice.person.model.PersonDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class OfferTracingDto {

    private Long id;

    private PersonDto person;

    private String comment;

    @JsonDeserialize(converter = DateToLocalDateConverter.class)
    private LocalDateTime date;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
