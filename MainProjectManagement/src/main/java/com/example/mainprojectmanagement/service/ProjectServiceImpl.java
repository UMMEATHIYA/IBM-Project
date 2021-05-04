package com.example.mainprojectmanagement.service;

import com.example.mainprojectmanagement.model.Project;
import com.example.mainprojectmanagement.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepo projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepo.findAll();
    }

    @Override
    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    @Override
    public Project findById(int theId) {
        Project proj=null;
        Optional<Project> result=projectRepo.findById(theId);
        if(result.isPresent())
        {
            proj=result.get();
        }
        else {
            throw new RuntimeException("Id not found - " + theId);
        }

        return proj;
    }

    @Override
    public Project deleteProjectById(int theId) {
        Optional<Project> proj1=projectRepo.findById(theId);
        if(proj1.isPresent())
        {
            projectRepo.deleteById(theId);
        }
        else {
            throw new RuntimeException("Id not found - " + theId);
        }

        return null;
    }

    @Override
    public List<Project> findByKeyword(String keyword) {
        return projectRepo.findByKeyword(keyword);
    }
}
