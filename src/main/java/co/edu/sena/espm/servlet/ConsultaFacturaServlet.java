package co.edu.sena.espm.servlet;

import co.edu.sena.espm.modelo.Factura;
import co.edu.sena.espm.servicio.FacturaServicio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet de CONSULTA de facturas (historia de usuario HU-01).
 *
 * Usa el método GET: el formulario de consulta.jsp envía el número de factura
 * en la dirección, por ejemplo:  /pago-facturas/consulta?numero=1001
 * Por eso aquí se implementa doGet().
 */
@WebServlet("/consulta")
public class ConsultaFacturaServlet extends HttpServlet {

    private final FacturaServicio servicio = FacturaServicio.getInstancia();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 1. Leer el parámetro que llegó por GET (puede ser null si es la primera visita)
        String numeroTexto = request.getParameter("numero");

        if (numeroTexto != null) {
            // 2. Validar y buscar
            try {
                int numero = Integer.parseInt(numeroTexto.trim());
                Factura factura = servicio.buscar(numero);
                if (factura == null) {
                    request.setAttribute("error", "No se encontró la factura " + numero + ".");
                } else {
                    request.setAttribute("factura", factura);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El número de factura debe contener solo dígitos.");
            }
            // Se devuelve lo que el usuario escribió para repetirlo en el campo
            request.setAttribute("numeroIngresado", numeroTexto);
        }

        // 3. Enviar el resultado al JSP que dibuja la página
        request.getRequestDispatcher("/WEB-INF/vistas/consulta.jsp").forward(request, response);
    }
}
