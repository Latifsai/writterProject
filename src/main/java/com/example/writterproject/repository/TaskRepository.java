package com.example.writterproject.repository;


import com.example.writterproject.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByTodoName(String todoName);
}