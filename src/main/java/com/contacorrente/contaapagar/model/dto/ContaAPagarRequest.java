package com.contacorrente.contaapagar.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaAPagarRequest {
    @NotNull
    private String nome;
    @NotNull
    private Float valorOriginal;
    @NotNull
    private Date dataVencimento;
    @NotNull
    private Date dataPagamento;
}
