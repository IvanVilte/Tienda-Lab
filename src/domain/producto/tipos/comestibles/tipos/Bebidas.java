package domain.producto.tipos.comestibles.tipos;

import domain.producto.tipos.comestibles.Comestible;

import java.math.BigDecimal;
import java.util.Date;

public class Bebidas extends Comestible {
    private double graduacionAlcoholica; // Graduación alcohólica en porcentaje.

    public Bebidas(String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, boolean disponibleParaVenta, Date fechaVencimiento, short calorias, double graduacionAlcoholica, boolean importado) {
        super(id, descripcion, stock, precioUnidad, porcentajeGanancia, fechaVencimiento, calorias, importado);
        this.graduacionAlcoholica = graduacionAlcoholica;
    }
}
