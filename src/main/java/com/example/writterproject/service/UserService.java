package com.example.writterproject.service;

import com.example.writterproject.domain.User;
import com.example.writterproject.dto.userDTO.AddUserRequest;
import com.example.writterproject.dto.userDTO.UserResponseDTO;
import com.example.writterproject.repository.UserRepository;
import com.example.writterproject.service.util.UserMapper;
import com.example.writterproject.service.validation.NotFoundException;
import com.example.writterproject.service.validation.UserAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<UserResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(user -> mapper.toResponse(user))
                .toList();
    }

    public UserResponseDTO findByUserName(String userName) {
        User user = repository.findByUsername(userName).orElseThrow(() -> new NotFoundException("User not found!"));
        return mapper.toResponse(user);
    }


    public User findByUserNameForTask(String userName) {
        User user = repository.findByUsername(userName)
                .orElseThrow(() -> new NotFoundException("User not found!"));
        return user;
    }

    public UserResponseDTO createUser(AddUserRequest request) {
        if (repository.findByUsername(request.getUsername()).isEmpty()) {
            User user = mapper.fromDTO(request);
            User sadevUser = repository.save(user);
            return mapper.toResponse(sadevUser);
        } else {
            throw new UserAlreadyExistException("User is exist, with username: " + request.getUsername());
        }
    }

    public void deleteUser(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id:" + id + "not found!"));
        repository.delete(user);
    }
}
