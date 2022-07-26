package com.ccsw.bidoffice.offertechnology.model;

import com.ccsw.bidoffice.technology.model.TechnologyDto;

public class OfferTechnologyDto {

    private Long id;

    private TechnologyDto technology;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TechnologyDto getTechnology() {
        return technology;
    }

    public void setTechnology(TechnologyDto technology) {
        this.technology = technology;
    }
}
