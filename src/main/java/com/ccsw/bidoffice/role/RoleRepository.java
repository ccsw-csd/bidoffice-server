package com.ccsw.bidoffice.role;

import com.ccsw.bidoffice.role.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author ccsw
 *
 */
public interface RoleRepository extends CrudRepository<RoleEntity, Long>{

	/**
	 * Recupera un role por su name
	 * @param name
	 * @return
	 */
	RoleEntity getByName(String name);
	
	/**
	 * Recupera un role por su id
	 * @param id
	 * @return
	 */
	RoleEntity getById(Long id);
}
