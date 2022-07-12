package com.ccsw.bidoffice.user.model;

import org.springframework.data.domain.Pageable;

public class UserSearchDto {


    private Pageable pageable;

    /**
     * @return pageable
     */
    public Pageable getPageable() {
        return pageable;
    }

    /**
     * @param pageable new value of {@link #getPageable}.
     */
    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
