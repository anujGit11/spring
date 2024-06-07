package com.spring.Service.Impl;

import com.spring.Entity.User;
import com.spring.Payload.ListUserDto;
import com.spring.Payload.UserDto;
import com.spring.Repository.UserRepository;
import com.spring.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = mapToEntity(userDto);

        User savedUser = userRepository.save(user);

        UserDto dto = mapToDto(savedUser);
        return dto;
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public ListUserDto fetchAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
//        List<User> user = userRepository.findAll();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<User> all = userRepository.findAll(pageable);
        List<User> user = all.getContent();

        List<UserDto> userDtos = user.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        ListUserDto listUserDto = new ListUserDto();
        listUserDto.setUserDto(userDtos);
        listUserDto.setTotalPages(all.getTotalPages());
        listUserDto.setTotalElements((int) all.getTotalElements());
        listUserDto.setFirstPage(all.isFirst());
        listUserDto.setLastPage(all.isLast());
        listUserDto.setPageNumber(all.getNumber());
        return listUserDto;
    }

    User mapToEntity(UserDto userDto){
//        User user = new User();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        user.setEmail(userDto.getEmail());
        User user = modelMapper.map(userDto, User.class);

        return user;
    }

    UserDto mapToDto(User user){

//        UserDto dto = new UserDto();
//        dto.setId(user.getId());
//        dto.setFirstName(user.getFirstName());
//        dto.setLastName(user.getLastName());
//        dto.setEmail(user.getEmail());

        UserDto dto = modelMapper.map(user, UserDto.class);
        return dto;
    }

}
