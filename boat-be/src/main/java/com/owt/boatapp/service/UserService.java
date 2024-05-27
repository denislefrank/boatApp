package com.owt.boatapp.service;

import com.owt.boatapp.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto login(UserDto userDto);

    UserDto register(UserDto userDto);
}