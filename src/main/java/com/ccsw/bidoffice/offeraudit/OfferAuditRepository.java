package com.ccsw.bidoffice.offeraudit;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offeraudit.model.OfferAuditEntity;

public interface OfferAuditRepository extends CrudRepository<OfferAuditEntity, Long> {

}
