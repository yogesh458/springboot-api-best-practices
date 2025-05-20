package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
    @NotBlank(message = "Name must not be blank") String name,
    @NotBlank(message = "Password must not be blank") String password
) {}
