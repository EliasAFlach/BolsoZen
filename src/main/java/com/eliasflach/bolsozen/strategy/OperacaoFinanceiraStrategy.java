package com.eliasflach.bolsozen.strategy;

import com.eliasflach.bolsozen.model.entity.ContaBancaria;
import java.math.BigDecimal;

public interface OperacaoFinanceiraStrategy {

    void calcularNovoSaldo(ContaBancaria conta, BigDecimal valor);
}
