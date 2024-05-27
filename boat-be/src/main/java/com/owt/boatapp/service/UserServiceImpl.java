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
        final var userDao = repository.findByUsername(userDto.getUsername()).orElseThrow();
        if (verifyHashedString(userDto.getPassword(), userDao.getPassword())) {
            return mapper.daoToDto(userDao);
        }
        throw new IllegalArgumentException("OWT-000001: User doest exist");
    }

    @Override
    public UserDto register(UserDto userDto) {
        userDto.setPassword(hashString(userDto.getPassword()));
        final var createdDao = repository.save(mapper.dtoToDao(userDto));
        return mapper.daoToDto(createdDao);
    }

    private String hashString(String plain) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

        Hash hash = Password.hash(plain)
                .addPepper(SECRET)
                .with(bcrypt);

        return hash.getResult();
    }

    public boolean verifyHashedString(String plainString, String hashedString) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

        return Password.check(plainString, hashedString)
                .addPepper(SECRET)
                .with(bcrypt);
    }
}