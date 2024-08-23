package domain.producto.tipos.limpieza;

import domain.producto.Producto;

import java.math.BigDecimal;

public class Limpieza extends Producto {
    private TipoAplicacion tipo;

    public Limpieza(String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, TipoAplicacion tipo){
        super(id, descripcion, stock, precioUnidad, porcentajeGanancia);
        this.tipo = tipo;
    }
}
