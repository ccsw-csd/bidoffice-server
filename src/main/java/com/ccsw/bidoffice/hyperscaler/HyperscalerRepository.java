package com.ccsw.bidoffice.hyperscaler;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.bidoffice.hyperscaler.model.HyperscalerEntity;

public interface HyperscalerRepository extends CrudRepository<HyperscalerEntity, Long> {

    List<HyperscalerEntity> findAll(Sort sort);

    @Query("select odt from offer_data_technology odt")
    List<HyperscalerEntity> getDataFromOfferDataTechnology(@Param("idHyperscaler") Long id);

}
