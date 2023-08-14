package com.example.writterproject.service.util;

import com.example.writterproject.domain.Task;
import com.example.writterproject.dto.taskDTO.TaskResponseListDTO;
import com.example.writterproject.dto.taskDTO.post.AddTaskRequest;
import com.example.writterproject.dto.taskDTO.post.TaskResponse;
import com.example.writterproject.dto.taskDTO.post.UpdateTaskRequest;
import com.example.writterproject.dto.userDTO.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskMapper {
    public TaskResponse toTaskResponse(Task task) {
        var response = new TaskResponse();
        response.setId(task.getId());
        response.setTodoName(task.getTodoName());
        response.setDescription(task.getDescription());
        response.setCreateDate(task.getCreateDate());
        response.setLastUpdate(task.getLastUpdate());
        response.setDeadline(task.getDeadline());
        response.setStatus(task.getStatus());
        response.setUserResponseDTO(new UserResponseDTO(task.getUser().getId(), task.getUser().getUsername()));
        return response;
    }

    public TaskResponseListDTO toTaskResponseList(Task task) {
        return new TaskResponseListDTO(
                task.getId(),
                task.getTodoName(),
                task.getDescription(),
                task.getCreateDate(),
                task.getLastUpdate(),
                task.getDeadline(),
                task.getStatus());
    }


    public Task fromRequest(AddTaskRequest request) {
        Task task = new Task();
        task.setTodoName(request.getTodoName());
        task.setDescription(request.getDescription());
        task.setDeadline(request.getDeadline());
        return task;
    }

    public Task fromUpdateRequest(Task task, UpdateTaskRequest request) {
        if (request.getTodoName() != null) task.setTodoName(request.getTodoName());
        if (request.getDescription() != null) task.setDescription(request.getDescription());
        if (request.getDeadline() != null) task.setDeadline(request.getDeadline());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        task.setLastUpdate(LocalDateTime.now());
        return task;
    }

}
