package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.dto.UserRequestDto;
import com.example.springbootjpa.domain.dto.UserResponseDto;
import com.example.springbootjpa.domain.entity.User;
import com.example.springbootjpa.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
        List<User> userList = userRepository.findAll();
        log.info("UserRequstDto username : {}", dto.getUsername());
        for (User user: userList) {
            log.info("User username : {}", user.getUsername());
            if(user.getUsername().equals(dto.getUsername()) && user.getPassword().equals(dto.getPassword())){
                return new UserResponseDto(dto.getUsername(), "해당 유저는 이미 등록되어 있는 유저입니다.");
            }
        }
        User savedUser = userRepository.save(dto.toEntity());
        return new UserResponseDto(savedUser.getId(), savedUser.getUsername(), "회원 등록 성공");
    }
}
