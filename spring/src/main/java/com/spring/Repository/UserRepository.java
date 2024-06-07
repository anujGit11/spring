package com.spring.Repository;

import com.spring.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface UserRepository extends JpaRepository<User,Long> {



    }
