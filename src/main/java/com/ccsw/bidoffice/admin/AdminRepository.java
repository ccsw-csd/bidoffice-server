package com.ccsw.bidoffice.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ccsw.bidoffice.admin.model.HyperscalerEntity;

public interface AdminRepository extends CrudRepository<HyperscalerEntity, Long> {

    @Query("select h from HyperscalerEntity h order by priority ")
    List<HyperscalerEntity> findDataFromHyperscaler();

}
