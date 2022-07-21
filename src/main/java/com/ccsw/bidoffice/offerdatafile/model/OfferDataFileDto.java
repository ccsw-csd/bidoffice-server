package com.ccsw.bidoffice.offerdatafile.model;

import com.ccsw.bidoffice.filetype.model.FileTypeDto;
import com.ccsw.bidoffice.offer.model.OfferDto;

public class OfferDataFileDto {

    private Long id;

    private OfferDto offer;

    private String name;

    private FileTypeDto fileType;

    private String link;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferDto getOffer() {
        return offer;
    }

    public void setOffer(OfferDto offer) {
        this.offer = offer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileTypeDto getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeDto fileType) {
        this.fileType = fileType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
