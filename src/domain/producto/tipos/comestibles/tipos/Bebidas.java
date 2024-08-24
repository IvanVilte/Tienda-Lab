package domain.producto.tipos.comestibles.tipos;

import domain.producto.tipos.comestibles.Comestible;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

public class Bebidas extends Comestible {
    private static final Pattern IDENTIFICADOR_BEBIDA_PATTERN = Pattern.compile("AC\\d{3}");
    private double graduacionAlcoholica; // Graduación alcohólica en porcentaje.

    public Bebidas(String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, Date fechaVencimiento, short calorias, double graduacionAlcoholica, boolean importado) {
        super(id, descripcion, stock, precioUnidad, porcentajeGanancia, fechaVencimiento, calorias, importado);
        this.graduacionAlcoholica = graduacionAlcoholica;
    }

    @Override
    protected boolean validarId(String id) {
        return IDENTIFICADOR_BEBIDA_PATTERN.matcher(id).matches();
    }
}
