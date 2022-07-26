package com.ccsw.bidoffice.offertechnology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.technology.model.TechnologyEntity;

@Entity
@Table(name = "offer_technology")
public class OfferTechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "technology_id", nullable = false)
    private TechnologyEntity technology;

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

    public TechnologyEntity getTechnology() {
        return technology;
    }

    public void setTechnology(TechnologyEntity technology) {
        this.technology = technology;
    }
}
