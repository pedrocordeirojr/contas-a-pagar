package com.contacorrente.contaapagar.controller.mapper;

import com.contacorrente.contaapagar.model.ContaAPagarEntity;
import com.contacorrente.contaapagar.model.dto.ContaAPagarRequest;
import com.contacorrente.contaapagar.model.dto.ContaAPagarResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ContasAPagarEntityMapper {
    private ContasAPagarEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ContaAPagarEntity from (ContaAPagarRequest contaAPagarRequest) {
        return ContaAPagarEntity.builder().nome(contaAPagarRequest.getNome())
                .dataPagamento(contaAPagarRequest.getDataPagamento())
                .valorOriginal(contaAPagarRequest.getValorOriginal())
                .dataVencimento(contaAPagarRequest.getDataVencimento())
                .build();

    }

    public static ContaAPagarResponse from (ContaAPagarEntity contaAPagarEntity) {
        return ContaAPagarResponse.builder().dataPagamento(contaAPagarEntity.getDataPagamento())
                .nome(contaAPagarEntity.getNome())
                .quantidadeDiasAtraso(contaAPagarEntity.getQuantidadeDiasAtraso())
                .valorCorrigido(contaAPagarEntity.getValorCorrigido())
                .valorOriginal(contaAPagarEntity.getValorOriginal())
                .build();
    }



    public static List<ContaAPagarResponse> from (List<ContaAPagarEntity> contaAPagarEntities) {
        return contaAPagarEntities.stream().map(ContasAPagarEntityMapper::from).collect(Collectors.toList());
    }

}
