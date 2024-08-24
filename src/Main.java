import domain.excepciones.IdentificadorInvalidoException;
import domain.producto.Producto;
import domain.producto.tipos.comestibles.tipos.Bebidas;
import domain.producto.tipos.comestibles.tipos.Envasado;
import domain.producto.tipos.limpieza.Limpieza;
import domain.producto.tipos.limpieza.TipoAplicacion;
import domain.tienda.Tienda;

import java.beans.Encoder;
import java.math.BigDecimal;
import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Tienda tiendaTest = new Tienda("Tienda de Iván", 100, new BigDecimal("100000.00"));
        boolean importado = false;
        short calorias = 256;
        TipoAplicacion tipoCocina = TipoAplicacion.COCINA;

        Producto productoLeche = new Envasado("AB123", "Leche Descremada Tregar por 1L", 10, new BigDecimal("1600.00"), new BigDecimal("5.00"), false, new Date("05/04/2025"), calorias, "Tetrabick");
        tiendaTest.agregarProducto(productoLeche);

        Producto productoBorita = new Limpieza("AZ123", "Detergente Borita x 900ml", 30, new BigDecimal("1800.00"), new BigDecimal("3.00"), tipoCocina);
        tiendaTest.agregarProducto(productoBorita);

        Producto productoPepsi = new Bebidas("AC123", "Gaseosa Pepsi Cola x 3L", 61, new BigDecimal("2300.00"), new BigDecimal("4.5"), new Date("25/01/2025"), calorias, 0.0, false);
        tiendaTest.agregarProducto(productoPepsi);


        System.out.println(tiendaTest.cantidadTotalProductos());
        System.out.println(tiendaTest.obtenerSaldoEnCaja());

    }
}