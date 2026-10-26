package co.edu.sena.espm.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase del modelo: representa una factura de servicios públicos.
 * Corresponde a la clase "Factura" del diagrama de clases (ver docs/artefactos.md).
 */
public class Factura {

    private final int numero;
    private final String cliente;
    private final String periodo;
    private final long valor;                 // valor en pesos colombianos
    private final LocalDate fechaVencimiento;

    // Estos datos se llenan cuando la factura se paga
    private boolean pagada;
    private LocalDateTime fechaPago;
    private String titularPago;
    private String metodoPago;

    public Factura(int numero, String cliente, String periodo, long valor, LocalDate fechaVencimiento) {
        this.numero = numero;
        this.cliente = cliente;
        this.periodo = periodo;
        this.valor = valor;
        this.fechaVencimiento = fechaVencimiento;
        this.pagada = false;
    }

    /** Marca la factura como pagada y guarda los datos del pago. */
    public void registrarPago(String titular, String metodo) {
        this.pagada = true;
        this.fechaPago = LocalDateTime.now();
        this.titularPago = titular;
        this.metodoPago = metodo;
    }

    // ---- Getters: permiten leer los datos desde los servlets y los JSP ----

    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public String getPeriodo() { return periodo; }
    public long getValor() { return valor; }
    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public boolean isPagada() { return pagada; }
    public LocalDateTime getFechaPago() { return fechaPago; }
    public String getTitularPago() { return titularPago; }
    public String getMetodoPago() { return metodoPago; }
}
