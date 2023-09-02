package com.example.writterproject.controllers;

import com.example.writterproject.dto.userDTO.UserResponseDTO;
import com.example.writterproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@Slf4j
public class AdminController {
    private final UserService service;
    public AdminController(UserService service) {
        this.service = service;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseDTO> getAll() {
        log.info("find all users!");
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
