package com.ccsw.bidoffice.offering.model;

public class OfferingDto {

    private Long id;

    private String name;

    private Integer priority;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer l) {
        this.priority = l;
    }
}
