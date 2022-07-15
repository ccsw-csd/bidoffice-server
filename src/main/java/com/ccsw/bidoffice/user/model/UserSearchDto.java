package com.ccsw.bidoffice.user.model;

import org.springframework.data.domain.Pageable;

public class UserSearchDto {

    String username;

    String firstName;

    String lastName;

    String name;

    private Pageable pageable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username new value of {@link #getUsername}.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName new value of {@link #getFirstName}.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName new value of {@link #getLastName}.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
