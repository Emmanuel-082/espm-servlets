# ESPM - Módulo de pago de facturas (Servlets y JSP)

Evidencia GA7-220501096-AA2-EV02: Módulos de software codificados y probados.

Aplicación web en Java que permite consultar una factura y pagarla en línea. Está hecha con servlets (parte lógica) y JSP (parte visual).

## Qué cumple de la lista de chequeo

1. Formularios HTML con servlets: `consulta.jsp` y `pago.jsp` envían sus datos a los servlets `/consulta` y `/pago`.
2. Métodos GET y POST: la consulta usa GET (`doGet` en `ConsultaFacturaServlet`) y el pago usa POST (`doPost` en `PagoFacturaServlet`).
3. Elementos de JSP: directivas `page` e `include`, acción `jsp:include`, declaración, scriptlets, expresiones, EL y comentarios JSP.
4. Versionamiento: el proyecto está controlado con Git (ver el historial con `git log`).

## Estructura

- `src/main/java/.../modelo`: clase `Factura`.
- `src/main/java/.../servicio`: clase `FacturaServicio` (lógica de negocio y datos de ejemplo).
- `src/main/java/.../servlet`: los tres servlets.
- `src/main/java/.../util`: clase `Formato` (moneda, fechas y escape de HTML).
- `src/main/webapp`: página de inicio, estilos y, dentro de `WEB-INF`, las vistas JSP y los fragmentos reutilizables.
- `docs/artefactos.md`: historias de usuario, casos de uso, diagrama de clases y prototipo.

## Requisitos

- Java JDK 11 o superior.
- Apache Tomcat 9 (usa `javax.servlet`; con Tomcat 10 o superior no funciona sin cambiar los imports a `jakarta`).
- Un IDE que abra proyectos Maven, por ejemplo NetBeans, Eclipse o IntelliJ.

## Cómo ejecutarlo (NetBeans)

1. Abra NetBeans y use Archivo > Abrir proyecto; elija la carpeta del proyecto.
2. Clic derecho sobre el proyecto > Propiedades > Ejecutar y escoja el servidor Tomcat 9.
3. Clic derecho sobre el proyecto > Ejecutar.
4. Abra en el navegador: `http://localhost:8080/pago-facturas/`

## Datos de prueba

Facturas 1001, 1002, 1003 y 1004 (todas pendientes). Los datos viven en memoria: al reiniciar el servidor las facturas vuelven a estar pendientes.
