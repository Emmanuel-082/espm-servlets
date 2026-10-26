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
 * Servlet que muestra el RECIBO de una factura ya pagada (historia de usuario HU-03).
 * Recibe el número por GET:  /pago-facturas/recibo?numero=1001
 */
@WebServlet("/recibo")
public class ReciboServlet extends HttpServlet {

    private final FacturaServicio servicio = FacturaServicio.getInstancia();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int numero = Integer.parseInt(request.getParameter("numero"));
            Factura factura = servicio.buscar(numero);

            if (factura == null || !factura.isPagada()) {
                request.setAttribute("error", "No hay un recibo para la factura " + numero
                        + " porque no existe o aún no está pagada.");
            } else {
                request.setAttribute("factura", factura);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "El número de factura no es válido.");
        }

        request.getRequestDispatcher("/WEB-INF/vistas/recibo.jsp").forward(request, response);
    }
}
