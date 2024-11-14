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
@Table(name = "GS_GELADEIRAS")
public class Geladeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_geladeira")
    private Long id;

    @OneToOne
    @JoinColumn(name = "eletrodomesticos_id", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

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
