package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.config.security.UserInfoAppDto;
import com.ccsw.bidoffice.user.model.UserEntity;

import java.util.List;

/**
 * @author ccsw
 *
 */
public interface UserService {

    /**
     * Recupera un usuario con su username
     *
     * @param username
     * @return
     * @throws Exception
     */
    UserEntity getByUsername(String username);

    /**
     * Obtiene usuarios que coincidan con el filtro
     *
     * @param filter
     */
    List<UserEntity> findByFilter(String filter);

    /**
     * Guarda un usuario
     *
     * @param dto
     */
    void save(UserInfoAppDto dto);
}
