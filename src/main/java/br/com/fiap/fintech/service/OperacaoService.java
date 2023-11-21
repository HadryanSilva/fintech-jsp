package br.com.fiap.fintech.service;

import br.com.fiap.fintech.dao.impl.*;
import br.com.fiap.fintech.enums.Categoria;
import br.com.fiap.fintech.enums.TipoOperacao;
import br.com.fiap.fintech.model.*;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OperacaoService {

    private final OperacaoDAOImpl operacaoDAO = new OperacaoDAOImpl();
    private final RecebimentoDAOImpl recebimentoDAO = new RecebimentoDAOImpl();
    private final InvestimentoDAOImpl investimentoDAO = new InvestimentoDAOImpl();
    private final DespesaDAOImpl despesaDAO = new DespesaDAOImpl();
    private final ContaService contaService = new ContaService();

    public Conta salvarRecebimento(HttpServletRequest request) {
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
        return contaService.atualizaSaldo(user, recebimento);
    }

    public Conta salvarInvestimento(HttpServletRequest request) {
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
        return contaService.atualizaSaldo(user, investimento);
    }

    public Conta salvarDespesa(HttpServletRequest request) {
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
        return contaService.atualizaSaldo(user, despesa);
    }

    public double getTotalPorTipoOperacao(Integer id, TipoOperacao tipoOperacao) {
        List<Operacao> operacoes = operacaoDAO.findByConta(id, tipoOperacao);
        AtomicReference<Double> total = new AtomicReference<>(0.0);
        if (tipoOperacao.equals(TipoOperacao.RECEBIMENTO)) {
            operacoes.forEach(recebimento -> {
                total.updateAndGet(v -> v + recebimento.getMontante());
            });
        } else if (tipoOperacao.equals(TipoOperacao.DESPESA)) {
            operacoes.forEach(despesa -> {
                total.updateAndGet(v -> v + despesa.getMontante());
            });
        } else if (tipoOperacao.equals(TipoOperacao.INVESTIMENTO)) {
            operacoes.forEach(investimento -> {
                total.updateAndGet(v -> v + investimento.getMontante());
            });
        }
        return total.get();
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

    private void populaObjetoRecebimento(Recebimento recebimento) {
        Operacao operacaoSalva = operacaoDAO.save(recebimento);
        recebimento.setId(operacaoSalva.getId());
    }
    private void populaObjetoInvestimento(Investimento investimento) {
        Operacao operacaoSalva = operacaoDAO.save(investimento);
        investimento.setId(operacaoSalva.getId());
    }

    private void populaObjetoDespesa(Despesa despesa) {
        Operacao operacaoSalva = operacaoDAO.save(despesa);
        despesa.setId(operacaoSalva.getId());
    }
}
