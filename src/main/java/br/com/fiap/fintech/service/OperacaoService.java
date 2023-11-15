package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.DespesaDAOImpl;
import br.com.fiap.fintech.dao.impl.InvestimentoDAOImpl;
import br.com.fiap.fintech.dao.impl.OperacaoDAOImpl;
import br.com.fiap.fintech.dao.impl.RecebimentoDAOImpl;
import jakarta.servlet.http.HttpServletRequest;

public class OperacaoService {

    private final OperacaoDAOImpl operacaoDAO = new OperacaoDAOImpl();
    private final RecebimentoDAOImpl recebimentoDAO = new RecebimentoDAOImpl();
    private final InvestimentoDAOImpl investimentoDAO = new InvestimentoDAOImpl();
    private final DespesaDAOImpl despesaDAO = new DespesaDAOImpl();

    public void salvarRecebimento(HttpServletRequest request) {

    }

    public void salvarInvestimento(HttpServletRequest request) {

    }

    public void salvarDespesa(HttpServletRequest request) {

    }

}
