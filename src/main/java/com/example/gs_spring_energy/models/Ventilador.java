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
@Table(name = "GS_VENTILADORES")
@DiscriminatorValue("Ventilador")
public class Ventilador extends Eletrodomestico {

    @Column(name = "quantidade_pas")
    private Integer quantidadePas;

    @Column(name = "quantidade_velocidades")
    private Integer quantidadeVelocidades;

    @Column(name = "tipo_ventilador")
    private String tipoVentilador;

    @Column(name = "tem_inclinacao_regulavel")
    private String temInclinacaoRegulavel;

}