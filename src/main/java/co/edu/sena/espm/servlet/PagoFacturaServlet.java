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
 * Servlet de PAGO de facturas (historia de usuario HU-02).
 *
 *  - doGet():  muestra el formulario de pago con los datos de la factura.
 *  - doPost(): recibe el formulario enviado, registra el pago y lleva al recibo.
 *
 * El pago usa POST porque modifica datos y porque los datos del formulario
 * no deben viajar visibles en la dirección de la página.
 */
@WebServlet("/pago")
public class PagoFacturaServlet extends HttpServlet {

    private final FacturaServicio servicio = FacturaServicio.getInstancia();

    /** GET /pago?numero=1001  ->  formulario de pago. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        cargarFactura(request, request.getParameter("numero"));
        mostrarFormulario(request, response);
    }

    /** POST /pago  ->  procesa el formulario. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Importante: se debe indicar UTF-8 ANTES de leer los parámetros,
        // si no, las tildes y la ñ llegan dañadas.
        request.setCharacterEncoding("UTF-8");

        String numeroTexto = request.getParameter("numero");
        String titular = request.getParameter("titular");
        String metodo = request.getParameter("metodo");

        try {
            int numero = Integer.parseInt(numeroTexto == null ? "" : numeroTexto.trim());
            servicio.pagar(numero, titular, metodo);

            // Patrón "Post-Redirect-Get": después de un POST exitoso se redirige
            // a una página GET. Así, si el usuario recarga la página del recibo,
            // no se vuelve a enviar el pago.
            response.sendRedirect(request.getContextPath() + "/recibo?numero=" + numero);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "El número de factura no es válido.");
            mostrarFormulario(request, response);
        } catch (IllegalArgumentException e) {
            // Error de validación: se vuelve a mostrar el formulario con el mensaje
            request.setAttribute("error", e.getMessage());
            cargarFactura(request, numeroTexto);
            request.setAttribute("titularIngresado", titular);
            mostrarFormulario(request, response);
        }
    }

    private void cargarFactura(HttpServletRequest request, String numeroTexto) {
        try {
            int numero = Integer.parseInt(numeroTexto == null ? "" : numeroTexto.trim());
            Factura factura = servicio.buscar(numero);
            if (factura != null) {
                request.setAttribute("factura", factura);
            }
        } catch (NumberFormatException e) {
            // Si el número no es válido simplemente no se carga la factura
        }
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/vistas/pago.jsp").forward(request, response);
    }
}
