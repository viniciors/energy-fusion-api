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
@Table(name = "GS_CAFETEIRAS")
public class Cafeteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cafeteira")
    private Long id;

    @OneToOne
    @JoinColumn(name = "eletrodomesticos_id", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "capacidade_agua")
    private Double capacidadeAgua;

    @Column(name = "pressao")
    private Double pressao;

    @Column(name = "capsulas_compativeis")
    private String capsulasCompativeis;

    @Column(name = "tecnologia")
    private String tecnologia;
}
