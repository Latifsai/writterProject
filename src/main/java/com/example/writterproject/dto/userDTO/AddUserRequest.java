package com.example.writterproject.dto.userDTO;

import com.example.writterproject.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String username;
    private String password;
    private String email;
    private Role role;

}