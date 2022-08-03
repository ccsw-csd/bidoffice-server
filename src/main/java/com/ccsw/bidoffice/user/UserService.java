package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.config.security.UserInfoAppDto;
import com.ccsw.bidoffice.user.model.UserDto;
import com.ccsw.bidoffice.user.model.UserEntity;
import com.ccsw.bidoffice.user.model.UserSearchDto;
import org.springframework.data.domain.Page;

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
     * Recupera un usuario por su id
     *
     * @param id
     * @return
     * @throws Exception
     */
    UserEntity get(Long id) throws EntityNotFoundException;

    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.bidoffice.user.model.UserEntity}
     * @param dto
     * @return
     */
    Page<UserEntity> findPage(UserSearchDto dto);

    /**
     * Obtiene usuarios que coincidan con el filtro
     *
     * @param filter
     */
    List<UserEntity> findByFilter(String filter);

    /**
     * Modifica un usuario
     *
     * @param dto
     */
    UserEntity modifyUser(UserDto dto) throws EntityNotFoundException;

    /**
     * Guarda un usuario
     *
     * @param dto
     */
    void save(UserInfoAppDto dto);
}
