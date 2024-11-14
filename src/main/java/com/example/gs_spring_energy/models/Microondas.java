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
@Table(name = "gs_microondas")
@DiscriminatorValue("Microondas")
public class Microondas extends Eletrodomestico {

    @Column(name = "potencia_watts")
    private Integer potenciaWatts;

    @Column(name = "quantidade_programas")
    private Integer quantidadeProgramas;

    @Column(name = "diametro_prato")
    private Double diametroPrato;

    @Column(name = "frequencia")
    private Integer frequencia;

    @Column(name = "tem_descongelamento")
    private String temDescongelamento;

}