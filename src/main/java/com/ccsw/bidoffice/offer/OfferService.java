package com.ccsw.bidoffice.offer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.InvalidDataException;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;

public interface OfferService {

    OfferEntity getOffer(Long id) throws EntityNotFoundException;

    List<String> findFirst15DistinctClientLikeFilter(String filter);

    Page<OfferEntity> findPage(OfferSearchDto dto);

    OfferEntity save(OfferDto dto) throws InvalidDataException, EntityNotFoundException;

    boolean checkIfExistsOffer(Long id);

    /**
     * Comprueba si el código de un Sector está siendo utilizado en algún Offer.
     * 
     * @param id Long identificador del sector a buscar.
     * 
     * @return boolean true si el sector está siendo utilizado en alguna oferta.
     */
    boolean checkIfSectorIsUsingInOfferBySectorId(Long id);

}
