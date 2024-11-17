package com.example.gs_spring_energy.dto.response;

public record MicroondasResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Integer potenciaWatts,
        Integer quantidadeProgramas,
        Double diametroPrato,
        Integer frequencia,
        String temDescongelamento
) {
}
