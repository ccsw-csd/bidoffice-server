package com.ccsw.bidoffice.role;

import com.ccsw.bidoffice.role.model.RoleEntity;

/**
 * @author ccsw
 *
 */
public interface RoleService {

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
