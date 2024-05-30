package com.owt.boatapp.persistance.mapper;

import com.owt.boatapp.controller.model.BoatModel;
import com.owt.boatapp.persistance.dao.BoatDao;
import com.owt.boatapp.service.dto.BoatDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface BoatMapper {

    BoatDto daoToDto(BoatDao boat);

    BoatDao dtoToDao(BoatDto boatDto);

    @Mapping(target = "id", ignore = true)
    BoatDto modelToDto(BoatModel boatModel);
}