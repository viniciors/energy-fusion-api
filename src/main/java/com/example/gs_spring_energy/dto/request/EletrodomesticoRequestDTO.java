package com.example.gs_spring_energy.dto.request;

public record EletrodomesticoRequestDTO(
        String voltagem,
        String marca,
        String modelo,
        String eficienciaEnergetica,
        String cor,
        Double peso,
        String linkCompra
) {
}
