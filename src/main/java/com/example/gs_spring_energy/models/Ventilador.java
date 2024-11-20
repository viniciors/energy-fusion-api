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
@Table(name = "GS_Ventiladores")
public class Ventilador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVentilador")
    private Long id;

    @OneToOne
    @JoinColumn(name = "EletrodomesticoId", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "QuantidadePas")
    private Integer quantidadePas;

    @Column(name = "QuantidadeVelocidades")
    private Integer quantidadeVelocidades;

    @Column(name = "TipoVentilador")
    private String tipoVentilador;

    @Column(name = "TemInclinacaoRegulavel")
    private String temInclinacaoRegulavel;

}