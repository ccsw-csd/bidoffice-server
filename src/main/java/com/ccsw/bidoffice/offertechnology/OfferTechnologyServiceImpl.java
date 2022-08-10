package com.ccsw.bidoffice.offertechnology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OfferTechnologyServiceImpl implements OfferTechnologyService {

    @Autowired
    OfferTechnologyRepository offerTechnologyRepository;

    /**
     * Devuelve si una tecnología está siendo utilizada en una oferta, no siendo
     * posible borrarla si se utiliza en una o más ofertas.
     * 
     * @param id Identificador de la tecnología a buscar.
     * @return boolean true si la tecnología está siendo utilizada en alguna oferta.
     */
    @Override
    public boolean checkExistsByTechnologyId(Long id) {

        return this.offerTechnologyRepository.existsByTechnologyId(id);
    }

}
