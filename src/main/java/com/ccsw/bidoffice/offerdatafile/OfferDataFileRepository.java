package com.ccsw.bidoffice.offerdatafile;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offerdatafile.model.OfferDataFileEntity;

public interface OfferDataFileRepository extends CrudRepository<OfferDataFileEntity, Long> {

    List<OfferDataFileEntity> findAllByFileTypeId(Long id);

}
