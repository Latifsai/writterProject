package com.example.writterproject.service.util;

import com.example.writterproject.domain.User;
import com.example.writterproject.dto.userDTO.AddUserRequest;
import com.example.writterproject.dto.userDTO.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }

    public User fromDTO(AddUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return user;
    }
}


