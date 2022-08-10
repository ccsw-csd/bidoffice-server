package com.ccsw.bidoffice.offertechnology;

public interface OfferTechnologyService {

    /**
     * Devuelve si una tecnología está siendo utilizada en una oferta, no siendo
     * posible borrarla si se utiliza en una o más ofertas.
     * 
     * @param Long id Identificador de la tecnología a buscar.
     * @return boolean true si la tecnología está siendo utilizada en alguna oferta.
     */
    public boolean checkExistsByTechnologyId(Long id);
}
