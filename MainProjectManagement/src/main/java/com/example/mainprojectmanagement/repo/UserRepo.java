package com.example.mainprojectmanagement.repo;

import com.example.mainprojectmanagement.model.Project;
import com.example.mainprojectmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value="select * from USER u where u.UNAME like %:keyword%", nativeQuery = true)
    List<User> findByKeyword(@Param("keyword") String keyword);
}
