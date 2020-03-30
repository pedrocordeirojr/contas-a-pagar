package com.contacorrente.contaapagar.message;


import com.contacorrente.contaapagar.exception.BadRequestException;
import com.contacorrente.contaapagar.exception.BusinessException;

public enum ContaMessage {
    NOME_INVALIDO("O nome é um campo obrigatório"),
    VENCIMENTO_INVALIDO("A data de vencimento é obrigatória"),
    PAGAMENTO_INVALIDA("A data de pagamento é obrigatória"),
    VALOR_OBRIGATORIO("A data de pagamento é obrigatória"),
    CONTA_INVALIDA("Conta inválida"),
    ERRO_SALVAR_CONTA("Erro ao salvar conta (%s)"),
    ERRO_LISTAR_CONTA("Erro ao consultar contas a pagar (%s)");
    private String message;

    ContaMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return String.format("CONTAS-%04d", this.ordinal() + 1);
    }



    public static BusinessException business(ContaMessage inventoryMessages, Object... args) {
        return new BusinessException(String.format(inventoryMessages.getMessage(), args),
                inventoryMessages.getCode());
    }

    public static BusinessException business(ContaMessage inventoryMessages) {
        return new BusinessException(inventoryMessages.getMessage(), inventoryMessages.getCode());
    }


    public static BadRequestException badRequest(ContaMessage inventoryMessages, Object... args) {
        return new BadRequestException(String.format(inventoryMessages.getMessage(), args),
                inventoryMessages.getCode());
    }

    public static BadRequestException badRequest(ContaMessage inventoryMessages) {
        return new BadRequestException(inventoryMessages.getMessage(), inventoryMessages.getCode());
    }

}
