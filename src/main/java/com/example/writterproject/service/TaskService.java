package com.example.writterproject.service;

import com.example.writterproject.domain.Task;
import com.example.writterproject.domain.ToDoStatus;
import com.example.writterproject.domain.User;
import com.example.writterproject.dto.taskDTO.TaskResponseListDTO;
import com.example.writterproject.dto.taskDTO.post.AddTaskRequest;
import com.example.writterproject.dto.taskDTO.post.TaskResponse;
import com.example.writterproject.dto.taskDTO.post.UpdateTaskRequest;
import com.example.writterproject.repository.TaskRepository;
import com.example.writterproject.service.util.TaskMapper;
import com.example.writterproject.service.validation.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    private final TaskMapper mapper;
    private final TaskRepository repository;
    private final UserService userService;


    public TaskService(TaskMapper mapper, TaskRepository repository, UserService userService) {
        this.mapper = mapper;
        this.repository = repository;
        this.userService = userService;
    }

    public List<TaskResponseListDTO> getAll() {
        return repository.findAll().
                stream().map(task -> mapper.toTaskResponseList(task))
                .toList();
    }

    public TaskResponse findByID(Integer id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id:" + id + "was not found!"));
        return mapper.toTaskResponse(task);
    }

    public TaskResponse create(AddTaskRequest request) {
        User user = userService.findByUserNameForTask(request.getUsername());
        Task task = mapper.fromRequest(request);
        task.setUser(user);
        task.setCreateDate(LocalDateTime.now());
        task.setStatus(ToDoStatus.Open);
        task.setLastUpdate(LocalDateTime.now());
        Task savedTask = repository.save(task);
        return mapper.toTaskResponse(savedTask);
    }

    public TaskResponse updateTask(UpdateTaskRequest request) {
        Task task = repository.findById(request.getId())
                .orElseThrow(() -> new NotFoundException("Task not found!"));


        if (task.getUser().getUsername().equals(request.getUsername())) {
            Task updatedTask = mapper.fromUpdateRequest(task, request);
            Task saved = repository.save(updatedTask);
            return mapper.toTaskResponse(saved);
        }else {
            throw  new NotFoundException("User: " + request.getUsername() + " has not rights o update!");
        }

    }

    public void delete(Integer id) {
        Task task = repository.findById(id)
                .orElseThrow(() ->  new NotFoundException("task not found!"));
        repository.delete(task);
    }

}
