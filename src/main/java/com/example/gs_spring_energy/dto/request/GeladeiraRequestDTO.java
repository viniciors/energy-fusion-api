package com.example.gs_spring_energy.dto.request;

public record GeladeiraRequestDTO(
        Double capacidadeFreezerLitros,
        Double consumoKwh,
        Integer quantidadePortas,
        String tipoDisplay,
        String temPortaLatas,
        EletrodomesticoRequestDTO eletrodomestico
) {}
