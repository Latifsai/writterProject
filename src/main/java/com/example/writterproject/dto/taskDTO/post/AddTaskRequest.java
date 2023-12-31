package com.example.writterproject.dto.taskDTO.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    private String username;
    private String todoName;
    private String description;
    private LocalDateTime deadline;
}
