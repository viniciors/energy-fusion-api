package com.example.gs_spring_energy.controller;

import com.example.gs_spring_energy.dto.request.VentiladorRequestDTO;
import com.example.gs_spring_energy.dto.response.VentiladorResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Ventilador;
import com.example.gs_spring_energy.repositories.EletrodomesticoRepository;
import com.example.gs_spring_energy.repositories.VentiladorRepository;
import com.example.gs_spring_energy.service.VentiladorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/ventiladores")
public class VentiladorController {

    @Autowired
    private VentiladorRepository ventiladorRepository;

    @Autowired
    private VentiladorMapper ventiladorMapper;

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;


    @PostMapping
    public ResponseEntity<EntityModel<VentiladorResponseDTO>> criarVentilador(@RequestBody VentiladorRequestDTO dto) {
        Ventilador ventilador = ventiladorMapper.requestToVentilador(dto);
        Eletrodomestico eletrodomesticoSalvo = eletrodomesticoRepository.save(ventilador.getEletrodomestico());
        ventilador.setEletrodomestico(eletrodomesticoSalvo);

        Ventilador ventiladorSalvo = ventiladorRepository.save(ventilador);

        VentiladorResponseDTO response = ventiladorMapper.ventiladorToResponse(ventiladorSalvo, List.of(
                linkTo(methodOn(VentiladorController.class).obterVentilador(ventiladorSalvo.getId())).withSelfRel(),
                linkTo(methodOn(VentiladorController.class).listarVentiladores()).withRel("ventiladores")
        ));

        return ResponseEntity.created(linkTo(methodOn(VentiladorController.class).obterVentilador(ventiladorSalvo.getId())).toUri())
                .body(EntityModel.of(response));
    }


    @GetMapping
    public ResponseEntity<List<EntityModel<VentiladorResponseDTO>>> listarVentiladores() {
        List<EntityModel<VentiladorResponseDTO>> ventiladores = ventiladorRepository.findAll()
                .stream()
                .map(ventiladorEntity -> {
                    VentiladorResponseDTO response = ventiladorMapper.ventiladorToResponse(ventiladorEntity, List.of(
                            linkTo(methodOn(VentiladorController.class).obterVentilador(ventiladorEntity.getId())).withSelfRel(),
                            linkTo(methodOn(VentiladorController.class).listarVentiladores()).withRel("ventiladores")
                    ));
                    return EntityModel.of(response);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(ventiladores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<VentiladorResponseDTO>> obterVentilador(@PathVariable Long id) {
        Ventilador ventilador = ventiladorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ventilador não encontrado com ID: " + id));
        VentiladorResponseDTO response = ventiladorMapper.ventiladorToResponse(ventilador, List.of(
                linkTo(methodOn(VentiladorController.class).obterVentilador(id)).withSelfRel(),
                linkTo(methodOn(VentiladorController.class).listarVentiladores()).withRel("ventiladores")
        ));
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<VentiladorResponseDTO>> atualizarVentilador(
            @PathVariable Long id,
            @RequestBody VentiladorRequestDTO dto) {
        Ventilador existente = ventiladorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ventilador não encontrado com ID: " + id));
        Ventilador atualizado = ventiladorMapper.requestToVentilador(dto);
        atualizado.setId(existente.getId());
        atualizado = ventiladorRepository.save(atualizado);

        VentiladorResponseDTO response = ventiladorMapper.ventiladorToResponse(atualizado, List.of(
                linkTo(methodOn(VentiladorController.class).obterVentilador(atualizado.getId())).withSelfRel(),
                linkTo(methodOn(VentiladorController.class).listarVentiladores()).withRel("ventiladores")
        ));

        return ResponseEntity.ok(EntityModel.of(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVentilador(@PathVariable Long id) {
        if (!ventiladorRepository.existsById(id)) {
            throw new IllegalArgumentException("Ventilador não encontrado com ID: " + id);
        }
        ventiladorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
