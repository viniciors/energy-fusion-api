package com.example.gs_spring_energy.dto.request;

public record LavadoraRequestDTO(
        Double capacidadeKg,
        Double consumoAgua,
        String sistemaLavagem,
        Integer velocidadeCentrifugacaoRpm,
        String temAguaQuente,
        EletrodomesticoRequestDTO eletrodomestico
) {
}
