package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.user.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * @author ccsw
 *
 */
public interface UserRepository extends CrudRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /**
     * Recupera un usuario con su username
     *
     * @param username
     * @return
     * @throws Exception
     */
    UserEntity getByUsername(String username);

    //TODO cambiar por spec
    @Query("select u from UserEntity u where concat(first_name, ' ', last_name, ' ', username) LIKE %:filter% order by first_name, last_name asc")
    List<UserEntity> findUsersLikeFilter(String filter, Pageable pageable);

    /**
     * Método para recuperar un listado paginado de {@link com.ccsw.bidoffice.user.model.UserEntity}
     * @param pageable
     * @return
     */
    /*@Query("select u from UserEntity u where (:username is null or u.username like '%'||:username||'%') and (:name is null or concat(first_name, ' ',last_name) like '%'||:name||'%' ) order by u.username asc" )
    Page<UserEntity> findAll(Pageable pageable);*/

}
