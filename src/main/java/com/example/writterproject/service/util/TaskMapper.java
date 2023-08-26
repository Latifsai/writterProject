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
        return TaskResponse.builder()
                .id(task.getId())
                .todoName(task.getTodoName())
                .description(task.getDescription())
                .createDate(task.getCreateDate())
                .lastUpdate(task.getLastUpdate())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .userResponseDTO(new UserResponseDTO(task.getUser().getId(),
                        task.getUser().getUsername(), task.getUser().getEmail()))
                .build();
    }

    public TaskResponseListDTO toTaskResponseList(Task task) {
       return TaskResponseListDTO.builder()
               .id(task.getId())
               .todoName(task.getTodoName())
               .description(task.getDescription())
               .createDate(task.getCreateDate())
               .lastUpdate(task.getLastUpdate())
               .deadline(task.getDeadline())
               .status(task.getStatus())
               .build();
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
