package com.ccsw.bidoffice.user;


import com.ccsw.bidoffice.config.security.UserInfoAppDto;
import com.ccsw.bidoffice.role.RoleRepository;
import com.ccsw.bidoffice.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserEntity getByUsername(String username) {

        return this.userRepository.getByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserInfoAppDto dto) {

        UserEntity user = new UserEntity();

        user.setRole(this.roleRepository.getByName(dto.getRole()));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getMail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());

        this.userRepository.save(user);
    }

    @Override
    public List<UserEntity> findByFilter(String filter) {

        return this.userRepository.findUsersLikeFilter(filter, PageRequest.of(0, 15));
    }

}
