package com.ccsw.bidoffice.offer;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.common.exception.InvalidDataException;
import com.ccsw.bidoffice.offer.model.ModifyStatusDto;
import com.ccsw.bidoffice.offer.model.OfferDto;
import com.ccsw.bidoffice.offer.model.OfferEntity;
import com.ccsw.bidoffice.offer.model.OfferSearchDto;
import com.ccsw.bidoffice.sector.model.SectorDto;

public interface OfferService {

    OfferEntity getOffer(Long id) throws EntityNotFoundException;

    List<String> findFirst15DistinctClientLikeFilter(String filter);

    Page<OfferEntity> findPage(OfferSearchDto dto) throws Exception;

	List<OfferEntity> findListToExport(OfferSearchDto dto) throws Exception;

	OfferEntity save(OfferDto dto) throws InvalidDataException, EntityNotFoundException;

    boolean checkIfExistsOffer(Long id);

    OfferEntity modifyStatus(ModifyStatusDto dto) throws InvalidDataException, EntityNotFoundException;

    /**
     * Comprueba si el código de un Sector está siendo utilizado en algún Offer.
     * 
     * @param id Long identificador del sector a buscar.
     * 
     * @return boolean true si el sector está siendo utilizado en alguna oferta.
     */
    boolean checkIfSectorIsUsingInOfferBySectorId(Long id);

    /**
     * Comprueba que la fecha de creación de oferta está dentro del rango de fechas.
     * 
     * Se cuenta el total de ofertas por el ID de sector a consultar, y después se
     * obtiene una consulta con las ofertas cuya fecha de creación está entre la
     * fecha de inicio y la fecha de final de un sector.
     * 
     * Si existen MENOS resultados en la segunda consulta, significa que hay al
     * menos una oferta cuyo requestedDate está fuera del rango de fechas de inicio
     * y final de un sector.
     * 
     * @param dto Objeto Dto de las fechas a comprobar.
     * @return boolean true si hay fechas fuera de rango.
     */
    boolean checkIfDateOfAllOfferAreInRange(SectorDto dto);
}
