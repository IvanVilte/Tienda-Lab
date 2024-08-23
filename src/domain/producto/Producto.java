package domain.producto;

import domain.excepciones.IdentificadorInvalidoException;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public abstract class Producto {
    private static final Pattern IDENTIFICADOR_ENVASADO_PATTERN = Pattern.compile("AB\\d{3}");
    private static final Pattern IDENTIFICADOR_BEBIDA_PATTERN = Pattern.compile("AC\\d{3}");
    private static final Pattern IDENTIFICADOR_LIMPIEZA_PATTERN = Pattern.compile("AZ\\d{3}");

    protected String id;
    protected String descripcion;
    protected int cantidadStock;
    protected BigDecimal precioUnidad;
    protected BigDecimal porcentajeGanancia; // Ganancia en porcentaje.
    protected boolean disponibleParaVenta;

    public Producto (String id, String descripcion, int cantidadStock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia){
        if (!validarId(id)){
            throw new IdentificadorInvalidoException("Identificador inválido: " + id);
        }
        this.id                  = id;
        this.descripcion         = descripcion;
        this.cantidadStock       = cantidadStock;
        this.precioUnidad        = precioUnidad;
        this.porcentajeGanancia  = porcentajeGanancia;
        this.disponibleParaVenta = true;
    }

    private boolean validarId(String id){
        if(id.startsWith("AB")){
            return IDENTIFICADOR_ENVASADO_PATTERN.matcher(id).matches();
        } else if(id.startsWith("AC")){
            return IDENTIFICADOR_BEBIDA_PATTERN.matcher(id).matches();
        } else if(id.startsWith("AZ")){
            return IDENTIFICADOR_LIMPIEZA_PATTERN.matcher(id).matches();
        } else {
            return false;
        }
    }

    public void cambiarDisponibilidadParaVenta(boolean disponibleParaVenta){
        this.disponibleParaVenta = disponibleParaVenta;
    }

    public int obtenerCantidadEnStock(){
        return this.cantidadStock;
    }

    public BigDecimal obtenerImporteTotal(){
        return this.precioUnidad.multiply(BigDecimal.valueOf(this.cantidadStock));
    }

    public BigDecimal obtenerImporteTotal(int cantidad){
        return this.precioUnidad.multiply(BigDecimal.valueOf(cantidad));
    }

    protected BigDecimal calcularPrecioFinal(BigDecimal porcentajeDescuento){
        if (porcentajeDescuento == null || porcentajeDescuento.compareTo(BigDecimal.ZERO) < 0 || porcentajeDescuento.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Porcentaje de descuento no válido");
        }

        // Calcular el descuento aplicado al precio
        BigDecimal descuento = precioUnidad.multiply(porcentajeDescuento).divide(BigDecimal.valueOf(100));
        return precioUnidad.subtract(descuento);
    }

    public String obtenerIdentificador(){
        return this.id;
    }

    public void aumentarStock(int cantidadAgregar){
        this.cantidadStock += cantidadAgregar;
    }
}
