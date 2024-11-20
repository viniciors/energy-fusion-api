package com.example.gs_spring_energy.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record CafeteiraResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Double capacidadeAgua,
        Double pressao,
        String capsulasCompativeis,
        String tecnologia,
        List<Link> links
) {}
