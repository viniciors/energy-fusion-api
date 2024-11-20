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
@Table(name = "GS_Geladeiras")
public class Geladeira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGeladeira")
    private Long id;

    @OneToOne
    @JoinColumn(name = "EletrodomesticoId", referencedColumnName = "id")
    private Eletrodomestico eletrodomestico;

    @Column(name = "CapacidadeFreezerLitros")
    private Double capacidadeFreezerLitros;

    @Column(name = "ConsumoKwh")
    private Double consumoKwh;

    @Column(name = "QuantidadePortas")
    private Integer quantidadePortas;

    @Column(name = "TipoDisplay")
    private String tipoDisplay;

    @Column(name = "TemPortaLatas")
    private String temPortaLatas;

}
