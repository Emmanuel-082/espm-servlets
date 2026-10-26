<%-- Directiva PAGE: configura la página (idioma de programación, codificación, clases importadas) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inicio - Pago de facturas</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>
    <%-- Directiva INCLUDE --%>
    <%@ include file="/WEB-INF/jspf/encabezado.jspf" %>

    <main class="contenedor">
        <h2>Bienvenido</h2>
        <p>Desde este módulo puede consultar sus facturas de servicios públicos y pagarlas en línea.</p>

        <%-- EXPRESIÓN <%= %>: calcula un valor y lo imprime en la página --%>
        <p class="nota">Fecha de hoy: <%= LocalDate.now() %></p>

        <h3>¿Qué desea hacer?</h3>
        <p><a class="boton" href="<%= request.getContextPath() %>/consulta">Consultar una factura</a></p>

        <h3>Facturas de ejemplo para probar</h3>
        <p>Los números 1001, 1002, 1003 y 1004 existen y están pendientes de pago.</p>
    </main>

    <%-- ACCIÓN jsp:include --%>
    <jsp:include page="/WEB-INF/jspf/pie.jspf" />
</body>
</html>
