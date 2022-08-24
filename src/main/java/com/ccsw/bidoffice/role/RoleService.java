package com.ccsw.bidoffice.role;

import com.ccsw.bidoffice.role.model.RoleEntity;

import java.util.List;

/**
 * @author ccsw
 *
 */
public interface RoleService {

	/**
	 * Recupera un listado de roles
	 * @return
	 */
	List<RoleEntity> getAllDataFromRole();

	/**
	 * Recupera rol con su name
	 * @param name
	 * @return
	 */
	RoleEntity getByName(String name);

	/**
	 * Recupera el rol con su id
	 * @param id
	 * @return
	 */
	RoleEntity getById(Long id);
}
