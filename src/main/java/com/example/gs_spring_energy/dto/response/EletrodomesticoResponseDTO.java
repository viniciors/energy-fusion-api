package com.example.gs_spring_energy.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record EletrodomesticoResponseDTO(
        Long id,
        String voltagem,
        String marca,
        String modelo,
        String eficienciaEnergetica,
        String cor,
        Double peso,
        String linkCompra,
        List<Link> links
) {}
