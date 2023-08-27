package com.example.writterproject.controllers;

import com.example.writterproject.dto.userDTO.UserResponseDTO;
import com.example.writterproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class AdminController {
    private final UserService service;
    public AdminController(UserService service) {
        this.service = service;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseDTO> getAll() {
        return service.findAll();
    }
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserResponseDTO find(@PathVariable(name = "name") String name) {
        return service.findByUserName(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") Integer id) {
         service.deleteUser(id);
    }
}
