package com.example.springbootjpa.domain.dto;

import com.example.springbootjpa.domain.entity.User;
import lombok.Builder;

public class UserRequestDto {
    String username;
    String password;

    @Builder
    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User toEntity() {
        return new User(this.username, this.password);
    }
}
