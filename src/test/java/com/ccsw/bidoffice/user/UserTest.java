package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.common.exception.EntityNotFoundException;
import com.ccsw.bidoffice.role.RoleServiceImpl;
import com.ccsw.bidoffice.role.model.RoleDto;
import com.ccsw.bidoffice.role.model.RoleEntity;
import com.ccsw.bidoffice.user.model.UserDto;
import com.ccsw.bidoffice.user.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    public static final Long EXISTS_USER_ID = 6L;
    public static final Long NOT_EXISTS_USER_ID = 7L;
    public static final Long EXISTS_ROLE_ID = 2L;

    public static final String EXISTS_USER_USERNAME = "USERNAME6";


    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleServiceImpl roleService;

    private UserDto userDto;

    private UserEntity userEntityData;

    private RoleEntity roleEntityData;

    @BeforeEach
    public void setUp() {
        this.userDto = new UserDto();
        this.userDto.setFirstName("");
        this.userDto.setLastName("");
        this.userDto.setEmail("");
        roleEntityData = new RoleEntity();
        this.roleEntityData.setId(EXISTS_ROLE_ID);
        this.roleEntityData.setName("user");
        userEntityData = new UserEntity();
        this.userEntityData.setId(EXISTS_USER_ID);
        this.userEntityData.setUsername("USERNAME6");
        this.userEntityData.setFirstName("");
        this.userEntityData.setLastName("");
        this.userEntityData.setEmail("");
        this.userEntityData.setRole(roleEntityData);
    }

    @Test
    public void modifyWithExistIdShouldModifyUser() throws EntityNotFoundException {

        RoleDto role = new RoleDto();
        role.setId(EXISTS_ROLE_ID);
        this.userDto.setId(EXISTS_USER_ID);
        this.userDto.setUsername(EXISTS_USER_USERNAME);
        this.userDto.setRole(role);
        when(this.userRepository.existsById(EXISTS_USER_ID)).thenReturn(true);
        when(this.userRepository.getByUsername(EXISTS_USER_USERNAME)).thenReturn(userEntityData);
        when(this.roleService.getById(EXISTS_ROLE_ID)).thenReturn(roleEntityData);

        this.userServiceImpl.modifyUser(userDto);

        verify(this.userRepository).save(userEntityData);
    }

    @Test
    public void modifyWithNotExistIdShouldThrowException() throws EntityNotFoundException {

        this.userDto.setId(NOT_EXISTS_USER_ID);
        this.userDto.setUsername(EXISTS_USER_USERNAME);
        UserEntity userEntity = mock(UserEntity.class);
        doReturn(false).when(this.userRepository).existsById(NOT_EXISTS_USER_ID);

        try {
            this.userServiceImpl.modifyUser(userDto);
        } catch (EntityNotFoundException e) {
        }

        verify(this.userRepository, never()).save(userEntity);
    }

}
