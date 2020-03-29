package com.contacorrente.contaapagar.service;

import com.contacorrente.contaapagar.model.ContaAPagarEntity;

import java.util.List;

public interface ContasAPagarService {
    public ContaAPagarEntity incluirConta(ContaAPagarEntity contaAPagar);
    public List<ContaAPagarEntity> listarContas();
}
