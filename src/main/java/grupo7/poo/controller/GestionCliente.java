package grupo7.poo.controller;

//Imports

import grupo7.poo.entity.ArchivoDatos;
import grupo7.poo.entity.Cliente;

import java.util.Map;

public class GestionCliente {

    // Asociaciones
    private Map<Long, Cliente> listaClientes; // Map de los clientes

    // Constructor


    public GestionCliente(ArchivoDatos archivo) {
        listaClientes = archivo.getListaClientes();
    }
    public GestionCliente(){

    }
    //Getters y Setters de la lista de clientes
    public Map<Long, Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Map<Long, Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * Insertar un cliente en la lista
     *
     * @param clnte Cliente a insertar
     */
    public boolean insertarCliente(Cliente clnte) {
        if (buscarCliente(clnte.getCedula()) == null) { // busca el id del producto, si no esta lo agrega
            this.listaClientes.put(clnte.getCedula(), clnte);//inserto el cliente en el mapa
            return true;
        } else {
            return false;
        }
    }

    /**
     * Buscar si un cliente existe
     *
     * @param cedula cedula del cliente
     * @return Cliente si se encontró, null si no
     */
    public Cliente buscarCliente(Long cedula) {
        return this.listaClientes.getOrDefault(cedula, null); //retorna el objeto cliente
    }

    /**
     * Eliminar a un cliente de la lista siguiendo los parámetros
     *
     * @param cedula cedula del cliente a eliminar
     * @return Cliente eliminado, null si no es posible ser eliminado
     */
    public Cliente eliminarCliente(Long cedula) {
        Cliente clnte = this.buscarCliente(cedula);
        if (clnte == null) {
            return null;// si no encontro el codigo lo
        }
        return listaClientes.remove(clnte.getCedula());// busca la cedula y remueve;
    }
}
