package com.example.gs_spring_energy.dto.request;

public record VentiladorRequestDTO(
        Integer quantidadePas,
        Integer quantidadeVelocidades,
        String tipoVentilador,
        String temInclinacaoRegulavel,
        EletrodomesticoRequestDTO eletrodomestico
) {}
