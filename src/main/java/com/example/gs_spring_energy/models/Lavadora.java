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
@Table(name = "gs_lavadoras")
public class Lavadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lavadora")
    private Long id;

    @OneToOne
    @JoinColumn(name = "eletrodomesticos_id", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

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