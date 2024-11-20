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
@Table(name = "GS_Cafeteiras")
public class Cafeteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCafeteira")
    private Long id;

    @OneToOne
    @JoinColumn(name = "EletrodomesticoId", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "CapacidadeAgua")
    private Double capacidadeAgua;

    @Column(name = "Pressao")
    private Double pressao;

    @Column(name = "CapsulasCompativeis")
    private String capsulasCompativeis;

    @Column(name = "Tecnologia")
    private String tecnologia;
}
