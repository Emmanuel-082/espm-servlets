package co.edu.sena.espm.servicio;

import co.edu.sena.espm.modelo.Factura;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Capa de servicio: aquí vive la lógica del negocio (buscar y pagar facturas).
 * Los servlets NO guardan datos; le piden todo a esta clase.
 *
 * Para que el ejemplo sea sencillo, las facturas se guardan en memoria
 * (se reinician cada vez que se reinicia el servidor).
 */
public class FacturaServicio {

    /** Métodos de pago aceptados. */
    public static final List<String> METODOS_PAGO = Arrays.asList("PSE", "TARJETA", "EFECTIVO");

    // Única instancia compartida por todos los servlets (patrón Singleton)
    private static final FacturaServicio INSTANCIA = new FacturaServicio();

    private final Map<Integer, Factura> facturas = new LinkedHashMap<>();

    private FacturaServicio() {
        // Datos de ejemplo para poder probar la aplicación
        agregar(new Factura(1001, "Maria Gomez", "Septiembre 2026", 85600, LocalDate.of(2026, 10, 30)));
        agregar(new Factura(1002, "Carlos Perez", "Septiembre 2026", 132450, LocalDate.of(2026, 10, 30)));
        agregar(new Factura(1003, "Ana Rodriguez", "Agosto 2026", 47900, LocalDate.of(2026, 9, 30)));
        agregar(new Factura(1004, "Luis Martinez", "Septiembre 2026", 210300, LocalDate.of(2026, 10, 30)));
    }

    public static FacturaServicio getInstancia() {
        return INSTANCIA;
    }

    private void agregar(Factura f) {
        facturas.put(f.getNumero(), f);
    }

    /** Devuelve la factura o null si no existe. */
    public synchronized Factura buscar(int numero) {
        return facturas.get(numero);
    }

    /**
     * Paga una factura. Lanza IllegalArgumentException con un mensaje claro
     * si algún dato no es válido; el servlet muestra ese mensaje al usuario.
     */
    public synchronized Factura pagar(int numero, String titular, String metodo) {
        Factura f = facturas.get(numero);
        if (f == null) {
            throw new IllegalArgumentException("La factura " + numero + " no existe.");
        }
        if (f.isPagada()) {
            throw new IllegalArgumentException("La factura " + numero + " ya fue pagada.");
        }
        if (titular == null || titular.trim().length() < 3) {
            throw new IllegalArgumentException("El nombre del titular debe tener al menos 3 caracteres.");
        }
        if (titular.trim().length() > 60) {
            throw new IllegalArgumentException("El nombre del titular no puede superar 60 caracteres.");
        }
        if (metodo == null || !METODOS_PAGO.contains(metodo)) {
            throw new IllegalArgumentException("Seleccione un método de pago válido.");
        }
        f.registrarPago(titular.trim(), metodo);
        return f;
    }
}
