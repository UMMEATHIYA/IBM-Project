package com.example.mainprojectmanagement.service;

import com.example.mainprojectmanagement.model.Project;
import com.example.mainprojectmanagement.model.User;
import com.example.mainprojectmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findById(int theId) {
        User user=null;
        Optional<User> rs=userRepo.findById(theId);
        if(rs.isPresent())
        {
            user=rs.get();
        }
        else {
            throw new RuntimeException("Id not found - " + theId);
        }

        return user;
    }

    @Override
    public User deleteUserById(int theId) {
        Optional<User> user1=userRepo.findById(theId);
        if(user1.isPresent())
        {
            userRepo.deleteById(theId);
        }
        else {
            throw new RuntimeException("Id not found - " + theId);
        }

        return null;
    }

    @Override
    public List<User> findByKeyword(String keyword) {
        return userRepo.findByKeyword(keyword);
    }
}
