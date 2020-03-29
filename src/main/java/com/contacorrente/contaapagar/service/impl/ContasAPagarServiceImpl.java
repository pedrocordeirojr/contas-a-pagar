package com.contacorrente.contaapagar.service.impl;

import com.contacorrente.contaapagar.model.ContaAPagarEntity;
import com.contacorrente.contaapagar.repository.ContasAPagarRepository;
import com.contacorrente.contaapagar.service.ContasAPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContasAPagarServiceImpl implements ContasAPagarService {

    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    @Override
    public ContaAPagarEntity incluirConta(ContaAPagarEntity contaAPagar) {
        return contasAPagarRepository.save(contaAPagar);
    }

    @Override
    public List<ContaAPagarEntity> listarContas() {
        return contasAPagarRepository.findAll();
    }
}
