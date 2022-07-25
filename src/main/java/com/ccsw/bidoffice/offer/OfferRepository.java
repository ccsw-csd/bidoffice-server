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
            "offerDataChapter", "offerDataFiles", "offerDataProject", "offerDataTeam", "offerDataTechnology",
            "offerOfferings", "offerTeamPersons", "offerTechnologys", "offerTracings" })
    Page<OfferEntity> findAll(Pageable pageable);

    List<Clients> findFirst15DistinctByClientIgnoreCaseContaining(String client);

}
