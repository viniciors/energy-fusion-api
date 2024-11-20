package com.example.gs_spring_energy.service;

import com.example.gs_spring_energy.dto.request.EletrodomesticoRequestDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EletrodomesticoMapper {

    public Eletrodomestico requestToEletrodomestico(EletrodomesticoRequestDTO dto) {
        Eletrodomestico eletrodomestico = new Eletrodomestico();
        eletrodomestico.setVoltagem(dto.voltagem());
        eletrodomestico.setMarca(dto.marca());
        eletrodomestico.setModelo(dto.modelo());
        eletrodomestico.setEficienciaEnergetica(dto.eficienciaEnergetica());
        eletrodomestico.setCor(dto.cor());
        eletrodomestico.setPeso(dto.peso());
        eletrodomestico.setLinkCompra(dto.linkCompra());
        return eletrodomestico;
    }

    public EletrodomesticoResponseDTO eletrodomesticoToResponse(Eletrodomestico entity, List<Link> links) {
        return new EletrodomesticoResponseDTO(
                entity.getId(),
                entity.getVoltagem(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getEficienciaEnergetica(),
                entity.getCor(),
                entity.getPeso(),
                entity.getLinkCompra(),
                links
        );
    }
}
