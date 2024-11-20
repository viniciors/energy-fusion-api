package com.example.gs_spring_energy.dto.request;

public record MicroondasRequestDTO(
        Integer potenciaWatts,
        Integer quantidadeProgramas,
        Double diametroPrato,
        Integer frequencia,
        String temDescongelamento,
        EletrodomesticoRequestDTO eletrodomestico
) {}
