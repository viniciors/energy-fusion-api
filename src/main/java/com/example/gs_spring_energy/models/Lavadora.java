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
@Table(name = "GS_Lavadoras")
public class Lavadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMaquina")
    private Long id;

    @OneToOne
    @JoinColumn(name = "EletrodomesticoId", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "CapacidadeKg")
    private Double capacidadeKg;

    @Column(name = "ConsumoAgua")
    private Double consumoAgua;

    @Column(name = "SistemaLavagem")
    private String sistemaLavagem;

    @Column(name = "VelocidadeCentrifugacaoRpm")
    private Integer velocidadeCentrifugacaoRpm;

    @Column(name = "TemAguaQuente")
    private String temAguaQuente;

}