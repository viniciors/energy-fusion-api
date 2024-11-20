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
@Table(name = "GS_Microondas")
public class Microondas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMicroondas")
    private Long id;

    @OneToOne
    @JoinColumn(name = "EletrodomesticoId", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "PotenciaWatts")
    private Integer potenciaWatts;

    @Column(name = "QuantidadeProgramas")
    private Integer quantidadeProgramas;

    @Column(name = "DiametroPrato")
    private Double diametroPrato;

    @Column(name = "Frequencia")
    private Integer frequencia;

    @Column(name = "TemDescongelamento")
    private String temDescongelamento;

}