package com.example.gs_spring_energy.dto.response;

public record GeladeiraResponseDTO(
        Long id,
        EletrodomesticoResponseDTO eletrodomestico,
        Double capacidadeFreezerLitros,
        Double consumoKwh,
        Integer quantidadePortas,
        String tipoDisplay,
        String temPortaLatas
) {
}
