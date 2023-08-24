package com.example.writterproject.dto.taskDTO;

import com.example.writterproject.domain.enums.ToDoStatus;
import com.example.writterproject.domain.User;

import java.time.LocalDateTime;


public class TaskDTO {
    private Integer id;

    private String todoName;
    private String description;

    private User user;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    private LocalDateTime deadline;
    private ToDoStatus status;

}
