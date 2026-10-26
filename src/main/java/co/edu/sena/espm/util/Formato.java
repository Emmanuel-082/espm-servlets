package co.edu.sena.espm.util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Funciones de ayuda que usan los JSP para mostrar datos.
 */
public final class Formato {

    private static final Locale CO = new Locale("es", "CO");
    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private Formato() {
    }

    /** Convierte 85600 en "$ 85.600". */
    public static String moneda(long valor) {
        return "$ " + NumberFormat.getIntegerInstance(CO).format(valor);
    }

    public static String fecha(LocalDate fecha) {
        return fecha == null ? "" : fecha.format(FECHA);
    }

    public static String fechaHora(LocalDateTime fecha) {
        return fecha == null ? "" : fecha.format(FECHA_HORA);
    }

    /**
     * Escapa caracteres especiales de HTML (&lt; &gt; &amp; comillas).
     * Se usa siempre que se muestra texto escrito por el usuario,
     * para evitar que alguien inyecte código en la página (ataque XSS).
     */
    public static String escapar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
}
