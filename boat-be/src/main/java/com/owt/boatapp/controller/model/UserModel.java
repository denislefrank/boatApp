package com.owt.boatapp.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserModel {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}