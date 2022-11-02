package com.ccsw.bidoffice.offerdatafile.model;

import com.ccsw.bidoffice.filetype.model.FileTypeDto;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentDto;

public class OfferDataFileDto {

    private Long id;

    private String name;

    private FileTypeDto fileType;

    private FormatDocumentDto formatDocument;

    private String link;

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

    public FileTypeDto getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeDto fileType) {
        this.fileType = fileType;
    }

    public FormatDocumentDto getFormatDocument() {
        return formatDocument;
    }

    public void setFormatDocument(FormatDocumentDto formatDocument) {
        this.formatDocument = formatDocument;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
