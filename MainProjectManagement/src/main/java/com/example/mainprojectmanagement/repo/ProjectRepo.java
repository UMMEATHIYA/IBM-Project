package com.example.mainprojectmanagement.repo;

import com.example.mainprojectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
    @Query(value="select * from PROJECT e where e.PNAME like %:keyword% or e.MANAGER like %:keyword%", nativeQuery = true)
    List<Project> findByKeyword(@Param("keyword") String keyword);
}