package com.example.projectmanagement.controller;


import com.example.projectmanagement.model.Project;
import com.example.projectmanagement.model.Task;
import com.example.projectmanagement.model.User;
import com.example.projectmanagement.repo.ProjectRepo;
import com.example.projectmanagement.repo.TaskRepo;
import com.example.projectmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TaskRepo taskRepo;


    @GetMapping("/project")
    public List<Project> getDetails(){
        return projectRepo.findAll();
    }
    @GetMapping("/user")
    public List<User> getUserDetails(){
        return userRepo.findAll();
    }
    @GetMapping("/task")
    public List<Task> getTaskDetails(){
        return taskRepo.findAll();
    }
    @PostMapping("/project/new")
    public Project createProject(@Valid @RequestBody Project project) {
        return projectRepo.save(project);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity <Project> getProjectById(
            @PathVariable(value = "id") long PID) throws ResourceNotFoundException {
        Project projects = projectRepo.findById(PID)
                .orElseThrow(() -> new ResourceNotFoundException("PID not found :: " + PID));
        return ResponseEntity.ok().body(projects);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <User> getUserById(
            @PathVariable(value = "id") long UID) throws ResourceNotFoundException {
        User users = userRepo.findById(UID)
                .orElseThrow(() -> new ResourceNotFoundException("UID not found :: " + UID));
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/project/{id}")
    public ResponseEntity <Project> updateProject(
            @PathVariable(value = "id") Long PID,
            @Valid @RequestBody Project projectDetails) throws ResourceNotFoundException {
        Project project1 = projectRepo.findById(PID)
                .orElseThrow(() -> new ResourceNotFoundException("Project Id not found :: " + PID));
        project1.setPNAME(projectDetails.getPNAME());
        project1.setMANAGERNAME(projectDetails.getMANAGERNAME());
        final Project updatedProject = projectRepo.save(project1);
        return ResponseEntity.ok(updatedProject);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity <User> updateUser(
            @PathVariable(value = "id") Long UID,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user1 = userRepo.findById(UID)
                .orElseThrow(() -> new ResourceNotFoundException("User Id not found :: " + UID));
        user1.setNAME(userDetails.getNAME());
        user1.setDOMAIN(userDetails.getDOMAIN());
        final User updatedUser = userRepo.save(user1);
        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/task/{id}")
    public ResponseEntity <Task> getTaskById(
            @PathVariable(value = "id") long TID) throws ResourceNotFoundException {
        Task tasks = taskRepo.findById(TID)
                .orElseThrow(() -> new ResourceNotFoundException("TID not found :: " + TID));
        return ResponseEntity.ok().body(tasks);
    }


    @PutMapping("/task/{id}")
    public ResponseEntity <Task> updateTask(
            @PathVariable(value = "id") Long TID,
            @Valid @RequestBody Task taskDetails) throws ResourceNotFoundException {
        Task task1 = taskRepo.findById(TID)
                .orElseThrow(() -> new ResourceNotFoundException("Task Id not found :: " + TID));
        task1.setStart_Date(taskDetails.getStart_Date());
        task1.setEnd_Date(taskDetails.getEnd_Date());
        task1.setStatus(taskDetails.isStatus());
        final Task updatedTask = taskRepo.save(task1);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/user/{id}")
    public Map< String, Boolean > deleteUser(
            @PathVariable(value = "id") Long UID) throws ResourceNotFoundException {
        User user1 = userRepo.findById(UID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + UID));

        userRepo.delete(user1);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @DeleteMapping("/task/{id}")
    public Map< String, Boolean > deleteTask(
            @PathVariable(value = "id") Long TID) throws ResourceNotFoundException {
        Task task1 = taskRepo.findById(TID)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found :: " + TID));

        taskRepo.delete(task1);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    @DeleteMapping("/project/{id}")
    public Map< String, Boolean > deleteProject(
            @PathVariable(value = "id") Long PID) throws ResourceNotFoundException {
        Project project1 = projectRepo.findById(PID)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found :: " + PID));

        projectRepo.delete(project1);
        Map < String, Boolean > response = new HashMap< >();
        response.put("Project deleted successfully", Boolean.TRUE);
        return response;
    }
}
