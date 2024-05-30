package com.owt.boatapp.service;

import com.owt.boatapp.persistance.mapper.UserMapper;
import com.owt.boatapp.persistance.repository.UserRepository;
import com.owt.boatapp.service.dto.UserDto;
import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final static String SECRET = "$2b$12$";

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto login(UserDto userDto) {
        final var userDao = repository.findByUsername(userDto.username()).orElseThrow();
        if (verifyHashedString(userDto.password(), userDao.getPassword())) {
            return mapper.daoToDto(userDao);
        }
        throw new IllegalArgumentException("OWT-000001: User doest exist");
    }

    @Override
    public UserDto register(UserDto userDto) {
        final var hashedUser = new UserDto(userDto.id(), userDto.username(), hashString(userDto.password()));
        final var createdDao = repository.save(mapper.dtoToDao(hashedUser));
        return mapper.daoToDto(createdDao);
    }

    private String hashString(String plain) {
        final BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        final Hash hash = Password.hash(plain).addPepper(SECRET).with(bcrypt);
        return hash.getResult();
    }

    private boolean verifyHashedString(String plainString, String hashedString) {
        final BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        return Password.check(plainString, hashedString)
                .addPepper(SECRET)
                .with(bcrypt);
    }
}