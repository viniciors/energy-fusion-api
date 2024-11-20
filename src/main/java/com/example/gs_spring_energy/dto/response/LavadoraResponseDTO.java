package com.example.gs_spring_energy.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record LavadoraResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Double capacidadeKg,
        Double consumoAgua,
        String sistemaLavagem,
        Integer velocidadeCentrifugacaoRpm,
        String temAguaQuente,
        List<Link> links
) {}
