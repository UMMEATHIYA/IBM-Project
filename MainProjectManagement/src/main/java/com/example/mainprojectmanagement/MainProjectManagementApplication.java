package com.example.mainprojectmanagement;

import com.example.mainprojectmanagement.model.Project;
import com.example.mainprojectmanagement.model.Task;
import com.example.mainprojectmanagement.model.User;
import com.example.mainprojectmanagement.repo.ProjectRepo;
import com.example.mainprojectmanagement.repo.TaskRepo;
import com.example.mainprojectmanagement.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MainProjectManagementApplication {

    private static final Logger log = LoggerFactory.getLogger(MainProjectManagementApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainProjectManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProjectRepo projectRepo,UserRepo userRepo, TaskRepo taskRepo) {
        return (args) -> {

            //save projects
            Project project = new Project(111,"Plant Disease Prediction","Vinay");
            Project project1 = new Project(112,"Face Mask Detection","Buchalka");
            Project project2 = new Project(113,"Whiz Bot 2.0","Samual");
            Project project3 = new Project(114,"Project App","Nilay");
            // save users
            User user1 = new User("John Doe");
            User user2 = new User("Marry Public");
            User user3 = new User("Susan Duck");
            User user4 = new User("Tim Rose");
            User user5 = new User("Ethan Hunt");
            User user6 = new User("Sam Curran");
            User user7 = new User("Patrick Cummins");
            User user8 = new User("Maxwell Kieor");

            userRepo.save(user1);
            userRepo.save(user2);
            userRepo.save(user3);
            userRepo.save(user4);
            userRepo.save(user5);
            userRepo.save(user6);
            userRepo.save(user7);
            userRepo.save(user8);

            Set<User> users1 = new HashSet<>();
            users1.add(user1);
            users1.add(user2);

            Set<User> users2 = new HashSet<>();
            users2.add(user3);
            users2.add(user4);

            Set<User> users3 = new HashSet<>();
            users3.add(user5);
            users3.add(user6);

            Set<User> users4 = new HashSet<>();
            users4.add(user7);


            //Save Tasks
            Task task1 = new Task("20/10/2005","12/02/2006",true);
            Task task2 = new Task("10/05/2020","21/08/2021",false);
            Task task3 = new Task("22/03/2021","12/12/2021",false);
            Task task4 = new Task("02/02/2021","25/04/2021",true);

            taskRepo.save(task1);
            taskRepo.save(task2);
            taskRepo.save(task3);
            taskRepo.save(task4);

            Set<Task> tasks1 = new HashSet<>();
            tasks1.add(task1);

            Set<Task> tasks2 = new HashSet<>();
            tasks2.add(task2);

            Set<Task> tasks3 = new HashSet<>();
            tasks3.add(task3);

            Set<Task> tasks4 = new HashSet<>();
            tasks4.add(task4);

            project.setUsers(users1);
            project.setTask(tasks1);
            projectRepo.save(project);

            project1.setUsers(users2);
            project1.setTask(tasks2);
            projectRepo.save(project1);

            project2.setUsers(users3);
            project2.setTask(tasks3);
            projectRepo.save(project2);

            project3.setUsers(users4);
            project3.setTask(tasks4);
            projectRepo.save(project3);
        };
    }

}
