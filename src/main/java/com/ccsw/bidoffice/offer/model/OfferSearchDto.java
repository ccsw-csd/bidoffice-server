package com.ccsw.bidoffice.offer.model;

import org.springframework.data.domain.Pageable;

public class OfferSearchDto {

    private Pageable pageable;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
