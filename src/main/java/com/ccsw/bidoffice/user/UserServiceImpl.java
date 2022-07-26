package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.common.criteria.TernarySearchCriteria;
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
                new TernarySearchCriteria("username", null, null, ":", userSearchDto.getUsername())
        );

        UserSpecification firstnameLastname = new UserSpecification(
                new TernarySearchCriteria("firstName", "lastName", null, "concat :", userSearchDto.getName())
        );

        UserSpecification lastnameFirstname = new UserSpecification(
                new TernarySearchCriteria("lastName", "firstName", null, "concat :", userSearchDto.getName())
        );

        Specification<UserEntity> specification = Specification.where(username).and(firstnameLastname.or(lastnameFirstname));

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

        UserSpecification firstnameLastnameUsername = new UserSpecification(
                new TernarySearchCriteria("firstName", "lastName", "username", "concat concat :", filter)
        );

        Specification<UserEntity> specification = Specification.where(firstnameLastnameUsername);

        return this.userRepository.findAll(specification, PageRequest.of(0, 15)).getContent();
    }

}
