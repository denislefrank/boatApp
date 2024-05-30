package com.owt.boatapp.service;

import com.owt.boatapp.persistance.dao.UserDao;
import com.owt.boatapp.persistance.mapper.UserMapper;
import com.owt.boatapp.persistance.repository.UserRepository;
import com.owt.boatapp.service.dto.UserDto;
import com.password4j.BcryptFunction;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    private final static String SECRET = "$2b$12$";
    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserServiceImpl userService;

    private UserDao createUserDao() {
        return new UserDao(1L, "testuser", "$2b$12$hashedpassword");
    }

    private UserDto createUserDto() {
        return new UserDto(1L, "testuser", "password");
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() {
        final var userDto = createUserDto();
        final var bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        final var hash = Password.hash("password").addPepper(SECRET).with(bcrypt);

        final var userDao = new UserDao(1L, "testuser", hash.getResult());
        when(repository.findByUsername(userDto.username())).thenReturn(Optional.of(userDao));
        when(mapper.daoToDto(any())).thenReturn(userDto);

        UserDto result = userService.login(userDto);

        assertThat(result, notNullValue());
        assertThat(result.username(), equalTo(userDto.username()));
    }

    @Test
    public void testLogin_UserNotFound() {
        final var userDto = createUserDto();
        when(repository.findByUsername(userDto.username())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.login(userDto));
    }

    @Test
    public void testLogin_InvalidPassword() {
        final var userDto = createUserDto();
        final var userDao = createUserDao();
        when(repository.findByUsername(userDto.username())).thenReturn(Optional.of(userDao));

        assertThrows(IllegalArgumentException.class, () -> userService.login(userDto));
    }

    @Test
    public void testRegister_Success() {
        final var userDto = createUserDto();
        final var hashedUserDto = new UserDto(1L, "newuser", "$2b$12$hashedpassword");
        final var userDao = createUserDao();

        when(mapper.dtoToDao(ArgumentMatchers.any())).thenReturn(userDao);
        when(repository.save(ArgumentMatchers.any())).thenReturn(userDao);
        when(mapper.daoToDto(ArgumentMatchers.any())).thenReturn(hashedUserDto);

        final var result = userService.register(userDto);

        assertThat(result, notNullValue());
        assertThat(result.username(), equalTo(hashedUserDto.username()));
        assertThat(result.password(), equalTo("$2b$12$hashedpassword"));
    }
}