package com.eliasflach.bolsozen.strategy;

import com.eliasflach.bolsozen.model.entity.ContaBancaria;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("DESPESA")
public class DespesaStrategy implements OperacaoFinanceiraStrategy {

    @Override
    public void calcularNovoSaldo(ContaBancaria conta, BigDecimal valor) {
        conta.setSaldo(conta.getSaldo().subtract(valor));
    }
}