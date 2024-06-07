package com.spring.Service.Impl;

import com.spring.Entity.Role;
import com.spring.Entity.User;
import com.spring.Payload.RoleDto;
import com.spring.Payload.UserWithRoleDto;
import com.spring.Repository.RoleRepository;
import com.spring.Repository.UserRepository;
import com.spring.Service.RoleService;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@NoArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public RoleDto createRole(RoleDto roleDto ,long userId) {
        Optional<User> byId = userRepository.findById(userId);
        User user = byId.get();

        Role role = mapToEntity(roleDto);
        role.setUser(user);
        Role savedRole = roleRepository.save(role);

        RoleDto dto = mapToDto(savedRole);
        return dto;
    }
    public UserWithRoleDto getAllRolesByUserId(long id){
        Optional<User> byId = userRepository.findById(id);
        User user = byId.get();

        List<Role> roles = roleRepository.findUserByUserId(id);
        List<RoleDto> dtos = roles.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        UserWithRoleDto userWithRoleDto = new UserWithRoleDto();
        userWithRoleDto.setRoleDto(dtos);
        userWithRoleDto.setUser(user);
        return userWithRoleDto;
    }




    Role mapToEntity(RoleDto dto){
        Role role = modelMapper.map(dto, Role.class);
        return role;
    }

    RoleDto mapToDto(Role role){
       return  modelMapper.map(role, RoleDto.class);
    }


}
