package com.ccsw.bidoffice.offer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ccsw.bidoffice.offer.model.Clients;
import com.ccsw.bidoffice.offer.model.OfferEntity;

public interface OfferRepository extends PagingAndSortingRepository<OfferEntity, Long> {

    @Override
    @EntityGraph(attributePaths = { "requestedBy", "managedBy", "sector", "opportunityStatus", "opportunityType",
            "dataChapter", "dataProject", "dataTeam", "dataTechnology", })
    Page<OfferEntity> findAll(Pageable pageable);

    List<Clients> findFirst15DistinctByClientIgnoreCaseContaining(String client);

    boolean existsByOpportunityTypeId(Long id);

    /**
     * Comprueba si un sector está siendo utilizado en al menos una oferta.
     * 
     * @param id Long identificador del sector a buscar.
     * 
     * @return boolean True si se está usando en al menos una oferta.
     */
    boolean existsBySectorId(Long id);

    List<OfferEntity> findBySectorId(Long id);

}
