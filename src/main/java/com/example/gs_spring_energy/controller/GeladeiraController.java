package com.example.gs_spring_energy.controller;

import com.example.gs_spring_energy.dto.request.GeladeiraRequestDTO;
import com.example.gs_spring_energy.dto.response.GeladeiraResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Geladeira;
import com.example.gs_spring_energy.repositories.EletrodomesticoRepository;
import com.example.gs_spring_energy.repositories.GeladeiraRepository;
import com.example.gs_spring_energy.service.GeladeiraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/geladeiras")
public class GeladeiraController {

    @Autowired
    private GeladeiraRepository geladeiraRepository;

    @Autowired
    private GeladeiraMapper geladeiraMapper;

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @PostMapping
    public ResponseEntity<EntityModel<GeladeiraResponseDTO>> criarGeladeira(@RequestBody GeladeiraRequestDTO dto) {
        Geladeira geladeira = geladeiraMapper.requestToGeladeira(dto);

        Eletrodomestico eletrodomesticoSalvo = eletrodomesticoRepository.save(geladeira.getEletrodomestico());
        geladeira.setEletrodomestico(eletrodomesticoSalvo);

        Geladeira geladeiraSalva = geladeiraRepository.save(geladeira);

        GeladeiraResponseDTO response = geladeiraMapper.geladeiraToResponse(geladeiraSalva, List.of(
                linkTo(methodOn(GeladeiraController.class).obterGeladeira(geladeiraSalva.getId())).withSelfRel(),
                linkTo(methodOn(GeladeiraController.class).listarGeladeiras()).withRel("geladeiras")
        ));

        return ResponseEntity.created(linkTo(methodOn(GeladeiraController.class).obterGeladeira(geladeiraSalva.getId())).toUri())
                .body(EntityModel.of(response));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<GeladeiraResponseDTO>>> listarGeladeiras() {
        List<EntityModel<GeladeiraResponseDTO>> geladeiras = geladeiraRepository.findAll()
                .stream()
                .map(geladeira -> geladeiraMapper.geladeiraToResponse(geladeira, List.of(
                        linkTo(methodOn(GeladeiraController.class).obterGeladeira(geladeira.getId())).withSelfRel(),
                        linkTo(methodOn(GeladeiraController.class).listarGeladeiras()).withRel("geladeiras")
                )))
                .map(EntityModel::of)
                .toList();

        return ResponseEntity.ok(geladeiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<GeladeiraResponseDTO>> obterGeladeira(@PathVariable Long id) {
        Geladeira geladeira = geladeiraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Geladeira não encontrada com ID: " + id));
        GeladeiraResponseDTO response = geladeiraMapper.geladeiraToResponse(geladeira, List.of(
                linkTo(methodOn(GeladeiraController.class).obterGeladeira(id)).withSelfRel(),
                linkTo(methodOn(GeladeiraController.class).listarGeladeiras()).withRel("geladeiras")
        ));
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<GeladeiraResponseDTO>> atualizarGeladeira(
            @PathVariable Long id,
            @RequestBody GeladeiraRequestDTO dto) {
        Geladeira existente = geladeiraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Geladeira não encontrada com ID: " + id));
        Geladeira atualizada = geladeiraMapper.requestToGeladeira(dto);
        atualizada.setId(existente.getId());
        atualizada = geladeiraRepository.save(atualizada);

        GeladeiraResponseDTO response = geladeiraMapper.geladeiraToResponse(atualizada, List.of(
                linkTo(methodOn(GeladeiraController.class).obterGeladeira(atualizada.getId())).withSelfRel(),
                linkTo(methodOn(GeladeiraController.class).listarGeladeiras()).withRel("geladeiras")
        ));

        return ResponseEntity.ok(EntityModel.of(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGeladeira(@PathVariable Long id) {
        if (!geladeiraRepository.existsById(id)) {
            throw new IllegalArgumentException("Geladeira não encontrada com ID: " + id);
        }
        geladeiraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
