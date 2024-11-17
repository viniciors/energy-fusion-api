package com.example.gs_spring_energy.dto.request;

public record VentiladorRequestDTO(
        Long idEletrodomestico,
        Integer quantidadePas,
        Integer quantidadeVelocidades,
        String tipoVentilador,
        String temInclinacaoRegulavel
) {
}
