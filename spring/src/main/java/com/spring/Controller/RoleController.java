package com.spring.Controller;


import com.spring.Payload.RoleDto;
import com.spring.Payload.UserWithRoleDto;
import com.spring.Service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @PostMapping("/{userId}")
    //http:localhost:8080/api/roles/1
    public ResponseEntity<RoleDto> createRole(

            @RequestBody RoleDto roleDto,
            @PathVariable long userId){
        RoleDto dto = roleService.createRole(roleDto, userId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserWithRoleDto>getAllRolesByUserId(@PathVariable long userId){
        UserWithRoleDto allRolesByUserId = roleService.getAllRolesByUserId(userId);
        return  new ResponseEntity<>(allRolesByUserId,HttpStatus.OK);

    }
}
