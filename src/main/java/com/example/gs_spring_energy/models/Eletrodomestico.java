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
@Table(name = "GS_Eletrodomesticos")
public class Eletrodomestico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Voltagem")
    private String voltagem;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Modelo")
    private String modelo;

    @Column(name = "EficienciaEnergetica")
    private String eficienciaEnergetica;

    @Column(name = "Cor")
    private String cor;

    @Column(name = "Peso")
    private Double peso;

    @Column(name = "LinkCompra")
    private String linkCompra;
}