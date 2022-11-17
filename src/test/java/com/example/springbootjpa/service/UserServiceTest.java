package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.dto.UserRequestDto;
import com.example.springbootjpa.domain.dto.UserResponseDto;
import com.example.springbootjpa.domain.entity.User;
import com.example.springbootjpa.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);  // 수동 DI
    }

    @Test
    @DisplayName("회원 등록 성공 메세지가 나오는지 테스트")
    void addTest() {

        Mockito.when(userRepository.save(any()))
                .thenReturn(new User("Soyeong", "11112222"));

        UserResponseDto dto = userService.insertUser(new UserRequestDto("Soyeong", "11112222"));
        assertEquals("Soyeong", dto.getUsername());
        assertEquals("회원 등록 성공", dto.getMessage());
    }
}