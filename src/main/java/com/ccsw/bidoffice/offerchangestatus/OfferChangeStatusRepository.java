package com.ccsw.bidoffice.offerchangestatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccsw.bidoffice.offerchangestatus.model.OfferChangeStatusEntity;

public interface OfferChangeStatusRepository extends JpaRepository<OfferChangeStatusEntity, Long>{

    List<OfferChangeStatusEntity> findByOfferId(Long id);
}
