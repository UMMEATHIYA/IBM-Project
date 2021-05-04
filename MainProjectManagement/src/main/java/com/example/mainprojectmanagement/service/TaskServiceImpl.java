package com.example.mainprojectmanagement.service;

import com.example.mainprojectmanagement.model.Task;
import com.example.mainprojectmanagement.model.User;
import com.example.mainprojectmanagement.repo.TaskRepo;
import com.example.mainprojectmanagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements  TaskService{

    private TaskRepo taskRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }
    @Override
    public List<Task> getAllTask() {
        return taskRepo.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task findById(int theId) {
        Task task=null;
        Optional<Task> task1=taskRepo.findById(theId);
        if(task1.isPresent())
        {
            task=task1.get();
        }
        else {
            throw new RuntimeException("Id not found - " + theId);
        }

        return task;
    }

    @Override
    public Task deleteTaskById(int theId) {
        Optional<Task> task2=taskRepo.findById(theId);
        if(task2.isPresent())
        {
            taskRepo.deleteById(theId);
        }
        else {
            throw new RuntimeException("Id not found - " + theId);
        }
        return null;
    }

    @Override
    public List<Task> findByKeyword(String keyword) {
        return taskRepo.findByKeyword(keyword);
    }
}
