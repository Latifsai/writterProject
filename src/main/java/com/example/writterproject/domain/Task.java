package com.example.writterproject.domain;

import com.example.writterproject.domain.enums.ToDoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, max = 15, message = "Name length must be between 3 and 15")
    private String todoName; //:

    @NotBlank(message = "Description must be not blank")
    @Size(min = 1, max = 25, message = "Description length must be between 1 and 25")
    private String description; //:

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

    @NotBlank
    private LocalDateTime deadline; //:
    private ToDoStatus status;

}
