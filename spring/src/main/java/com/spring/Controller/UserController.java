package com.spring.Controller;

import com.spring.Payload.ListUserDto;
import com.spring.Payload.UserDto;

import com.spring.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/api/users")
    public class UserController {

        @Autowired
        private UserService userService;





        //http://localhost:8080/api/users
        @PostMapping
        public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){

            if(bindingResult.hasErrors()){
                return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            UserDto dto = userService.createUser(userDto);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }

        //http://localhost:8080/api/users/4
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteUser( @PathVariable long id){

            userService.deleteUser(id);
            return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
        }

    //http://localhost:8080/api/users?pageNo=0&pageSize=3&sortBy=lastName&sortDir
        @GetMapping
        public ResponseEntity<ListUserDto> fetchAllUsers(
                @RequestParam(name = "pageNo", defaultValue = "0", required = false) int pageNo,
                @RequestParam(name = "pageSize", defaultValue = "0", required = false) int pageSize,
                @RequestParam(name = "sortBy", defaultValue = "0", required = false) String sortBy,
                @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
        ){

            ListUserDto listUserDtos = userService.fetchAllUsers(pageNo,pageSize,sortBy,sortDir);
            return new ResponseEntity<>(listUserDtos,HttpStatus.OK);
        }

}
