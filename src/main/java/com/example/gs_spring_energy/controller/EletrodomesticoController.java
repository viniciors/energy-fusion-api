package com.example.gs_spring_energy.controller;

import com.example.gs_spring_energy.dto.request.EletrodomesticoRequestDTO;
import com.example.gs_spring_energy.dto.response.EletrodomesticoResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.repositories.EletrodomesticoRepository;
import com.example.gs_spring_energy.service.EletrodomesticoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/eletrodomesticos")
public class EletrodomesticoController {

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @Autowired
    private EletrodomesticoMapper eletrodomesticoMapper;

    @PostMapping
    public ResponseEntity<EntityModel<EletrodomesticoResponseDTO>> criarEletrodomestico(@RequestBody EletrodomesticoRequestDTO dto) {
        Eletrodomestico eletrodomestico = eletrodomesticoMapper.requestToEletrodomestico(dto);
        Eletrodomestico salvo = eletrodomesticoRepository.save(eletrodomestico);

        EletrodomesticoResponseDTO response = eletrodomesticoMapper.eletrodomesticoToResponse(salvo, List.of(
                linkTo(methodOn(EletrodomesticoController.class).obterEletrodomestico(salvo.getId())).withSelfRel(),
                linkTo(methodOn(EletrodomesticoController.class).listarEletrodomesticos()).withRel("eletrodomesticos")
        ));

        return ResponseEntity.created(linkTo(methodOn(EletrodomesticoController.class).obterEletrodomestico(salvo.getId())).toUri())
                .body(EntityModel.of(response));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<EletrodomesticoResponseDTO>>> listarEletrodomesticos() {
        List<EntityModel<EletrodomesticoResponseDTO>> eletrodomesticos = eletrodomesticoRepository.findAll()
                .stream()
                .map(eletrodomestico -> {
                    EletrodomesticoResponseDTO response = eletrodomesticoMapper.eletrodomesticoToResponse(eletrodomestico, List.of(
                            linkTo(methodOn(EletrodomesticoController.class).obterEletrodomestico(eletrodomestico.getId())).withSelfRel(),
                            linkTo(methodOn(EletrodomesticoController.class).listarEletrodomesticos()).withRel("eletrodomesticos")
                    ));
                    return EntityModel.of(response);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(eletrodomesticos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EletrodomesticoResponseDTO>> obterEletrodomestico(@PathVariable Long id) {
        Eletrodomestico eletrodomestico = eletrodomesticoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Eletrodoméstico não encontrado com ID: " + id));
        EletrodomesticoResponseDTO response = eletrodomesticoMapper.eletrodomesticoToResponse(eletrodomestico, List.of(
                linkTo(methodOn(EletrodomesticoController.class).obterEletrodomestico(id)).withSelfRel(),
                linkTo(methodOn(EletrodomesticoController.class).listarEletrodomesticos()).withRel("eletrodomesticos")
        ));
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<EletrodomesticoResponseDTO>> atualizarEletrodomestico(
            @PathVariable Long id,
            @RequestBody EletrodomesticoRequestDTO dto) {
        Eletrodomestico existente = eletrodomesticoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Eletrodoméstico não encontrado com ID: " + id));
        Eletrodomestico atualizado = eletrodomesticoMapper.requestToEletrodomestico(dto);
        atualizado.setId(existente.getId());
        atualizado = eletrodomesticoRepository.save(atualizado);

        EletrodomesticoResponseDTO response = eletrodomesticoMapper.eletrodomesticoToResponse(atualizado, List.of(
                linkTo(methodOn(EletrodomesticoController.class).obterEletrodomestico(atualizado.getId())).withSelfRel(),
                linkTo(methodOn(EletrodomesticoController.class).listarEletrodomesticos()).withRel("eletrodomesticos")
        ));

        return ResponseEntity.ok(EntityModel.of(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEletrodomestico(@PathVariable Long id) {
        if (!eletrodomesticoRepository.existsById(id)) {
            throw new IllegalArgumentException("Eletrodoméstico não encontrado com ID: " + id);
        }
        eletrodomesticoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
