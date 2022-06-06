package com.ccsw.bidoffice.user.model;



import com.ccsw.bidoffice.role.model.RoleEntity;

import javax.persistence.*;

/**
 * @author ccsw
 *
 */
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private RoleEntity role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {

        return this.username;
    }

    /**
     * @param username new value of {@link #getUsername}.
     */
    public void setUsername(String username) {

        this.username = username;
    }

    /**
     * @return role
     */
    public RoleEntity getRole() {

        return this.role;
    }

    /**
     * @param role new value of {@link #getRole}.
     */
    public void setRole(RoleEntity role) {

        this.role = role;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
