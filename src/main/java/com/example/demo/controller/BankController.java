package com.example.demo.controller;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class BankController {

    @Autowired
    private BankService bankService;

    // This endpoint retrieves all users, returning a list of UserResponseDTOs.
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return bankService.getAllUsers(); // bankService now returns a list of UserResponseDTO
    }

    // This endpoint retrieves a user by its ID, returning an Optional of UserResponseDTO.
    @GetMapping("/{id}")
    public Optional<UserResponseDTO> getUserById(@PathVariable Long id) {
        return bankService.getUserById(id); // bankService should return Optional<UserResponseDTO>
    }

    // This endpoint creates a user based on a UserResponseDTO object from the request body.
    @PostMapping("/save")
    public UserResponseDTO createUser(@RequestBody UserResponseDTO userResponseDTO) {
        return bankService.createUser(userResponseDTO); // bankService method will now handle UserResponseDTO
    }

    // This endpoint updates a user based on the provided ID and a UserResponseDTO object in the request body.
    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserResponseDTO userResponseDTO) {
        return bankService.updateUser(id, userResponseDTO); // bankService will handle the update for UserResponseDTO
    }

    // This endpoint deletes a user by the provided ID.
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        bankService.deleteUser(id);
    }
}
