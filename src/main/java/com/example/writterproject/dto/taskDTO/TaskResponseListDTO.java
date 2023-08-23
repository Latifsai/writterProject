package com.example.writterproject.dto.taskDTO;

import com.example.writterproject.domain.ToDoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseListDTO {
    private Integer id;
    private String todoName;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private LocalDateTime deadline;
    private ToDoStatus status;

}