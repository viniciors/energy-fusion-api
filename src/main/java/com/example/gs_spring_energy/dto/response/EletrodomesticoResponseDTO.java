package com.example.gs_spring_energy.dto.response;

public record EletrodomesticoResponseDTO(
        Long idEletrodomestico,
        String voltagem,
        String marca,
        String modelo,
        String eficienciaEnergetica,
        String cor,
        Double peso,
        String linkCompra
) {
}
