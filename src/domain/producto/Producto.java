package domain.producto;

import java.math.BigDecimal;

public abstract class Producto {
    protected String id;
    protected String descripcion;
    protected int stock;
    protected BigDecimal precioUnidad;
    protected BigDecimal porcentajeGanancia; // Ganancia en porcentaje.
    protected boolean disponibleParaVenta;

    public Producto (String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, boolean disponibleParaVenta){
        if (!validarIdentificador(id)){

        }
        this.id                  = id;
        this.descripcion         = descripcion;
        this.stock               = stock;
        this.precioUnidad        = precioUnidad;
        this.porcentajeGanancia  = porcentajeGanancia;
        this.disponibleParaVenta = disponibleParaVenta;
    }

    protected abstract boolean validarIdentificador(String id);

    protected BigDecimal calcularPrecioFinal(BigDecimal porcentajeDescuento){
        if (porcentajeDescuento == null || porcentajeDescuento.compareTo(BigDecimal.ZERO) < 0 || porcentajeDescuento.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Porcentaje de descuento no válido");
        }

        // Calcular el descuento aplicado al precio
        BigDecimal descuento = precioUnidad.multiply(porcentajeDescuento).divide(BigDecimal.valueOf(100));
        return precioUnidad.subtract(descuento);
    }
}
