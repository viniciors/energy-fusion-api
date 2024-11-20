package com.example.gs_spring_energy.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record VentiladorResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Integer quantidadePas,
        Integer quantidadeVelocidades,
        String tipoVentilador,
        String temInclinacaoRegulavel,
        List<Link> links
) {}
