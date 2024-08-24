package domain.producto.tipos.comestibles.tipos;

import domain.producto.tipos.comestibles.Comestible;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Pattern;

public class Envasado extends Comestible {
    /*
        * Se elije la clase String (en lugar de una enumeración) para representar el tipo de envase, por no estar predefinidos los tipos de envase
        * (como si lo estan los tipos de aplicación de los artículos de limpieza) que pudieran haber en la tienda. Ya que si suponemos algunos,
        * puede que otros queden afura, y a consideracion mia eso no es correcto.
     */
    private static final Pattern IDENTIFICADOR_ENVASADO_PATTERN = Pattern.compile("AB\\d{3}");
    private String tipoEnvase;

    public Envasado(String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, boolean importado, Date fechaVencimiento, short calorias, String tipoEnvase) {
        super(id, descripcion, stock, precioUnidad, porcentajeGanancia, fechaVencimiento, calorias, importado);
        this.tipoEnvase = tipoEnvase;
    }

    @Override
    protected boolean validarId(String id) {
        return IDENTIFICADOR_ENVASADO_PATTERN.matcher(id).matches();
    }
}
