package com.example.writterproject.dto.taskDTO.post;

import com.example.writterproject.domain.ToDoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {

    private Integer id;
    private String username;
    private String todoName;
    private String description;
    private LocalDateTime deadline;
    private ToDoStatus status;
}
