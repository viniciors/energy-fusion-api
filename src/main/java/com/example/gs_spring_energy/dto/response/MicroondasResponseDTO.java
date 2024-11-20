package com.example.gs_spring_energy.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record MicroondasResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Integer potenciaWatts,
        Integer quantidadeProgramas,
        Double diametroPrato,
        Integer frequencia,
        String temDescongelamento,
        List<Link> links
) {}
