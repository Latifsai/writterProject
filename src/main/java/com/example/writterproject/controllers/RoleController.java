package com.example.writterproject.controllers;

import com.example.writterproject.domain.Role;
import com.example.writterproject.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Role> getAllRoles() {
        return repository.findAll();
    }


}
