package com.contacorrente.contaapagar.controller;

import com.contacorrente.contaapagar.controller.mapper.ContasAPagarEntityMapper;
import com.contacorrente.contaapagar.model.dto.ContaAPagarRequest;
import com.contacorrente.contaapagar.model.dto.ContaAPagarResponse;
import com.contacorrente.contaapagar.service.impl.ContasAPagarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/contas-a-pagar")
public class ContasAPagarController {
    @Autowired
    private ContasAPagarServiceImpl contasAPagarService;

    @PostMapping
    public ContaAPagarResponse incluirConta(@RequestBody ContaAPagarRequest contaAPagarRequest){
        return ContasAPagarEntityMapper.from(
                contasAPagarService.incluirConta(
                        ContasAPagarEntityMapper.from(contaAPagarRequest)
                )
        );
    }

    @GetMapping
    public List<ContaAPagarResponse> listarContas(){
        return ContasAPagarEntityMapper.from(
                contasAPagarService.listarContas()
        );
    }
}
