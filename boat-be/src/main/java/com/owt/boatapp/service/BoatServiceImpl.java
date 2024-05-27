package com.owt.boatapp.service;

import com.owt.boatapp.persistance.mapper.BoatMapper;
import com.owt.boatapp.persistance.repository.BoatRepository;
import com.owt.boatapp.service.dto.BoatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {

    private final BoatRepository boatRepository;
    private final BoatMapper mapper;

    public List<BoatDto> findAll() {
        return boatRepository.findAll().stream().map(mapper::daoToDto).toList();
    }

    public BoatDto findById(Long id) {
        final var boatDao = boatRepository.findById(id).orElseThrow();
        return mapper.daoToDto(boatDao);
    }

    public BoatDto save(BoatDto boatDto) {
        final var boatDao = boatRepository.save(mapper.dtoToDao(boatDto));
        return mapper.daoToDto(boatDao);
    }

    public void deleteById(Long id) {
        boatRepository.deleteById(id);
    }
}