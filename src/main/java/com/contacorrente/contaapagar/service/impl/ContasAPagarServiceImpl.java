package com.contacorrente.contaapagar.service.impl;

import com.contacorrente.contaapagar.message.ContaMessage;
import com.contacorrente.contaapagar.model.ContaAPagarEntity;
import com.contacorrente.contaapagar.repository.ContasAPagarRepository;
import com.contacorrente.contaapagar.service.ContasAPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContasAPagarServiceImpl implements ContasAPagarService {

    private final static String REGRA_ATE_TRES_DIAS = "até 3 dias";
    private final static float JUROS_ATE_TRES_DIAS = 0.1F;
    private final static float MULTA_ATE_TRES_DIAS = 2F;

    private final static String REGRA_SUPERIOR_TRES_DIAS = "superior a 3 dias";
    private final static float JUROS_SUPERIOR_TRES_DIAS = 0.2F;
    private final static float MULTA_SUPERIOR_TRES_DIAS = 3F;

    private final static String REGRA_SUPERIOR_CINCO_DIAS = "superior a 5 dias";
    private final static float JUROS_SUPERIOR_CINCO_DIAS = 0.3F;
    private final static float MULTA_SUPERIOR_CINCO_DIAS = 4F;

    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    @Override
    public ContaAPagarEntity incluirConta(ContaAPagarEntity contaAPagar) {
        try {
            validaCamposObrigatorios(contaAPagar);
            return contasAPagarRepository.save(calcularMulta(contaAPagar));
        } catch (Exception e) {
            throw ContaMessage.badRequest(ContaMessage.ERRO_SALVAR_CONTA, e.getMessage());
        }
    }

    @Override
    public List<ContaAPagarEntity> listarContas() {
        try {
            return contasAPagarRepository.findAll();
        } catch (Exception e) {
            throw ContaMessage.badRequest(ContaMessage.ERRO_LISTAR_CONTA, e.getMessage());
        }
    }

    private void validaCamposObrigatorios(ContaAPagarEntity contaAPagar) {

        if(contaAPagar == null){
            throw ContaMessage.business(ContaMessage.CONTA_INVALIDA);
        }

        if(contaAPagar.getNome() == null || contaAPagar.getNome().isEmpty()) {
            throw ContaMessage.business(ContaMessage.NOME_INVALIDO);
        }

        if(contaAPagar.getDataPagamento() == null) {
            throw ContaMessage.business(ContaMessage.PAGAMENTO_INVALIDA);
        }

        if(contaAPagar.getDataVencimento() == null) {
            throw ContaMessage.business(ContaMessage.VENCIMENTO_INVALIDO);
        }

        if(contaAPagar.getValorOriginal() == null) {
            throw ContaMessage.business(ContaMessage.VALOR_OBRIGATORIO);
        }

    }

    private ContaAPagarEntity calcularMulta(ContaAPagarEntity contaAPagarEntity) {
        if(contaAPagarEntity.getDataPagamento().isBefore(contaAPagarEntity.getDataVencimento())){
            contaAPagarEntity.setValorCorrigido(contaAPagarEntity.getValorOriginal());
            return contaAPagarEntity;
        }

        contaAPagarEntity.setQuantidadeDiasAtraso(contaAPagarEntity.getDataPagamento().compareTo(contaAPagarEntity.getDataVencimento()));

        if(contaAPagarEntity.getQuantidadeDiasAtraso() <= 3) {
            contaAPagarEntity.setRegra(REGRA_ATE_TRES_DIAS);
            contaAPagarEntity.setJurosDia(JUROS_ATE_TRES_DIAS);
            contaAPagarEntity.setMulta(MULTA_ATE_TRES_DIAS);
        }

        if(contaAPagarEntity.getQuantidadeDiasAtraso() <= 5) {
            contaAPagarEntity.setRegra(REGRA_SUPERIOR_TRES_DIAS);
            contaAPagarEntity.setJurosDia(JUROS_SUPERIOR_TRES_DIAS);
            contaAPagarEntity.setMulta(MULTA_SUPERIOR_TRES_DIAS);
        } else {
            contaAPagarEntity.setRegra(REGRA_SUPERIOR_CINCO_DIAS);
            contaAPagarEntity.setJurosDia(JUROS_SUPERIOR_CINCO_DIAS);
            contaAPagarEntity.setMulta(MULTA_SUPERIOR_CINCO_DIAS);
        }

        contaAPagarEntity.setValorCorrigido(calcularValorCorrigido(contaAPagarEntity));

        return contaAPagarEntity;

    }

    private Float calcularValorCorrigido (ContaAPagarEntity contaAPagarEntity) {
        return contaAPagarEntity.getValorOriginal()
                + (contaAPagarEntity.getValorOriginal() * contaAPagarEntity.getMulta() / 100)
                + (contaAPagarEntity.getValorOriginal() * (contaAPagarEntity.getJurosDia() * contaAPagarEntity.getQuantidadeDiasAtraso()) / 100);
    }
}
