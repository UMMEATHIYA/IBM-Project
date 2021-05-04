package com.example.mainprojectmanagement.repo;

import com.example.mainprojectmanagement.model.Project;
import com.example.mainprojectmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    @Query(value="select * from TASK t where t.START_DATE like %:keyword% or t.END_DATE like %:keyword% or t.STATUS like %:keyword%", nativeQuery = true)
    List<Task> findByKeyword(@Param("keyword") String keyword);

}
