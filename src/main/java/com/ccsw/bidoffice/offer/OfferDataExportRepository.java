package com.ccsw.bidoffice.offer;

import com.ccsw.bidoffice.offer.model.OfferDataExportEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferDataExportRepository extends CrudRepository<OfferDataExportEntity, Long> {

	List<OfferDataExportEntity> findAll();
}
