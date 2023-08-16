package com.example.writterproject.controllers;

import com.example.writterproject.dto.taskDTO.TaskResponseListDTO;
import com.example.writterproject.dto.taskDTO.post.AddTaskRequest;
import com.example.writterproject.dto.taskDTO.post.TaskResponse;
import com.example.writterproject.dto.taskDTO.post.UpdateTaskRequest;
import com.example.writterproject.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<TaskResponseListDTO> getTasks() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public TaskResponse find(@PathVariable("id") Integer id) {
        return service.findByID(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse add(@RequestBody AddTaskRequest request) {
        return service.create(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public TaskResponse update(@RequestBody UpdateTaskRequest request) {
        return service.updateTask(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}