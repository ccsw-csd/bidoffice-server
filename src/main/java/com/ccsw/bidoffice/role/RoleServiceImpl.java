package com.ccsw.bidoffice.role;

import com.ccsw.bidoffice.role.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoleEntity> getAllDataFromRole() {

		return (List<RoleEntity>) this.roleRepository.findAll();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RoleEntity getByName(String name) {
		
		return this.roleRepository.getByName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RoleEntity getById(Long id) {
		
		return this.roleRepository.getById(id);
	}

}
