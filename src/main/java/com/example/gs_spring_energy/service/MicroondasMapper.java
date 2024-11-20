package com.example.gs_spring_energy.service;

import com.example.gs_spring_energy.dto.request.MicroondasRequestDTO;
import com.example.gs_spring_energy.dto.response.MicroondasResponseDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Microondas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroondasMapper {

    public Microondas requestToMicroondas(MicroondasRequestDTO dto) {
        Microondas microondas = new Microondas();
        microondas.setPotenciaWatts(dto.potenciaWatts());
        microondas.setQuantidadeProgramas(dto.quantidadeProgramas());
        microondas.setDiametroPrato(dto.diametroPrato());
        microondas.setFrequencia(dto.frequencia());
        microondas.setTemDescongelamento(dto.temDescongelamento());

        Eletrodomestico eletrodomestico = getEletrodomestico(dto);

        microondas.setEletrodomestico(eletrodomestico);

        return microondas;
    }

    private static Eletrodomestico getEletrodomestico(MicroondasRequestDTO dto) {
        Eletrodomestico eletrodomestico = new Eletrodomestico();
        eletrodomestico.setVoltagem(dto.eletrodomestico().voltagem());
        eletrodomestico.setMarca(dto.eletrodomestico().marca());
        eletrodomestico.setModelo(dto.eletrodomestico().modelo());
        eletrodomestico.setEficienciaEnergetica(dto.eletrodomestico().eficienciaEnergetica());
        eletrodomestico.setCor(dto.eletrodomestico().cor());
        eletrodomestico.setPeso(dto.eletrodomestico().peso());
        eletrodomestico.setLinkCompra(dto.eletrodomestico().linkCompra());
        return eletrodomestico;
    }

    public MicroondasResponseDTO microondasToResponse(Microondas entity, List<org.springframework.hateoas.Link> links) {
        Eletrodomestico eletrodomestico = entity.getEletrodomestico();
        EletrodomesticoResponseDTO eletrodomesticoDTO = new EletrodomesticoResponseDTO(
                eletrodomestico.getId(),
                eletrodomestico.getVoltagem(),
                eletrodomestico.getMarca(),
                eletrodomestico.getModelo(),
                eletrodomestico.getEficienciaEnergetica(),
                eletrodomestico.getCor(),
                eletrodomestico.getPeso(),
                eletrodomestico.getLinkCompra(),
                links
        );

        return new MicroondasResponseDTO(
                entity.getId(),
                eletrodomesticoDTO,
                entity.getPotenciaWatts(),
                entity.getQuantidadeProgramas(),
                entity.getDiametroPrato(),
                entity.getFrequencia(),
                entity.getTemDescongelamento(),
                links
        );
    }
}
