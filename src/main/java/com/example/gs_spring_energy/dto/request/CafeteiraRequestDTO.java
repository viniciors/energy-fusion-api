package com.example.gs_spring_energy.dto.request;

public record CafeteiraRequestDTO(
        Double capacidadeAgua,
        Double pressao,
        String capsulasCompativeis,
        String tecnologia,
        EletrodomesticoRequestDTO eletrodomestico
) {}
