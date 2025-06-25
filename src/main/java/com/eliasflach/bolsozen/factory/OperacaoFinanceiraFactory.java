package com.eliasflach.bolsozen.factory;

import com.eliasflach.bolsozen.model.enums.TipoTransacao;
import com.eliasflach.bolsozen.exceptions.BusinessException;
import com.eliasflach.bolsozen.strategy.OperacaoFinanceiraStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OperacaoFinanceiraFactory {

    private final Map<String, OperacaoFinanceiraStrategy> strategies;

    @Autowired
    public OperacaoFinanceiraFactory(Map<String, OperacaoFinanceiraStrategy> strategies) {
        this.strategies = strategies;
    }

    public OperacaoFinanceiraStrategy getStrategy(TipoTransacao tipo) {
        OperacaoFinanceiraStrategy strategy = strategies.get(tipo.name());
        if (strategy == null) {
            throw new BusinessException("Estratégia não encontrada para o tipo de transação: " + tipo);
        }
        return strategy;
    }
}