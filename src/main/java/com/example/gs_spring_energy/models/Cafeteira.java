package com.example.gs_spring_energy.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gs_cafeteiras")
@DiscriminatorValue("Cafeteira")
public class Cafeteira extends Eletrodomestico {

    @Column(name = "capacidade_agua")
    private Double capacidadeAgua;

    @Column(name = "pressao")
    private Double pressao;

    @Column(name = "capsulas_compativeis")
    private String capsulasCompativeis;

    @Column(name = "tecnologia")
    private String tecnologia;
}
