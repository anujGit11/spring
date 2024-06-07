package com.spring.Repository;

import com.spring.Entity.Role;
import com.spring.Payload.RoleDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findUserByUserId(long userId);
}
