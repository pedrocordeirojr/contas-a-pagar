package com.contacorrente.contaapagar.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="contaAPagar")
@Builder
public class ContaAPagarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nome")
    @NotNull
    private String nome;

    @Column(name="valorOriginal")
    @NotNull
    private Float valorOriginal;

    @Column(name="valorCorrigido")
    @NotNull
    private Float valorCorrigido;

    @Column(name="quantidadeDiasAtraso")
    @NotNull
    private Integer quantidadeDiasAtraso;

    @Column(name="dataPagamento")
    @NotNull
    private LocalDate dataPagamento;

    @Column(name="dataVencimento")
    @NotNull
    private LocalDate dataVencimento;

    @Column(name="regra")
    private String regra;

    @Column(name="jurosDia")
    private Float jurosDia;

    @Column(name="multa")
    private Float multa;


}

