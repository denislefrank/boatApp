package com.owt.boatapp.service;

import com.owt.boatapp.persistance.mapper.BoatMapper;
import com.owt.boatapp.persistance.repository.BoatRepository;
import com.owt.boatapp.service.dto.BoatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoatServiceImpl implements BoatService {

    private final BoatRepository boatRepository;
    private final BoatMapper mapper;

    @Override
    public List<BoatDto> findAll() {
        return boatRepository.findAll().stream().map(mapper::daoToDto).toList();
    }

    @Override
    public BoatDto findById(Long id) {
        log.info("Searching for Entity with given id {}", id);
        final var boatDao = boatRepository.findById(id).orElseThrow();
        log.debug("Found Entity with given id {}", id);
        return mapper.daoToDto(boatDao);
    }

    @Override
    public BoatDto save(BoatDto boatDto) {
        log.info("Saving given Entity {}", boatDto);
        final var boatDao = boatRepository.save(mapper.dtoToDao(boatDto));
        log.debug("Saved given Entity with id {}", boatDao.getId());
        return mapper.daoToDto(boatDao);
    }

    @Override
    public BoatDto update(BoatDto boatDto) {
        log.info("Updating given Entity with id {}", boatDto.getId());
        final var boatDao = boatRepository.save(mapper.dtoToDao(boatDto));
        log.debug("Updated given Entity with id {}", boatDao.getId());
        return mapper.daoToDto(boatDao);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting given Entity with id {}", id);
        boatRepository.deleteById(id);
        log.debug("Deleted given Entity with id {}", id);
    }
}