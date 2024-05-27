package com.owt.boatapp.controller;

import com.owt.boatapp.controller.model.BoatModel;
import com.owt.boatapp.persistance.mapper.BoatMapper;
import com.owt.boatapp.service.BoatService;
import com.owt.boatapp.service.dto.BoatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/boat")
@RequiredArgsConstructor
public class BoatController {

    private final BoatService boatService;

    private final BoatMapper mapper;

    @GetMapping
    public ResponseEntity<List<BoatDto>> getAllBoats() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoatDto> getBoatById(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<BoatDto> createBoat(@RequestBody BoatModel boatModel) {
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<BoatDto> updateBoat(@RequestBody BoatDto boatDto) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoat(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }
}