package com.ccsw.bidoffice.offertechnology;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.offertechnology.model.OfferTechnologyEntity;

public interface OfferTechnologyRepository extends CrudRepository<OfferTechnologyEntity, Long> {

    /**
     * Devuelve si una tecnología está siendo utilizada en una oferta, no siendo
     * posible borrarla si se utiliza en una o más ofertas.
     * 
     * @param id Identificador de la tecnología a buscar.
     * @return boolean true si la tecnología está siendo utilizada en alguna oferta.
     */
    boolean existsByTechnologyId(Long id);

}
