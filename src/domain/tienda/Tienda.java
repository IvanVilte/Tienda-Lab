package domain.tienda;

import com.sun.source.tree.WhileLoopTree;
import domain.producto.Producto;

import java.math.BigDecimal;
import java.util.*;

/*
    * Una de las cosas que mas me costo decidir (al no tener o tal vez no entender el contexto del tp o sus requerimientos)
    * de esta parte del TP N°1 fue el tipo de collection donde almacenariamos los productos de la tienda, estuve entre
    * LIST y MAP prácticamente toda la semana y al final me incline por implementar esto con Map.
    * Mi decisión de usar Map se baso en las siguientes ventajas que al investigar note tenian esta sobre LIST:
    *
    * 1- Se podia usar el identificador como clave en el map, esto haría que el acceso a los productos fuera
    * mas rápido que con un LIST, donde deberia recorrerlo cada vez que buscara un producto.
    * 2- Basandome en la experiencia empirica de ir al súper, creo que al realizar una compra (solo se compran productos
    * que estan en la tienda) el uso de un map seria adecuado ya que la busqueda, de nuevo, se haria más rapido y solo
    * necesitariamos conocer el identificador del producto (el código de barra en el caso de un súper real).
    * 3- El echo de poder usar Stream, y de lo anteriormente mencionado, con HasMap fue lo que termino convenciendome de
    * usarlo en mi implementación del TP1.
    *
    * NOTA: La verdad me costo mucho dicidir y aprovecho (por si lo leen) para pedirles si pueden explicar en el classroom
    * cual hubiera sido la collection correcta para el desarrollo de esta narrativa. Desde ya muchas gracias.
 */
public class Tienda {
    private static final int CANTIDAD_MAXIMA_PRODUCTOs_X_VENTA = 3;
    private static final int CANTIDAD_MAXIMA_UNIDADES_X_PRODUCTO = 12;
    private String nombre;
    private int maximoProductoStock;
    private BigDecimal saldoCaja;
    private Map<String, Producto> productos = new HashMap<>();

    public Tienda(String nombre, int maximoProductoStock, BigDecimal saldoCaja){
        this.nombre = nombre;
        this.maximoProductoStock = maximoProductoStock;
        this.saldoCaja = saldoCaja;
        this.productos = new HashMap<>();
    }

    //Compra de productos
    public void agregarProducto(Producto nuevoProducto){
        BigDecimal importeTotalCompra = nuevoProducto.obtenerImporteTotal();
        if(sePuedeComprar(importeTotalCompra)){
            int nuevaCantidadTotal = this.cantidadTotalProductos() + nuevoProducto.obtenerCantidadEnStock();

            if(nuevaCantidadTotal <= this.maximoProductoStock){
                // Si el nuevoProducto ya existe, actulizamos el stock del mismo
                Producto productoExistente = productos.get(nuevoProducto.obtenerIdentificador());

                if (productoExistente != null) {
                    productoExistente.aumentarStock(productoExistente.obtenerCantidadEnStock() + nuevoProducto.obtenerCantidadEnStock());
                    this.actualizarSaldoEnCaja(this.obtenerSaldoEnCaja().subtract(importeTotalCompra));
                } else {
                    // Si el producto no existe, lo agregamos al mapa
                    productos.put(nuevoProducto.obtenerIdentificador(), nuevoProducto);
                    this.actualizarSaldoEnCaja(this.obtenerSaldoEnCaja().subtract(importeTotalCompra));
                }
            }else {
                System.out.println("No se puede agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
            }
        }else {
            System.out.println("El nuevoProducto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
        }
    }

    public void agregarProducto(Producto nuevoProducto, int cantidad){
        BigDecimal importeTotalCompra = nuevoProducto.obtenerImporteTotal(cantidad);
        if(sePuedeComprar(importeTotalCompra)){
            int nuevaCantidadTotal = this.cantidadTotalProductos() + cantidad;

            if(nuevaCantidadTotal <= this.maximoProductoStock){
                // Si el nuevoProducto ya existe, actulizamos el stock del mismo.
                Producto productoExistente = productos.get(nuevoProducto.obtenerIdentificador());

                if (productoExistente != null) {
                    productoExistente.aumentarStock(productoExistente.obtenerCantidadEnStock() + cantidad);
                    this.actualizarSaldoEnCaja(this.obtenerSaldoEnCaja().subtract(importeTotalCompra));
                } else {
                    // Si el producto no existe, lo agregamos al mapa
                    productos.put(nuevoProducto.obtenerIdentificador(), nuevoProducto);
                    this.actualizarSaldoEnCaja(this.obtenerSaldoEnCaja().subtract(importeTotalCompra));
                }
            }else {
                System.out.println("No se puede agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock.");
            }
        }else {
            System.out.println("El nuevoProducto no podrá ser agregado a la tienda por saldo insuficiente en la caja.");
        }
    }

    private boolean sePuedeComprar(BigDecimal importeTotal){
        BigDecimal saldo = this.saldoCaja.subtract(importeTotal);

        return saldo.compareTo(BigDecimal.ZERO) >= 0;
    }

    private void actualizarSaldoEnCaja(BigDecimal importeTotal){
        this.saldoCaja = importeTotal;
    }

    public BigDecimal obtenerSaldoEnCaja(){
        return this.saldoCaja;
    }

    public int cantidadTotalProductos(){
        return productos.values().stream().mapToInt(Producto::obtenerCantidadEnStock).sum();
    }

    public String mostrarNombreTienda(){
        return this.nombre;
    }

    //Venta de productos
    public void ventaDeProductos(){
        Scanner scanner = new Scanner(System.in);
        int cantidadProductos = 0;
        int cantidad;
        List<Producto> productosAVender = new ArrayList<>(); //List donde se guardan los productos que se compran y aquellos que por uno u otro motivo no pudieron comprarse y hay que mostrarlo como no validos

        System.out.println("Bienvenido a la tienda " + this.mostrarNombreTienda() + " (0- Para Finaliza ingreso)");

        System.out.println("Ingrese el código de producto: ");
        String codigoProducto = scanner.nextLine();

        while((codigoProducto.compareTo("0") != 0) && (cantidadProductos <= CANTIDAD_MAXIMA_PRODUCTOs_X_VENTA)){
            if(this.esProductoDeLaTienda(codigoProducto)){
                Producto producto = this.productos.get(codigoProducto);

                if (producto.isDisponibleParaVenta()){
                    cantidadProductos++;
                    System.out.println("Ingrese la cantidad de unidades (0 - cancelar producto): ");
                    cantidad = scanner.nextInt();

                    while(cantidad > 12){
                        System.out.println("No puede comprar mas de 12 unidades.");
                        System.out.println("Ingrese la cantidad de unidades (0 - cancelar producto): ");
                        cantidad = scanner.nextInt();
                    }

                    if(cantidad != 0){

                    }
                    else{
                        cantidad--;
                        System.out.println("Producto cancelado.");
                        System.out.println("Ingrese el código de producto (0-Para salir): ");
                        codigoProducto = scanner.nextLine();
                    }

                } else {
                    System.out.println("El producto ingresado no se encuentra disponibe para la venta.");
                    System.out.println("Si no desea ingresar otro código de producto presione 0(cero).");
                    //productosAVender.add();
                }

            }else{
                System.out.println("El código ingresado no pertenece a un producto de la tienda. Ingrese un código válido.");
                System.out.println("Si no desea ingresar otro código de producto presione 0(cero).");
                codigoProducto = scanner.nextLine();
            }

        }

    }

    public boolean esProductoDeLaTienda(String codigoProducto){
        return this.productos.get(codigoProducto) != null;
    }

}
