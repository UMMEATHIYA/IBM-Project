package com.example.mainprojectmanagement.service;


import com.example.mainprojectmanagement.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public User createUser(User user);
    public User findById(int theId);
    public User deleteUserById(int theId);
    @Query(value="select * from USER u where u.UNAME like %:keyword% or u.DOMAIN like %:keyword%", nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);
}
