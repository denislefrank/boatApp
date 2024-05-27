package com.owt.boatapp.persistance.mapper;

import com.owt.boatapp.controller.model.UserModel;
import com.owt.boatapp.persistance.dao.UserDao;
import com.owt.boatapp.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface UserMapper {
    UserDto daoToDto(UserDao boat);

    UserDao dtoToDao(UserDto boatDto);

    UserModel dtoToModel(UserDto boatDto);

    @Mapping(target = "id", ignore = true)
    UserDto modelToDto(UserModel boatModel);

}