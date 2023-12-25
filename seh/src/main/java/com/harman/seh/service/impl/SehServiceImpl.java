package com.harman.seh.service.impl;

import com.harman.seh.dao.UserRepo;
import com.harman.seh.entity.Users;
import com.harman.seh.service.SehService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SehServiceImpl implements SehService {

    @Autowired
    UserRepo userRepo;

    @Override
    public Users save(Users theUser) {
        return userRepo.save(theUser);
    }

    @Override
    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Users findById(int theId) {
        Optional<Users> result = userRepo.findById(theId);

        Users theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theUser;
    }

}
