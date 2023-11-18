package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.DespesaDAOImpl;
import br.com.fiap.fintech.dao.impl.InvestimentoDAOImpl;
import br.com.fiap.fintech.dao.impl.OperacaoDAOImpl;
import br.com.fiap.fintech.dao.impl.RecebimentoDAOImpl;
import br.com.fiap.fintech.enums.Categoria;
import br.com.fiap.fintech.enums.TipoOperacao;
import br.com.fiap.fintech.model.*;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String data = request.getParameter("data");
        Integer contaId = user.getContaId();

        recebimento.setNome(nome);
        recebimento.setDescricao(descricao);
        recebimento.setMontante(Double.valueOf(montante));
        recebimento.setDataHora(getDateFromString(data));
        recebimento.setContaId(contaId);
        recebimento.setTipoOperacao(TipoOperacao.RECEBIMENTO);
        recebimento.setCategoria(Categoria.valueOf(categoria.toUpperCase()));

        populaObjetoRecebimento(recebimento);
        recebimentoDAO.save(recebimento);
    }

    public void salvarInvestimento(HttpServletRequest request) {
        Investimento investimento = new Investimento();
        Usuario user = getUserBySession(request);
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String montante = request.getParameter("montante");
        String categoria = request.getParameter("categoria");
        String data = request.getParameter("data");
        Integer contaId = user.getContaId();

        investimento.setNome(nome);
        investimento.setDescricao(descricao);
        investimento.setMontante(Double.valueOf(montante));
        investimento.setDataHora(getDateFromString(data));
        investimento.setContaId(contaId);
        investimento.setTipoOperacao(TipoOperacao.INVESTIMENTO);
        investimento.setCategoria(Categoria.valueOf(categoria.toUpperCase()));

        populaObjetoInvestimento(investimento);
        investimentoDAO.save(investimento);
    }

    public void salvarDespesa(HttpServletRequest request) {
        Despesa despesa = new Despesa();
        Usuario user = getUserBySession(request);
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String montante = request.getParameter("montante");
        String categoria = request.getParameter("categoria");
        String data = request.getParameter("data");
        Integer contaId = user.getContaId();

        despesa.setNome(nome);
        despesa.setDescricao(descricao);
        despesa.setMontante(Double.valueOf(montante));
        despesa.setDataHora(getDateFromString(data));
        despesa.setContaId(contaId);
        despesa.setTipoOperacao(TipoOperacao.DESPESA);
        despesa.setCategoria(Categoria.valueOf(categoria.toUpperCase()));

        populaObjetoDespesa(despesa);
        despesaDAO.save(despesa);
    }

    private Usuario getUserBySession(HttpServletRequest request) {
        return (Usuario) request.getSession().getAttribute("user");

    }

    private Date getDateFromString(String dataString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            return sdf.parse(dataString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public void populaObjetoRecebimento(Recebimento recebimento) {
        Operacao operacaoSalva = operacaoDAO.save(recebimento);
        recebimento.setId(operacaoSalva.getId());
    }
    public void populaObjetoInvestimento(Investimento investimento) {
        Operacao operacaoSalva = operacaoDAO.save(investimento);
        investimento.setId(operacaoSalva.getId());
    }

    public void populaObjetoDespesa(Despesa despesa) {
        Operacao operacaoSalva = operacaoDAO.save(despesa);
        despesa.setId(operacaoSalva.getId());
    }

}
