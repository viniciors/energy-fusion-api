package com.example.gs_spring_energy.dto.request;

public record LavadoraRequestDTO(
        Long idEletrodomestico,
        Double capacidadeKg,
        Double consumoAgua,
        String sitemaLavagem,
        Integer velocidadeCentrifugacaoRpm,
        String temAguaQuente
) {
}
