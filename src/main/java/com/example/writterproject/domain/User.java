package com.example.writterproject.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "User name must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Username name can contain only latin letters and numbers")
    private String username;

    @NotBlank(message = "User password must be not blank")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*_-]+$", message = "Password contain only latin letters and numbers")
    private String password;

    @NotBlank(message = "email must not be blank!")
    @Email(message = "Invalid email format!")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> todos;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}