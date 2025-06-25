package com.eliasflach.bolsozen.service.impl;

import com.eliasflach.bolsozen.model.entity.ContaBancaria;
import com.eliasflach.bolsozen.model.entity.Transacao;
import com.eliasflach.bolsozen.model.enums.TipoTransacao;
import com.eliasflach.bolsozen.exceptions.BusinessException;
import com.eliasflach.bolsozen.exceptions.ResourceNotFoundException;
import com.eliasflach.bolsozen.factory.OperacaoFinanceiraFactory;
import com.eliasflach.bolsozen.repository.ContaBancariaRepository;
import com.eliasflach.bolsozen.repository.TransacaoRepository;
import com.eliasflach.bolsozen.service.TransacaoService;
import com.eliasflach.bolsozen.strategy.OperacaoFinanceiraStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private OperacaoFinanceiraFactory operacaoFactory;

    @Override
    @Transactional
    public Transacao createTransacao(Transacao transacao) {
        validarTransacao(transacao);
        Transacao novaTransacao = transacaoRepository.save(transacao);
        aplicarImpactoFinanceiro(novaTransacao, novaTransacao.getTipo());
        return novaTransacao;
    }

    @Override
    @Transactional
    public Transacao updateTransacao(String id, Transacao transacaoDetails) {
        Transacao transacaoExistente = findTransacaoById(id);

        TipoTransacao tipoReverso = transacaoExistente.getTipo() == TipoTransacao.RECEITA ? TipoTransacao.DESPESA : TipoTransacao.RECEITA;
        aplicarImpactoFinanceiro(transacaoExistente, tipoReverso);

        transacaoExistente.setDescricao(transacaoDetails.getDescricao());
        transacaoExistente.setValor(transacaoDetails.getValor());
        transacaoExistente.setData(transacaoDetails.getData());
        transacaoExistente.setTipo(transacaoDetails.getTipo());
        transacaoExistente.setPaga(transacaoDetails.getPaga());
        transacaoExistente.setIdCategoria(transacaoDetails.getIdCategoria());
        transacaoExistente.setIdContaBancaria(transacaoDetails.getIdContaBancaria());
        transacaoExistente.setIdCartaoCredito(transacaoDetails.getIdCartaoCredito());

        validarTransacao(transacaoExistente);

        Transacao transacaoAtualizada = transacaoRepository.save(transacaoExistente);

        aplicarImpactoFinanceiro(transacaoAtualizada, transacaoAtualizada.getTipo());

        return transacaoAtualizada;
    }

    @Override
    @Transactional
    public void deleteTransacao(String id) {
        Transacao transacao = findTransacaoById(id);
        TipoTransacao tipoReverso = transacao.getTipo() == TipoTransacao.RECEITA ? TipoTransacao.DESPESA : TipoTransacao.RECEITA;
        aplicarImpactoFinanceiro(transacao, tipoReverso);
        transacaoRepository.delete(transacao);
    }

    @Override
    public List<Transacao> findTransacoesByUsuario(String idUsuario) {
        return transacaoRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public Transacao findTransacaoById(String id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada com o id: " + id));
    }

    private void aplicarImpactoFinanceiro(Transacao transacao, TipoTransacao tipoOperacao) {
        if (transacao.getIdContaBancaria() == null) {
            return;
        }
        ContaBancaria conta = contaBancariaRepository.findById(transacao.getIdContaBancaria())
                .orElseThrow(() -> new BusinessException("Conta bancária associada à transação não foi encontrada."));
        OperacaoFinanceiraStrategy strategy = operacaoFactory.getStrategy(tipoOperacao);
        strategy.calcularNovoSaldo(conta, transacao.getValor());
        contaBancariaRepository.save(conta);
    }

    private void validarTransacao(Transacao transacao) {
        if (transacao.getIdContaBancaria() == null && transacao.getIdCartaoCredito() == null) {
            throw new BusinessException("A transação deve estar associada a uma conta bancária ou a um cartão de crédito.");
        }
        if (transacao.getIdContaBancaria() != null && transacao.getIdCartaoCredito() != null) {
            throw new BusinessException("A transação não pode estar associada a uma conta e um cartão simultaneamente.");
        }
    }
}