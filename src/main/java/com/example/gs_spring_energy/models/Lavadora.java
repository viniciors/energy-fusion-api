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
@Table(name = "gs_lavadoras")
@DiscriminatorValue("Lavadora")
public class Lavadora extends Eletrodomestico {

    @Column(name = "capacidade_kg")
    private Double capacidadeKg;

    @Column(name = "consumo_agua")
    private Double consumoAgua;

    @Column(name = "sistema_lavagem")
    private String sistemaLavagem;

    @Column(name = "velocidade_centrifugacao_rpm")
    private Integer velocidadeCentrifugacaoRpm;

    @Column(name = "tem_agua_quente")
    private String temAguaQuente;

}
