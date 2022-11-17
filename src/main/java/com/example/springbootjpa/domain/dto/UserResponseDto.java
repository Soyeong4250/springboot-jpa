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

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), "");
    }
}
