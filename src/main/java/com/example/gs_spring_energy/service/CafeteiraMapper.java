package com.example.gs_spring_energy.service;

import com.example.gs_spring_energy.dto.request.CafeteiraRequestDTO;
import com.example.gs_spring_energy.dto.response.CafeteiraResponseDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Cafeteira;
import com.example.gs_spring_energy.models.Eletrodomestico;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeteiraMapper {

    public Cafeteira requestToCafeteira(CafeteiraRequestDTO dto) {
        Cafeteira cafeteira = new Cafeteira();
        cafeteira.setCapacidadeAgua(dto.capacidadeAgua());
        cafeteira.setPressao(dto.pressao());
        cafeteira.setCapsulasCompativeis(dto.capsulasCompativeis());
        cafeteira.setTecnologia(dto.tecnologia());

        Eletrodomestico eletrodomestico = getEletrodomestico(dto);

        cafeteira.setEletrodomestico(eletrodomestico);

        return cafeteira;
    }

    private static Eletrodomestico getEletrodomestico(CafeteiraRequestDTO dto) {
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

    public CafeteiraResponseDTO cafeteiraToResponse(Cafeteira entity, List<org.springframework.hateoas.Link> links) {
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

        return new CafeteiraResponseDTO(
                entity.getId(),
                eletrodomesticoDTO,
                entity.getCapacidadeAgua(),
                entity.getPressao(),
                entity.getCapsulasCompativeis(),
                entity.getTecnologia(),
                links
        );
    }
}
