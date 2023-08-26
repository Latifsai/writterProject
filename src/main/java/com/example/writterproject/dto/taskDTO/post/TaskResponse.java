package com.example.writterproject.dto.taskDTO.post;

import com.example.writterproject.domain.enums.ToDoStatus;
import com.example.writterproject.dto.userDTO.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TaskResponse {
    private Integer id;
    private String todoName;
    private String description;
    private UserResponseDTO userResponseDTO;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime deadline;
    private ToDoStatus status;
}
