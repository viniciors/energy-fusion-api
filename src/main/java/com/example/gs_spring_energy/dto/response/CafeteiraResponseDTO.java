package com.example.gs_spring_energy.dto.response;

public record CafeteiraResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Double capacidadeAgua,
        Double pressao,
        String capsulasCompativeis,
        String tecnologia
) {
}
