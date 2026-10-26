<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="co.edu.sena.espm.modelo.Factura, co.edu.sena.espm.servicio.FacturaServicio, co.edu.sena.espm.util.Formato" %>
<%
    Factura factura = (Factura) request.getAttribute("factura");
    String error = (String) request.getAttribute("error");
    String titularIngresado = (String) request.getAttribute("titularIngresado");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pagar factura</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <%@ include file="/WEB-INF/jspf/encabezado.jspf" %>

    <main class="contenedor">
        <h2>Pagar factura</h2>

        <% if (error != null) { %>
            <p class="mensaje error"><%= Formato.escapar(error) %></p>
        <% } %>

        <% if (factura == null) { %>
            <p>No se encontró la factura. <a href="${pageContext.request.contextPath}/consulta">Volver a consultar</a></p>
        <% } else if (factura.isPagada()) { %>
            <p>La factura ${factura.numero} ya está pagada.
               <a href="${pageContext.request.contextPath}/recibo?numero=${factura.numero}">Ver recibo</a></p>
        <% } else { %>
            <table class="tabla">
                <tr><th>Factura</th><td>${factura.numero}</td></tr>
                <tr><th>Cliente</th><td><%= Formato.escapar(factura.getCliente()) %></td></tr>
                <tr><th>Valor a pagar</th><td><%= Formato.moneda(factura.getValor()) %></td></tr>
            </table>

            <%-- FORMULARIO HTML con método POST: envía los datos del pago al servlet /pago --%>
            <form action="${pageContext.request.contextPath}/pago" method="post" class="formulario">
                <%-- Campo oculto: lleva el número de factura sin que el usuario lo escriba --%>
                <input type="hidden" name="numero" value="${factura.numero}">

                <label for="titular">Nombre del titular del pago</label>
                <input type="text" id="titular" name="titular" minlength="3" maxlength="60" required
                       value="<%= Formato.escapar(titularIngresado) %>">

                <label for="metodo">Método de pago</label>
                <select id="metodo" name="metodo" required>
                    <option value="">-- Seleccione --</option>
                    <% for (String m : FacturaServicio.METODOS_PAGO) { %>
                        <option value="<%= m %>"><%= m %></option>
                    <% } %>
                </select>

                <button type="submit" class="boton">Confirmar pago</button>
            </form>
        <% } %>
    </main>

    <jsp:include page="/WEB-INF/jspf/pie.jspf" />
</body>
</html>
