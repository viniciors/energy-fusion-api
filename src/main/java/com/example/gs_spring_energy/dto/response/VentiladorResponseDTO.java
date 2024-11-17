package com.example.gs_spring_energy.dto.response;

public record VentiladorResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Integer quantidadePas,
        Integer quantidadeVelocidades,
        String tipoVentilador,
        String temInclinacaoRegulavel
) {
}
