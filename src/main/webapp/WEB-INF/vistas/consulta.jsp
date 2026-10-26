<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="co.edu.sena.espm.modelo.Factura, co.edu.sena.espm.util.Formato" %>

<%-- DECLARACIÓN <%! %>: define un método que se puede usar en toda la página --%>
<%!
    private String etiquetaEstado(Factura f) {
        return f.isPagada() ? "Pagada" : "Pendiente de pago";
    }
%>

<%-- SCRIPTLET <% %>: código Java que se ejecuta al armar la página --%>
<%
    // Datos que envió el servlet con request.setAttribute(...)
    Factura factura = (Factura) request.getAttribute("factura");
    String error = (String) request.getAttribute("error");
    String numeroIngresado = (String) request.getAttribute("numeroIngresado");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Consultar factura</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <%@ include file="/WEB-INF/jspf/encabezado.jspf" %>

    <main class="contenedor">
        <h2>Consultar factura</h2>

        <%-- FORMULARIO HTML con método GET: envía "numero" al servlet /consulta --%>
        <form action="${pageContext.request.contextPath}/consulta" method="get" class="formulario">
            <label for="numero">Número de factura</label>
            <input type="text" id="numero" name="numero" maxlength="10" required
                   placeholder="Ejemplo: 1001"
                   value="<%= Formato.escapar(numeroIngresado) %>">
            <button type="submit" class="boton">Consultar</button>
        </form>

        <% if (error != null) { %>
            <p class="mensaje error"><%= Formato.escapar(error) %></p>
        <% } %>

        <% if (factura != null) { %>
            <h3>Datos de la factura</h3>
            <table class="tabla">
                <tr><th>Número</th><td>${factura.numero}</td></tr>
                <tr><th>Cliente</th><td><%= Formato.escapar(factura.getCliente()) %></td></tr>
                <tr><th>Periodo</th><td>${factura.periodo}</td></tr>
                <tr><th>Valor a pagar</th><td><%= Formato.moneda(factura.getValor()) %></td></tr>
                <tr><th>Fecha de vencimiento</th><td><%= Formato.fecha(factura.getFechaVencimiento()) %></td></tr>
                <tr><th>Estado</th><td><%= etiquetaEstado(factura) %></td></tr>
            </table>

            <% if (factura.isPagada()) { %>
                <p><a class="boton" href="${pageContext.request.contextPath}/recibo?numero=${factura.numero}">Ver recibo</a></p>
            <% } else { %>
                <p><a class="boton" href="${pageContext.request.contextPath}/pago?numero=${factura.numero}">Pagar esta factura</a></p>
            <% } %>
        <% } %>
    </main>

    <jsp:include page="/WEB-INF/jspf/pie.jspf" />
</body>
</html>
