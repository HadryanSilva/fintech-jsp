package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.DespesaDAOImpl;
import br.com.fiap.fintech.dao.impl.InvestimentoDAOImpl;
import br.com.fiap.fintech.dao.impl.OperacaoDAOImpl;
import br.com.fiap.fintech.dao.impl.RecebimentoDAOImpl;
import br.com.fiap.fintech.enums.Categoria;
import br.com.fiap.fintech.enums.TipoOperacao;
import br.com.fiap.fintech.model.Recebimento;
import br.com.fiap.fintech.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public class OperacaoService {

    private final OperacaoDAOImpl operacaoDAO = new OperacaoDAOImpl();
    private final RecebimentoDAOImpl recebimentoDAO = new RecebimentoDAOImpl();
    private final InvestimentoDAOImpl investimentoDAO = new InvestimentoDAOImpl();
    private final DespesaDAOImpl despesaDAO = new DespesaDAOImpl();

    public void salvarRecebimento(HttpServletRequest request) {
        Recebimento recebimento = new Recebimento();
        Usuario user = getUserBySession(request);
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String montante = request.getParameter("montante");
        String categoria = request.getParameter("categoria");
        Integer contaId = user.getContaId();

        recebimento.setNome(nome);
        recebimento.setDescricao(descricao);
        recebimento.setMontante(Double.valueOf(montante));
        recebimento.setDataHora(new Date());
        recebimento.setContaId(contaId);
        recebimento.setTipoOperacao(TipoOperacao.RECEBIMENTO);
        recebimento.setCategoria(Categoria.valueOf(categoria));

        operacaoDAO.save(recebimento);
        recebimentoDAO.save(recebimento);
    }

    public void salvarInvestimento(HttpServletRequest request) {
        Usuario user = getUserBySession(request);
    }

    public void salvarDespesa(HttpServletRequest request) {
        Usuario user = getUserBySession(request);
    }

    private Usuario getUserBySession(HttpServletRequest request) {
        return (Usuario) request.getSession().getAttribute("user");

    }

}
