package com.example.writterproject.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
}
