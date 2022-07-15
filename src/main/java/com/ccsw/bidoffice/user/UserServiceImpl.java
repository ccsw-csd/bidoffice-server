package com.ccsw.bidoffice.user;


import com.ccsw.bidoffice.common.criteria.SearchCriteria;
import com.ccsw.bidoffice.config.security.UserInfoAppDto;
import com.ccsw.bidoffice.role.RoleRepository;
import com.ccsw.bidoffice.user.model.UserEntity;
import com.ccsw.bidoffice.user.model.UserSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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
    public Page<UserEntity> findPage(UserSearchDto userSearchDto) {

        UserSpecification username = new UserSpecification(
                new SearchCriteria("username", ":", userSearchDto.getUsername())
        );

        UserSpecification firstName = new UserSpecification(
                new SearchCriteria("first_name", ":", userSearchDto.getFirstName())
        );

        UserSpecification lastName = new UserSpecification(
                new SearchCriteria("last_name", ":", userSearchDto.getLastName())
        );

        Specification<UserEntity> specification = Specification.where(username).and(firstName).and(lastName);

        return this.userRepository.findAll(specification,userSearchDto.getPageable());
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
