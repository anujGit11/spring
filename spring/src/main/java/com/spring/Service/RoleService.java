package com.spring.Service;

import com.spring.Payload.RoleDto;
import com.spring.Payload.UserWithRoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto, long userId);

    public UserWithRoleDto getAllRolesByUserId(long id);

}
