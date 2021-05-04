package com.example.mainprojectmanagement.controller;

import com.example.mainprojectmanagement.model.Project;
import com.example.mainprojectmanagement.model.Task;
import com.example.mainprojectmanagement.model.User;
import com.example.mainprojectmanagement.repo.ProjectRepo;
import com.example.mainprojectmanagement.repo.TaskRepo;
import com.example.mainprojectmanagement.repo.UserRepo;
import com.example.mainprojectmanagement.service.ProjectService;
import com.example.mainprojectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService projectService;
    private TaskRepo taskRepo;
    private UserRepo userRepo;
    private ProjectRepo projectRepo;
    private UserService userService;

    @Autowired
    public  ProjectController(ProjectService projectService,TaskRepo taskRepo, UserRepo userRepo,ProjectRepo projectRepo,UserService userService){
        this.projectService = projectService;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
        this.userService = userService;
    }


    @GetMapping("/list")
    public String getProjects(Model model, String keyword){
        List<Project> list = projectService.getAllProject();
        if(keyword!=null){
            model.addAttribute("projects",projectService.findByKeyword(keyword));

        }
        else{
            model.addAttribute("projects",list);
        }
        return "list-projects";
    }


    @RequestMapping("/delete")
    public String deleteProjectById(@RequestParam("PID") int theId){
        projectService.deleteProjectById(theId);
        return "redirect:/projects/list";
    }

    @RequestMapping(value = "/showFormForAdd")
    public String addProject(Model model){
        model.addAttribute("project", new Project());
        return "project-form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(Project project){
        projectRepo.save(project);
        return "redirect:/projects/list";
    }

    @RequestMapping(value = "/showFormForAddUser")
    public String addUser(Model model){
        Project theProject = new Project();
        User theUser=new User();
        model.addAttribute("projects", theProject);
        model.addAttribute("users",theUser);
        return "user-form";
    }
    @RequestMapping(value = "/save/users", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("projects") Project theProject,@ModelAttribute User theUser){
        projectService.createProject(theProject);
        userRepo.save(theUser);
        return "redirect:/projects/list";
    }

    @RequestMapping(value="/project/{id}/users", method=RequestMethod.GET)
    public String projectAddUser(@PathVariable Integer PID, @RequestParam Integer UID, Model model) {
        User user = userRepo.getOne(UID);
        Project project = projectRepo.getOne(PID);
        if (project != null) {
            if (!project.hasUsers(user)) {
                project.getUsers().add(user);
            }
            projectRepo.save(project);
            model.addAttribute("projects", projectService.findById(PID));
            model.addAttribute("user", userRepo.findAll());
            return "redirect:/projects/list";
        }
        return "redirect:/projects/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("PID") int theId,
                                    Model theModel) {

        Project theProject = projectService.findById(theId);
        projectRepo.save(theProject);
        theModel.addAttribute("projects", theProject);
        return "user-form";
    }

    @RequestMapping(value = "addProjectUser/{id}", method = RequestMethod.GET)
    public String addUser(@PathVariable("id") Integer PID, Model model){
        model.addAttribute("user", userRepo.findAll());
        model.addAttribute("project", projectRepo.getOne(PID));
        return "user-form";
    }

}
