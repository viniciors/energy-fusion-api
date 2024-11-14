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
@Table(name = "GS_MICROONDAS")
public class Microondas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_microondas")
    private Long id;

    @OneToOne
    @JoinColumn(name = "eletrodomesticos_id", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

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