package com.example.gs_spring_energy.dto.response;

import org.springframework.hateoas.Link;

import java.util.List;

public record GeladeiraResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Double capacidadeFreezerLitros,
        Double consumoKwh,
        Integer quantidadePortas,
        String tipoDisplay,
        String temPortaLatas,
        List<Link> links
) {}
