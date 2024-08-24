package domain.producto.tipos.limpieza;

import domain.producto.Producto;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Limpieza extends Producto {
    private static final Pattern IDENTIFICADOR_LIMPIEZA_PATTERN = Pattern.compile("AZ\\d{3}");
    private TipoAplicacion tipo;

    public Limpieza(String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, TipoAplicacion tipo) {
        super(id, descripcion, stock, precioUnidad, porcentajeGanancia);
        this.tipo = tipo;
    }

    @Override
    protected boolean validarId(String id) {
        return IDENTIFICADOR_LIMPIEZA_PATTERN.matcher(id).matches();
    }
}
