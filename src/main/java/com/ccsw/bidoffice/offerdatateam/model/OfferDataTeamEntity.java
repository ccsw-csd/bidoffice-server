package com.ccsw.bidoffice.offerdatateam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_data_team")
public class OfferDataTeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "offer_id", nullable = false)
    private Long offerId;

    @Column(name = "cca")
    private Boolean cca;

    @Column(name = "multitower")
    private Boolean multitower;

    @Column(name = "multitower")
    private String practices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Boolean getCca() {
        return cca;
    }

    public void setCca(Boolean cca) {
        this.cca = cca;
    }

    public Boolean getMultitower() {
        return multitower;
    }

    public void setMultitower(Boolean multitower) {
        this.multitower = multitower;
    }

    public String getPractices() {
        return practices;
    }

    public void setPractices(String practices) {
        this.practices = practices;
    }
}