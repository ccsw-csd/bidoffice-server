package com.ccsw.bidoffice.user;

import com.ccsw.bidoffice.config.mapper.BeanMapper;
import com.ccsw.bidoffice.user.model.UserDto;
import com.ccsw.bidoffice.user.model.UserEntity;
import com.ccsw.bidoffice.user.model.UserSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ccsw
 *
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    BeanMapper beanMapper;

    @Autowired
    UserService userService;

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public UserDto getEstimation(@PathVariable String username) {

        return this.beanMapper.map(this.userService.getByUsername(username), UserDto.class);
    }

    @RequestMapping(path="/findPage", method = RequestMethod.POST)
    public Page<UserDto> findPage(@RequestBody UserSearchDto dto){
        return this.beanMapper.mapPage(this.userService.findPage(dto), UserDto.class);
    }

    @RequestMapping(path = "/filter", method = RequestMethod.POST)
    public List<UserDto> findByFilter(@RequestBody String filter) {

        return this.beanMapper.mapList(this.userService.findByFilter(filter), UserDto.class);
    }
}
