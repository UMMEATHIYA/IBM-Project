package com.example.mainprojectmanagement.service;

import com.example.mainprojectmanagement.model.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface TaskService {
    public List<Task> getAllTask();
    public Task createTask(Task task);
    public Task findById(int theId);
    public Task deleteTaskById(int theId);
    @Query(value="select * from TASK t where t.START_DATE like %:keyword% or t.END_DATE like %:keyword% or t.STATUS like %:keyword%", nativeQuery = true)
    List<Task> findByKeyword(@Param("keyword") String keyword);


}
