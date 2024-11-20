package com.example.gs_spring_energy.controller;

import com.example.gs_spring_energy.dto.request.LavadoraRequestDTO;
import com.example.gs_spring_energy.dto.response.LavadoraResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Lavadora;
import com.example.gs_spring_energy.repositories.EletrodomesticoRepository;
import com.example.gs_spring_energy.repositories.LavadoraRepository;
import com.example.gs_spring_energy.service.LavadoraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/lavadoras")
public class LavadoraController {

    @Autowired
    private LavadoraRepository lavadoraRepository;

    @Autowired
    private LavadoraMapper lavadoraMapper;

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;


    @PostMapping
    public ResponseEntity<EntityModel<LavadoraResponseDTO>> criarLavadora(@RequestBody LavadoraRequestDTO dto) {
        Lavadora lavadora = lavadoraMapper.requestToLavadora(dto);

        Eletrodomestico eletrodomesticoSalvo = eletrodomesticoRepository.save(lavadora.getEletrodomestico());
        lavadora.setEletrodomestico(eletrodomesticoSalvo);

        Lavadora lavadoraSalva = lavadoraRepository.save(lavadora);

        LavadoraResponseDTO response = lavadoraMapper.lavadoraToResponse(lavadoraSalva, List.of(
                linkTo(methodOn(LavadoraController.class).obterLavadora(lavadoraSalva.getId())).withSelfRel(),
                linkTo(methodOn(LavadoraController.class).listarLavadoras()).withRel("lavadoras")
        ));

        return ResponseEntity.created(linkTo(methodOn(LavadoraController.class).obterLavadora(lavadoraSalva.getId())).toUri())
                .body(EntityModel.of(response));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<LavadoraResponseDTO>>> listarLavadoras() {
        List<EntityModel<LavadoraResponseDTO>> lavadoras = lavadoraRepository.findAll()
                .stream()
                .map(lavadora -> {
                    LavadoraResponseDTO response = lavadoraMapper.lavadoraToResponse(lavadora, List.of(
                            linkTo(methodOn(LavadoraController.class).obterLavadora(lavadora.getId())).withSelfRel(),
                            linkTo(methodOn(LavadoraController.class).listarLavadoras()).withRel("lavadoras")
                    ));
                    return EntityModel.of(response);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(lavadoras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<LavadoraResponseDTO>> obterLavadora(@PathVariable Long id) {
        Lavadora lavadora = lavadoraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lavadora não encontrada com ID: " + id));
        LavadoraResponseDTO response = lavadoraMapper.lavadoraToResponse(lavadora, List.of(
                linkTo(methodOn(LavadoraController.class).obterLavadora(id)).withSelfRel(),
                linkTo(methodOn(LavadoraController.class).listarLavadoras()).withRel("lavadoras")
        ));
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<LavadoraResponseDTO>> atualizarLavadora(
            @PathVariable Long id,
            @RequestBody LavadoraRequestDTO dto) {
        Lavadora existente = lavadoraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lavadora não encontrada com ID: " + id));
        Lavadora atualizada = lavadoraMapper.requestToLavadora(dto);
        atualizada.setId(existente.getId());
        atualizada = lavadoraRepository.save(atualizada);

        LavadoraResponseDTO response = lavadoraMapper.lavadoraToResponse(atualizada, List.of(
                linkTo(methodOn(LavadoraController.class).obterLavadora(atualizada.getId())).withSelfRel(),
                linkTo(methodOn(LavadoraController.class).listarLavadoras()).withRel("lavadoras")
        ));

        return ResponseEntity.ok(EntityModel.of(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLavadora(@PathVariable Long id) {
        if (!lavadoraRepository.existsById(id)) {
            throw new IllegalArgumentException("Lavadora não encontrada com ID: " + id);
        }
        lavadoraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
