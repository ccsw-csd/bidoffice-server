package com.ccsw.bidoffice.sector.model;

import java.time.LocalDate;

public class SectorDto {

    private Long id;

    private String name;

    private Long priority;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Devuelve si un Sector se considera activo o no, en función de si la fecha de
     * hoy está comprendida entre la fecha de inicio y la fecha final.
     * 
     * @return boolean True si el elemento es activo.
     */
    public boolean getActive() {

        LocalDate toDay = LocalDate.now();

        if ((toDay.isAfter(startDate)) && (toDay.isBefore(endDate))) {

            return true;

        } else {

            return false;
        }

    }

}
