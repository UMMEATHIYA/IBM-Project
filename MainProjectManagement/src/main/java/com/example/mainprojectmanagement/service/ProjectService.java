package com.example.mainprojectmanagement.service;

import com.example.mainprojectmanagement.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectService {
    public List<Project> getAllProject();
    public Project createProject(Project project);
    public Project findById(int theId);
    public Project deleteProjectById(int theId);
    @Query(value="select * from PROJECT e where e.PNAME like %:keyword% or e.MANAGERNAME like %:keyword%", nativeQuery = true)
    List<Project> findByKeyword(@Param("keyword") String keyword);
}
