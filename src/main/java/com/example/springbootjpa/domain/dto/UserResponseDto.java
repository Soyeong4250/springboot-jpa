package com.example.springbootjpa.domain.dto;

import com.example.springbootjpa.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    private String message;

    public UserResponseDto(String username, String message) {
        this.username = username;
        this.message = message;
    }
}
