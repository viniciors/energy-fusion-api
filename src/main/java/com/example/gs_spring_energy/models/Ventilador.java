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
@Table(name = "gs_ventiladores")
public class Ventilador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ventilador")
    private Long id;

    @OneToOne
    @JoinColumn(name = "eletrodomesticos_id", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "quantidade_pas")
    private Integer quantidadePas;

    @Column(name = "quantidade_velocidades")
    private Integer quantidadeVelocidades;

    @Column(name = "tipo_ventilador")
    private String tipoVentilador;

    @Column(name = "tem_inclinacao_regulavel")
    private String temInclinacaoRegulavel;

}