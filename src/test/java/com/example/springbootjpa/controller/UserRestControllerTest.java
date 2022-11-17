package com.example.springbootjpa.controller;

import com.example.springbootjpa.domain.dto.UserRequestDto;
import com.example.springbootjpa.domain.dto.UserResponseDto;
import com.example.springbootjpa.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    @DisplayName("getUser 테스트")
    void getUserTest() throws Exception {
        given(userService.getUser(1L)).willReturn(new UserResponseDto(1L, "Soyeong", "유저 찾기 성공"));

        Long userId = 1L;

        mockMvc.perform(get(String.format("/api/v1/users/%d", userId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("Soyeong"))
                .andExpect(jsonPath("$.message").value("유저 찾기 성공"))
                .andDo(print());

        verify(userService).getUser(1L);
    }

    @Test
    @DisplayName("addUser 테스트")
    void addUserTest() throws Exception {
        UserRequestDto dto = UserRequestDto.builder()
                .username("testName")
                .password("testPassword")
                .build();

        given(userService.insertUser(dto))
                .willReturn(new UserResponseDto(dto.getUsername(), "회원 등록 성공"));


        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message").exists())
                .andDo(print());

        verify(userService.insertUser(ArgumentMatchers.refEq(dto)));
    }
}