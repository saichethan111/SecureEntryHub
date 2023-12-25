package com.harman.seh.dao;

import com.harman.seh.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByEmail(String email);
}
