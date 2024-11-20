package com.example.gs_spring_energy.controller;

import com.example.gs_spring_energy.dto.request.MicroondasRequestDTO;
import com.example.gs_spring_energy.dto.response.MicroondasResponseDTO;
import com.example.gs_spring_energy.models.Eletrodomestico;
import com.example.gs_spring_energy.models.Microondas;
import com.example.gs_spring_energy.repositories.EletrodomesticoRepository;
import com.example.gs_spring_energy.repositories.MicroondasRepository;
import com.example.gs_spring_energy.service.MicroondasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/microondas")
public class MicroondasController {

    @Autowired
    private MicroondasRepository microondasRepository;

    @Autowired
    private MicroondasMapper microondasMapper;

    @Autowired
    private EletrodomesticoRepository eletrodomesticoRepository;

    @PostMapping
    public ResponseEntity<EntityModel<MicroondasResponseDTO>> criarMicroondas(@RequestBody MicroondasRequestDTO dto) {
        Microondas microondas = microondasMapper.requestToMicroondas(dto);

        Eletrodomestico eletrodomesticoSalvo = eletrodomesticoRepository.save(microondas.getEletrodomestico());
        microondas.setEletrodomestico(eletrodomesticoSalvo);

        Microondas microondasSalvo = microondasRepository.save(microondas);

        MicroondasResponseDTO response = microondasMapper.microondasToResponse(microondasSalvo, List.of(
                linkTo(methodOn(MicroondasController.class).obterMicroondas(microondasSalvo.getId())).withSelfRel(),
                linkTo(methodOn(MicroondasController.class).listarMicroondas()).withRel("microondas")
        ));

        return ResponseEntity.created(linkTo(methodOn(MicroondasController.class).obterMicroondas(microondasSalvo.getId())).toUri())
                .body(EntityModel.of(response));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<MicroondasResponseDTO>>> listarMicroondas() {
        List<EntityModel<MicroondasResponseDTO>> microondas = microondasRepository.findAll()
                .stream()
                .map(microondasEntity -> {
                    MicroondasResponseDTO response = microondasMapper.microondasToResponse(microondasEntity, List.of(
                            linkTo(methodOn(MicroondasController.class).obterMicroondas(microondasEntity.getId())).withSelfRel(),
                            linkTo(methodOn(MicroondasController.class).listarMicroondas()).withRel("microondas")
                    ));
                    return EntityModel.of(response);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(microondas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<MicroondasResponseDTO>> obterMicroondas(@PathVariable Long id) {
        Microondas microondas = microondasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Microondas não encontrado com ID: " + id));
        MicroondasResponseDTO response = microondasMapper.microondasToResponse(microondas, List.of(
                linkTo(methodOn(MicroondasController.class).obterMicroondas(id)).withSelfRel(),
                linkTo(methodOn(MicroondasController.class).listarMicroondas()).withRel("microondas")
        ));
        return ResponseEntity.ok(EntityModel.of(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<MicroondasResponseDTO>> atualizarMicroondas(
            @PathVariable Long id,
            @RequestBody MicroondasRequestDTO dto) {
        Microondas existente = microondasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Microondas não encontrado com ID: " + id));
        Microondas atualizado = microondasMapper.requestToMicroondas(dto);
        atualizado.setId(existente.getId());
        atualizado = microondasRepository.save(atualizado);

        MicroondasResponseDTO response = microondasMapper.microondasToResponse(atualizado, List.of(
                linkTo(methodOn(MicroondasController.class).obterMicroondas(atualizado.getId())).withSelfRel(),
                linkTo(methodOn(MicroondasController.class).listarMicroondas()).withRel("microondas")
        ));

        return ResponseEntity.ok(EntityModel.of(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMicroondas(@PathVariable Long id) {
        if (!microondasRepository.existsById(id)) {
            throw new IllegalArgumentException("Microondas não encontrado com ID: " + id);
        }
        microondasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
