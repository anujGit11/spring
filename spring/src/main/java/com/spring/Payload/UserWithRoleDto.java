package com.spring.Payload;

import com.spring.Entity.User;

import java.util.List;

public class UserWithRoleDto {


    private List<RoleDto> roleDto;

   private User user;

    public List<RoleDto> getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(List<RoleDto> roleDto) {
        this.roleDto = roleDto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
