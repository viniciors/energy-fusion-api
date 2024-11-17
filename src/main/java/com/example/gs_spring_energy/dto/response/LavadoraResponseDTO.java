package com.example.gs_spring_energy.dto.response;

public record LavadoraResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Double capacidadeKg,
        Double consumoAgua,
        String sitemaLavagem,
        Integer velocidadeCentrifugacaoRpm,
        String temAguaQuente
) {
}
