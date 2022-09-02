package com.ccsw.bidoffice.config.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author mvallsal
 *
 */
public class UserUtils {

    /**
     * @return UserDetailsJWTDto
     */
    public static UserInfoDto getUserDetails() {

        return (UserInfoDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
