package com.ccsw.bidoffice.offerdatatechnology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;
import com.ccsw.bidoffice.methodology.model.MethodologyEntity;
import com.ccsw.bidoffice.offer.model.OfferEntity;

@Entity
@Table(name = "offer_data_technology")
public class OfferDataTechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "hyperscaler_id")
    private HyperscalerEntity hyperscaler;

    @ManyToOne
    @JoinColumn(name = "methodology_id")
    private MethodologyEntity methodology;

    @Column(name = "observations")
    private String observations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

    public HyperscalerEntity getHyperscaler() {
        return hyperscaler;
    }

    public void setHyperscaler(HyperscalerEntity hyperscaler) {
        this.hyperscaler = hyperscaler;
    }

    public MethodologyEntity getMethodology() {
        return methodology;
    }

    public void setMethodology(MethodologyEntity methodology) {
        this.methodology = methodology;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
