package com.example.writterproject.service.util;

import com.example.writterproject.domain.User;
import com.example.writterproject.dto.userDTO.AddUserRequest;
import com.example.writterproject.dto.userDTO.UserResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public User fromDTO(AddUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        return user;
    }
}


