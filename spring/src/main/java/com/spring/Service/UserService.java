package com.spring.Service;

import com.spring.Payload.ListUserDto;
import com.spring.Payload.UserDto;

public interface UserService {
    public UserDto createUser(UserDto userDto);

    void deleteUser(long id);

    ListUserDto fetchAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
}
