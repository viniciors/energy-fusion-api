package com.example.gs_spring_energy.controller;

import com.example.gs_spring_energy.dto.request.CafeteiraRequestDTO;
import com.example.gs_spring_energy.dto.response.CafeteiraResponseDTO;
import com.example.gs_spring_energy.models.Cafeteira;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.repositories.CafeteiraRepository;
import com.example.gs_spring_energy.repositories.EletrodomesticoRepository;
import com.example.gs_spring_energy.service.CafeteiraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/cafeteiras")
public class CafeteiraController {

    @Autowired
    private CafeteiraRepository cafeteiraRepository;

    @Autowired
    private CafeteiraMapper cafeteiraMapper;

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @PostMapping
    public ResponseEntity<EntityModel<CafeteiraResponseDTO>> criarCafeteira(@RequestBody CafeteiraRequestDTO dto) {
        Cafeteira cafeteira = cafeteiraMapper.requestToCafeteira(dto);

        Eletrodomestico eletrodomesticoSalvo = eletrodomesticoRepository.save(cafeteira.getEletrodomestico());
        cafeteira.setEletrodomestico(eletrodomesticoSalvo);

        Cafeteira cafeteiraSalva = cafeteiraRepository.save(cafeteira);

        CafeteiraResponseDTO response = cafeteiraMapper.cafeteiraToResponse(cafeteiraSalva, List.of(
                linkTo(methodOn(CafeteiraController.class).obterCafeteira(cafeteiraSalva.getId())).withSelfRel(),
                linkTo(methodOn(CafeteiraController.class).listarCafeteiras()).withRel("cafeteiras")
        ));

        return ResponseEntity.created(linkTo(methodOn(CafeteiraController.class).obterCafeteira(cafeteiraSalva.getId())).toUri())
                .body(EntityModel.of(response));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CafeteiraResponseDTO>>> listarCafeteiras() {
        List<EntityModel<CafeteiraResponseDTO>> cafeteiras = cafeteiraRepository.findAll()
                .stream()
                .map(cafeteira -> cafeteiraMapper.cafeteiraToResponse(cafeteira, List.of(
                        linkTo(methodOn(CafeteiraController.class).obterCafeteira(cafeteira.getId())).withSelfRel(),
                        linkTo(methodOn(CafeteiraController.class).listarCafeteiras()).withRel("cafeteiras")
                )))
                .map(EntityModel::of)
                .toList();

        return ResponseEntity.ok(cafeteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CafeteiraResponseDTO>> obterCafeteira(@PathVariable Long id) {
        Cafeteira cafeteira = cafeteiraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cafeteira não encontrada com ID: " + id));
        CafeteiraResponseDTO response = cafeteiraMapper.cafeteiraToResponse(cafeteira, List.of(
                linkTo(methodOn(CafeteiraController.class).obterCafeteira(id)).withSelfRel(),
                linkTo(methodOn(CafeteiraController.class).listarCafeteiras()).withRel("cafeteiras")
        ));
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CafeteiraResponseDTO>> atualizarCafeteira(
            @PathVariable Long id,
            @RequestBody CafeteiraRequestDTO dto) {
        Cafeteira existente = cafeteiraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cafeteira não encontrada com ID: " + id));
        Cafeteira atualizada = cafeteiraMapper.requestToCafeteira(dto);
        atualizada.setId(existente.getId());
        atualizada = cafeteiraRepository.save(atualizada);

        CafeteiraResponseDTO response = cafeteiraMapper.cafeteiraToResponse(atualizada, List.of(
                linkTo(methodOn(CafeteiraController.class).obterCafeteira(atualizada.getId())).withSelfRel(),
                linkTo(methodOn(CafeteiraController.class).listarCafeteiras()).withRel("cafeteiras")
        ));

        return ResponseEntity.ok(EntityModel.of(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCafeteira(@PathVariable Long id) {
        if (!cafeteiraRepository.existsById(id)) {
            throw new IllegalArgumentException("Cafeteira não encontrada com ID: " + id);
        }
        cafeteiraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
