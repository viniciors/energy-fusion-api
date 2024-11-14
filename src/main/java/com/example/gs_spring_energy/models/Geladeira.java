package com.example.gs_spring_energy.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gs_geladeiras")
@DiscriminatorValue("Geladeira")
public class Geladeira extends Eletrodomestico {

    @Column(name = "capacidade_freezer_litros")
    private Double capacidadeFreezerLitros;

    @Column(name = "consumo_kwh")
    private Double consumoKwh;

    @Column(name = "quantidade_portas")
    private Integer quantidadePortas;

    @Column(name = "tipo_display")
    private String tipoDisplay;

    @Column(name = "tem_porta_latas")
    private String temPortaLatas;

}
