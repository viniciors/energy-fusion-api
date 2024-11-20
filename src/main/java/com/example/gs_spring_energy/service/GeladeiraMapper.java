package com.example.gs_spring_energy.service;

import com.example.gs_spring_energy.dto.request.GeladeiraRequestDTO;
import com.example.gs_spring_energy.dto.response.GeladeiraResponseDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Geladeira;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeladeiraMapper {

    public Geladeira requestToGeladeira(GeladeiraRequestDTO dto) {
        Geladeira geladeira = new Geladeira();
        geladeira.setCapacidadeFreezerLitros(dto.capacidadeFreezerLitros());
        geladeira.setConsumoKwh(dto.consumoKwh());
        geladeira.setQuantidadePortas(dto.quantidadePortas());
        geladeira.setTipoDisplay(dto.tipoDisplay());
        geladeira.setTemPortaLatas(dto.temPortaLatas());

        Eletrodomestico eletrodomestico = getEletrodomestico(dto);

        geladeira.setEletrodomestico(eletrodomestico);

        return geladeira;
    }

    private static Eletrodomestico getEletrodomestico(GeladeiraRequestDTO dto) {
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

    public GeladeiraResponseDTO geladeiraToResponse(Geladeira entity, List<org.springframework.hateoas.Link> links) {
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

        return new GeladeiraResponseDTO(
                entity.getId(),
                eletrodomesticoDTO,
                entity.getCapacidadeFreezerLitros(),
                entity.getConsumoKwh(),
                entity.getQuantidadePortas(),
                entity.getTipoDisplay(),
                entity.getTemPortaLatas(),
                links
        );
    }
}
