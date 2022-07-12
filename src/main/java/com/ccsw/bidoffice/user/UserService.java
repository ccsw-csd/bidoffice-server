package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.config.security.UserInfoAppDto;
import com.ccsw.bidoffice.user.model.UserEntity;
import com.ccsw.bidoffice.user.model.UserSearchDto;
import org.springframework.data.domain.Page;

import java.sql.Date;
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
     * Método para recuperar un listado paginado de {@link com.ccsw.bidoffice.user.model.UserEntity}
     * @param dto
     * @param username
     * @param name
     * @return
     */
    Page<UserEntity> findPage(UserSearchDto dto, String username, String name);

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
