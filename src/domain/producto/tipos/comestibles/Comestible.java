package domain.producto.tipos.comestibles;

import domain.producto.Producto;

import java.math.BigDecimal;
import java.util.Date;

/*
    * En este caso tomamos la consideración de que tanto los productos envasados como las bebidas son comestibles.
    * Para realizar tal afirmación me base en los siguiente:
    * Las bebidas, en su mayoría, en una tienda suelen ser comestibles y en las mismas se encuentran presenten, también en
    * su mayoría los atributos descriptos en la consigna.
    * Los envasados, al tratarse de una tienda y al estar especificado en el práctico los productos de limpieza, los
    * considere que son todos envasados de alimento. También al investigar sobre los envasados llegué a dicha conclusión
    * fuente: https://www.euroinnova.com/blog/que-son-productos-envasados#tecnicas-de-envasado
 */
public abstract class Comestible extends Producto {
    protected Date fechaVencimiento;
    protected short calorias;
    protected  boolean importado;

    public Comestible(String id, String descripcion, int stock, BigDecimal precioUnidad, BigDecimal porcentajeGanancia, Date fechaVencimiento, short calorias, boolean importado){
        super(id, descripcion, stock, precioUnidad, porcentajeGanancia);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        this.importado = importado;
    }
}
