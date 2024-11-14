package com.example.gs_spring_energy.repositories;

import com.example.gs_spring_energy.models.Ventilador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentiladorRepository extends JpaRepository<Ventilador, Long> {
}
