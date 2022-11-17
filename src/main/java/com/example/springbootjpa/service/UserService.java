package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.dto.UserResponseDto;
import com.example.springbootjpa.domain.entity.User;
import com.example.springbootjpa.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto getUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();
        return UserResponseDto.of(user);
    }
}
