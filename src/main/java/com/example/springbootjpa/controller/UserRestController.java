package com.example.springbootjpa.controller;

import com.example.springbootjpa.domain.dto.UserRequestDto;
import com.example.springbootjpa.domain.dto.UserResponseDto;
import com.example.springbootjpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.getUser(id);
        log.info("userResponseDto id : {}", userResponseDto.getId());
        return ResponseEntity.ok().body(userResponseDto);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto dto) {
        UserResponseDto userResponseDto = userService.insertUser(dto);
        return ResponseEntity.ok().body(userResponseDto);
    }
}
