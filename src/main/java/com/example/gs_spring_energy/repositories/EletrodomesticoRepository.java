package com.example.gs_spring_energy.repositories;

import com.example.gs_spring_energy.models.Eletrodomestico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EletrodomesticoRepository extends JpaRepository<Eletrodomestico, Long> {
}
