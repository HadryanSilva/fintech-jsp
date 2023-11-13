<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Contas</title>
</head>
<body>
<h1>Listagem de Contas</h1>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Data de Criação</th>
        <th>Saldo</th>
    </tr>
    <c:forEach var="conta" items="${contas}">
        <tr>
            <td>${conta.id}</td>
            <td>${conta.nome}</td>
            <td>${conta.descricao}</td>
            <td>${conta.dataCriacao}</td>
            <td>${conta.saldo}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
