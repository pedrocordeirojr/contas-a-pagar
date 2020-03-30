package com.contacorrente.contaapagar.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaAPagarResponse {
    @NotNull
    private String nome;
    @NotNull
    private Float valorOriginal;
    @NotNull
    private Float valorCorrigido;
    @NotNull
    private Integer quantidadeDiasAtraso;
    @NotNull
    private LocalDate dataPagamento;
}
