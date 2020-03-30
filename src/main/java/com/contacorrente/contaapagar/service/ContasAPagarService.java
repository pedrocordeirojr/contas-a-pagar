package com.contacorrente.contaapagar.service;

import com.contacorrente.contaapagar.model.ContaAPagarEntity;

import java.util.List;

public interface ContasAPagarService {
    ContaAPagarEntity incluirConta(ContaAPagarEntity contaAPagar);
    List<ContaAPagarEntity> listarContas();
}
