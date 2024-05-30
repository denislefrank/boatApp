package com.owt.boatapp.service;

import com.owt.boatapp.service.dto.BoatDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoatService {

    List<BoatDto> findAll();

    BoatDto findById(Long id);

    BoatDto save(BoatDto boatDto);

    BoatDto update(BoatDto boatDto);

    void deleteById(Long id);
}