package com.ccsw.bidoffice.offering.model;

public class OfferingDto {

    private Long id;

    private String name;

    private long priority;

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

    public long getPriority() {
        return priority;
    }

    public void setPriority(long l) {
        this.priority = l;
    }
}
