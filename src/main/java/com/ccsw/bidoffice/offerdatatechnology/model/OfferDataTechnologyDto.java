package com.ccsw.bidoffice.offerdatatechnology.model;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerDto;
import com.ccsw.bidoffice.methodology.model.MethodologyDto;

public class OfferDataTechnologyDto {

    private Long id;

    private HyperscalerDto hyperscaler;

    private MethodologyDto methodology;

    private String observations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HyperscalerDto getHyperscaler() {
        return hyperscaler;
    }

    public void setHyperscaler(HyperscalerDto hyperscaler) {
        this.hyperscaler = hyperscaler;
    }

    public MethodologyDto getMethodology() {
        return methodology;
    }

    public void setMethodology(MethodologyDto methodology) {
        this.methodology = methodology;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
