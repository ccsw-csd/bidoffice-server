package com.ccsw.bidoffice.offerdatafile.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ccsw.bidoffice.filetype.model.FileTypeEntity;
import com.ccsw.bidoffice.formatdocument.model.FormatDocumentEntity;
import com.ccsw.bidoffice.offer.model.OfferEntity;

@Entity
@Table(name = "offer_data_files")
public class OfferDataFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "file_type_id", nullable = false)
    private FileTypeEntity fileType;

    @ManyToOne
    @JoinColumn(name = "format_document_id", nullable = false)
    private FormatDocumentEntity formatDocument;

    @Column(name = "link", nullable = false)
    private String link;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileTypeEntity getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeEntity fileType) {
        this.fileType = fileType;
    }

    public FormatDocumentEntity getFormatDocument() {
        return formatDocument;
    }

    public void setFormatDocument(FormatDocumentEntity formatDocument) {
        this.formatDocument = formatDocument;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
