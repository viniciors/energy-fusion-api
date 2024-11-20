package com.example.gs_spring_energy.service;

import com.example.gs_spring_energy.dto.request.VentiladorRequestDTO;
import com.example.gs_spring_energy.dto.response.VentiladorResponseDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Ventilador;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentiladorMapper {

    public Ventilador requestToVentilador(VentiladorRequestDTO dto) {
        Ventilador ventilador = new Ventilador();
        ventilador.setQuantidadePas(dto.quantidadePas());
        ventilador.setQuantidadeVelocidades(dto.quantidadeVelocidades());
        ventilador.setTipoVentilador(dto.tipoVentilador());
        ventilador.setTemInclinacaoRegulavel(dto.temInclinacaoRegulavel());

        Eletrodomestico eletrodomestico = new Eletrodomestico();
        eletrodomestico.setVoltagem(dto.eletrodomestico().voltagem());
        eletrodomestico.setMarca(dto.eletrodomestico().marca());
        eletrodomestico.setModelo(dto.eletrodomestico().modelo());
        eletrodomestico.setEficienciaEnergetica(dto.eletrodomestico().eficienciaEnergetica());
        eletrodomestico.setCor(dto.eletrodomestico().cor());
        eletrodomestico.setPeso(dto.eletrodomestico().peso());
        eletrodomestico.setLinkCompra(dto.eletrodomestico().linkCompra());

        ventilador.setEletrodomestico(eletrodomestico);

        return ventilador;
    }

    public VentiladorResponseDTO ventiladorToResponse(Ventilador entity, List<org.springframework.hateoas.Link> links) {
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

        return new VentiladorResponseDTO(
                entity.getId(),
                eletrodomesticoDTO,
                entity.getQuantidadePas(),
                entity.getQuantidadeVelocidades(),
                entity.getTipoVentilador(),
                entity.getTemInclinacaoRegulavel(),
                links
        );
    }
}
