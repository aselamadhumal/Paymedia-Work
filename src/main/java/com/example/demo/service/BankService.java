package com.example.demo.service;

import com.example.demo.dto.UserResponseDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    private UserRepository userRepository;

    // Converts a User entity to a UserResponseDTO
    private UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setName(user.getName());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }

    // Converts a UserResponseDTO to a User entity
    private User convertToEntity(UserResponseDTO dto) {
        return new User(dto.getName(), dto.getAge(), dto.getEmail(), dto.getPassword());
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO);
    }

    public UserResponseDTO createUser(UserResponseDTO userResponseDTO) {
        User user = convertToEntity(userResponseDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserResponseDTO updateUser(Long id, UserResponseDTO userResponseDTO) {
        if (userRepository.existsById(id)) {
            User user = convertToEntity(userResponseDTO);
            user.setId(id);
            User updatedUser = userRepository.save(user);
            return convertToDTO(updatedUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
