package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.dto.UserRequestDto;
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
        if (optUser.isEmpty()) {
            return new UserResponseDto(id, "", "해당 id의 유저가 없습니다.");
        } else {
            User user = optUser.get();
            return new UserResponseDto(user.getId(), user.getUsername(), "유저 찾기 성공");
        }
    }

    public UserResponseDto insertUser(UserRequestDto dto) {
        User savedUser = userRepository.save(dto.toEntity());
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), "회원 등록 성공");
    }
}
