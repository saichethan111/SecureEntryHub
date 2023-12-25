package com.harman.seh.service;

import com.harman.seh.entity.Users;

import java.util.List;

public interface SehService {

    Users save(Users theUser);
    Users findByEmail(String email);
    public Users findById(int theId);
}
