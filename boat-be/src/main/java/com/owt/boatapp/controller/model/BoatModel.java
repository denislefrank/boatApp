package com.owt.boatapp.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoatModel {
    @NotBlank
    private String name;
    private String description;
}