package grupo7.poo.entity;

import grupo7.poo.controller.GestionProductos;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArchivoControl {
    private ProductosMap mapaProductos;
    private ClientesMap mapaClientes;
    private ArrayList<Pedido> pedidos;

    public ProductosMap getMapaProductos() {
        return mapaProductos;
    }

    public void setMapaProductos(ProductosMap mapaProductos) {
        this.mapaProductos = mapaProductos;
    }

    public ClientesMap getMapaClientes() {
        return mapaClientes;
    }

    public void setMapaClientes(ClientesMap mapaClientes) {
        this.mapaClientes = mapaClientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
