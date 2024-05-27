package com.owt.boatapp.controller;

import com.owt.boatapp.controller.model.UserModel;
import com.owt.boatapp.persistance.mapper.UserMapper;
import com.owt.boatapp.service.UserServiceImpl;
import com.owt.boatapp.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    private final UserMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserModel authRequest) {
        final var userDto = userService.register(mapper.modelToDto(authRequest));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody UserModel authRequest) throws Exception {
        final var userDto = userService.login(mapper.modelToDto(authRequest));
        return ResponseEntity.ok(userDto);
    }
}
