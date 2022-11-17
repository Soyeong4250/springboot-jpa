package com.example.springbootjpa.domain.dto;

import com.example.springbootjpa.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    Long id;
    String username;
    String password;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getPassword());
    }
}
