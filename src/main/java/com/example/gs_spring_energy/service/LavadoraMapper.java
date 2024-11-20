package com.example.gs_spring_energy.service;

import com.example.gs_spring_energy.dto.request.LavadoraRequestDTO;
import com.example.gs_spring_energy.dto.response.LavadoraResponseDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Lavadora;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LavadoraMapper {

    public Lavadora requestToLavadora(LavadoraRequestDTO dto) {
        Lavadora lavadora = new Lavadora();
        lavadora.setCapacidadeKg(dto.capacidadeKg());
        lavadora.setVelocidadeCentrifugacaoRpm(dto.velocidadeCentrifugacaoRpm());
        lavadora.setConsumoAgua(dto.consumoAgua());
        lavadora.setSistemaLavagem(dto.sistemaLavagem());
        lavadora.setTemAguaQuente(dto.temAguaQuente());

        Eletrodomestico eletrodomestico = getEletrodomestico(dto);

        lavadora.setEletrodomestico(eletrodomestico);

        return lavadora;
    }

    private static Eletrodomestico getEletrodomestico(LavadoraRequestDTO dto) {
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

    public LavadoraResponseDTO lavadoraToResponse(Lavadora entity, List<org.springframework.hateoas.Link> links) {
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

        return new LavadoraResponseDTO(
                entity.getId(),
                eletrodomesticoDTO,
                entity.getCapacidadeKg(),
                entity.getConsumoAgua(),
                entity.getSistemaLavagem(),
                entity.getVelocidadeCentrifugacaoRpm(),
                entity.getTemAguaQuente(),
                links
        );
    }
}
