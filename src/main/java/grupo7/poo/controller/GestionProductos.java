package grupo7.poo.controller;

//Imports

import grupo7.poo.entity.Producto;

import java.util.*;

public class GestionProductos {
    //Asociaciones
    private final Map<UUID, Producto> listaProductos;  //MAP donde la llave es prodId que es el id del producto

    //Inicializador de instancia
    {
        listaProductos = new HashMap<>();
    }

    /**
     * Obtener el map de lista de productos
     *
     * @return Map<UUID, Producto>, Map donde la llave es el prodId
     */
    public Map<UUID, Producto> getListaProductos() {
        return listaProductos;
    }           //metodos de acceso

    /**
     * Insertar el producto
     *
     * @param producto producto a insertar
     */
    public boolean insertarProducto(Producto producto) {
        if (buscarProducto(producto.getProdId()) == null) {             //busca el id del producto,si no esta lo agrega
            this.listaProductos.put(producto.getProdId(), producto);    //inserto el producto
            return true;
        } else return false;
    }

    //Métodos para modificar un producto
    public boolean modificarNombreProducto(String nuevo_nombre, UUID id_producto) {
        Producto objetoAmodificar = buscarProducto(id_producto);
        if (objetoAmodificar == null) return false;
        objetoAmodificar.setNombreComercial(nuevo_nombre);
        return true;
    }

    public boolean modificarNombreTienda(String nombre_comercio, UUID id_producto) {
        Producto objetoAmodificar = buscarProducto(id_producto);
        if (objetoAmodificar == null) return false;
        objetoAmodificar.setTienda(nombre_comercio);
        return true;
    }

    public boolean modificarPrecioProducto(double precio, UUID id_producto) {
        Producto objetoAmodificar = buscarProducto(id_producto);
        if (objetoAmodificar == null) return false;
        objetoAmodificar.setPrecio(precio);
        return true;
    }

    /**
     * Busca el id del producto
     *
     * @param id UUID del prodcuto
     * @return Producto encontrado o null si no es contrado
     */
    public Producto buscarProducto(UUID id) {
        return this.listaProductos.getOrDefault(id, null); //retorna el objeto producto
    }

    /**
     * Buscar un producto y eliminarlo
     *
     * @param id UUID del producto
     * @return el producto eliminado, o null si no se encontró
     */
    public Producto eliminarProducto(UUID id) {
        Producto producto = this.buscarProducto(id);
        if (producto == null) return null;
        return listaProductos.remove(producto.getProdId());
    }

    /**
     * Imprimir la lista de los productos
     */
    public String imprimirListaProductos() {
        StringBuilder output = new StringBuilder();
        output.append("*---------------------------------------------------------------*\n");
        if (this.listaProductos.isEmpty()) {
            output.append("No hay productos registrados!!");
            return output.toString();
        }

        for (Producto recorrer : this.listaProductos.values()) {
            output.append(recorrer.toString()).append('\n');
            output.append("*---------------------------------------------------------------*\n");
        }
        return output.toString();
    }
}
