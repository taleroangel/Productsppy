package grupo7.poo.controller;

//Imports

import grupo7.poo.entity.Cliente;


import java.util.*;

public class GestionCliente {
    // Scanner para le lectura de datos
    private final Scanner scaner;

    // Asociaciones
    private final Map<Long, Cliente> listaClientes; // Map de los clientes

    //Inicializador de instancia
    {
        scaner = new Scanner(System.in);
        listaClientes = new HashMap<>();
    }

    // Constructor
    public GestionCliente() {
        scaner.useDelimiter("\n");
    }

    // Metodos

    /**
     * Imprimir la lista de todos los clientes en el Map
     *
     * @return String para imprimir todos los clientes
     */
    public String verListadoClientes() {
        StringBuilder output = new StringBuilder();
        output.append("*---------------------------------------------------------------*\n");
        if (this.listaClientes.isEmpty()) {
            output.append("No hay clientes registrados!!");
            return output.toString();
        }

        for (Cliente clt : this.listaClientes.values()) {//recorre objetos
            output.append(clt.toString()).append('\n');
            output.append("*---------------------------------------------------------------*\n");
        }
        return output.toString();
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

    //nombre  o direccion
    public boolean modificarCliente(long cedula, int opcion, String dato) {   //nombre o direccion

        if (opcion == 1) {
            if (buscarCliente(cedula) == null) {
                return false;
            }
            System.out.println("-----------info cliente--------------------------");
            System.out.println(buscarCliente(cedula).toString()); // Imprimir al Cliente
            System.out.println("-----------------------------------------------");
            buscarCliente(cedula).setNombreCompleto(dato);  //actualiza el dato del cliente
            System.out.println("-----------info cliente--------------------------");//muestra la info del cliente ya modificado
            System.out.println(buscarCliente(cedula).toString()); // Imprimir al Cliente
            System.out.println("-----------------------------------------------");

        }
        if (opcion == 3) {
            if (buscarCliente(cedula) == null) {
                return false;
            }
            System.out.println("-----------info cliente--------------------------");
            System.out.println(buscarCliente(cedula).toString()); // Imprimir al Cliente
            System.out.println("-----------------------------------------------");
            buscarCliente(cedula).setDireccion(dato);//actualiza el dato del cliente
            System.out.println("-----------info cliente--------------------------");//muestra la info del cliente ya modificado
            System.out.println(buscarCliente(cedula).toString()); // Imprimir al Cliente
            System.out.println("-----------------------------------------------");

        }

        return true;
    }

    //telefono
    public boolean ModificarCliente(long ced, long dato) {   //telefono

        if (buscarCliente(ced) == null) {
            return false;
        }
        System.out.println("-----------info cliente--------------------------");
        System.out.println(buscarCliente(ced).toString()); // Imprimir al Cliente
        System.out.println("-----------------------------------------------");
        buscarCliente(ced).setTelefonoContacto(dato); //actualiza el dato del cliente
        System.out.println("-----------info cliente--------------------------");//muestra la info del cliente ya modificado
        System.out.println(buscarCliente(ced).toString()); // Imprimir al Cliente
        System.out.println("-----------------------------------------------");
        return true;

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
