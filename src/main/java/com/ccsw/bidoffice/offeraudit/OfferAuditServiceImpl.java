package com.ccsw.bidoffice.offeraudit;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccsw.bidoffice.offeraudit.model.OfferAuditEntity;

public class OfferAuditServiceImpl implements OfferAuditService {

    @Autowired
    private OfferAuditRepository offerAuditRepository;

    @Override
    public OfferAuditEntity getOfferAudit(Long id) {

        return this.offerAuditRepository.findById(id).orElse(null);
    }

}
