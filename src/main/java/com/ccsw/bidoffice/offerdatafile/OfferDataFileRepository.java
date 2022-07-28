package com.ccsw.bidoffice.offerdatafile;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

public interface OfferDataFileRepository extends CrudRepository<OfferDataFileEntity, Long> {

    boolean existsByFileTypeId(Long id);

}
