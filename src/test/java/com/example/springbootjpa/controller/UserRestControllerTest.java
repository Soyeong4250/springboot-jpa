package com.example.springbootjpa.controller;

import com.example.springbootjpa.domain.dto.UserResponseDto;
import com.example.springbootjpa.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}