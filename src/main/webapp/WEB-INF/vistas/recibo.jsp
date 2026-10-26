<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="co.edu.sena.espm.modelo.Factura, co.edu.sena.espm.util.Formato" %>
<%
    Factura factura = (Factura) request.getAttribute("factura");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Recibo de pago</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <%@ include file="/WEB-INF/jspf/encabezado.jspf" %>

    <main class="contenedor">
        <h2>Recibo de pago</h2>

        <% if (error != null) { %>
            <p class="mensaje error"><%= Formato.escapar(error) %></p>
        <% } else { %>
            <p class="mensaje ok">El pago se registró correctamente.</p>
            <table class="tabla">
                <tr><th>Factura</th><td>${factura.numero}</td></tr>
                <tr><th>Cliente</th><td><%= Formato.escapar(factura.getCliente()) %></td></tr>
                <tr><th>Periodo</th><td>${factura.periodo}</td></tr>
                <tr><th>Valor pagado</th><td><%= Formato.moneda(factura.getValor()) %></td></tr>
                <tr><th>Titular del pago</th><td><%= Formato.escapar(factura.getTitularPago()) %></td></tr>
                <tr><th>Método de pago</th><td>${factura.metodoPago}</td></tr>
                <tr><th>Fecha y hora</th><td><%= Formato.fechaHora(factura.getFechaPago()) %></td></tr>
            </table>
        <% } %>

        <p><a class="boton" href="${pageContext.request.contextPath}/consulta">Consultar otra factura</a></p>
    </main>

    <jsp:include page="/WEB-INF/jspf/pie.jspf" />
</body>
</html>
