package grupo7.poo.entity;

import grupo7.poo.servicioAdicional.EnvioPrime;
import grupo7.poo.servicioAdicional.ServicioAdicional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.stream.Collectors;

//Imports

public class Pedido {

    //Atributos
    private UUID numPedido; //Identificador propio del pedido
    private Calendar fechaRecibido; //Cuando recibio el pedido?
    private boolean pagado; //Si lo pago o no lo pago
    private String nombreRepartidor; //Persona que es la encargada de dar el producto
    private Double precio;

    //Asociaciones
    private Cliente solicitante; //Cliente que solicita el pedido
    private ArrayList<ServicioAdicional> serviciosAdicionales = new ArrayList<>(); //lista de servicios adicionales
    private Producto productoSolicitado; //Producto solicitado en el pedido

    //Contructores
    public Pedido() {
        this.numPedido = UUID.randomUUID();
    }

    public Pedido(
            Calendar fechaRecibido,
            boolean pagado,
            String nombreRepartidor,
            Producto producto,
            Cliente cliente
    ) {
        this.fechaRecibido = fechaRecibido;
        this.pagado = pagado;
        this.nombreRepartidor = nombreRepartidor;

        this.productoSolicitado = producto;
        this.solicitante = cliente;

        //Obtener un UUID alteatorio
        this.numPedido = UUID.randomUUID();
    }

    public Pedido(
            UUID new_uuid,
            Calendar fechaRecibido,
            boolean pagado,
            String nombreRepartidor,
            Producto producto,
            Cliente cliente
    ) {
        this.fechaRecibido = fechaRecibido;
        this.pagado = pagado;
        this.nombreRepartidor = nombreRepartidor;

        this.productoSolicitado = producto;
        this.solicitante = cliente;

        //Obtener un UUID alteatorio
        this.numPedido = new_uuid;
    }

    //Metodos

    //Getters y Setters (Atributos)


    public void setNumPedido(UUID numPedido) {
        this.numPedido = numPedido;
    }

    public Double getPrecio() {
        this.precioTotal();
        return precio;
    }

    public UUID getNumPedido() {
        return numPedido;
    }

    public Calendar getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Calendar fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    //Getters y Setters (Asociaciones)
    public Cliente getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Cliente solicitante) {
        this.solicitante = solicitante;
    }

    public ArrayList<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(
            ArrayList<ServicioAdicional> serviciosAdicionales
    ) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    public void setProductoSolicitado(Producto productoSolicitado) {
        this.productoSolicitado = productoSolicitado;
    }

    @Override
    public String toString() {
        StringBuilder lista_servicios = new StringBuilder(""); //imprime los servicios adicionales
        for (ServicioAdicional service : this.serviciosAdicionales) {
            lista_servicios.append("\n");
            lista_servicios.append(service.toString());
        }

        return "Pedido: \n" +
                       "\tNúmero de pedido: \t" + this.numPedido + '\n' +
                       "\tFecha recibido: \t" + (new SimpleDateFormat("dd/MM/yyyy")).format(this.fechaRecibido.getTime()) + '\n' +
                       "\tPagado: \t\t\t" + this.pagado + '\n' +
                       "\tNombre Repartidor: \t" + this.nombreRepartidor + '\n' +
                       "\tCédula solicitante: " + this.solicitante.getCedula() + '\n' +
                       "\tLista de Servicios adicionales: \n" + lista_servicios + '\n' +
                       "\tUUID del producto: \t" + productoSolicitado.getProdId();
    }

    /**
     * Calcular el precio total de un pedido
     */
    public void precioTotal() {
        //Calcular costo del pedido
        double precioServicios = 0;
        for (ServicioAdicional servi : this.serviciosAdicionales) {
            precioServicios += servi.getPrecio(); //guarda el precio de los servicios adicionales
        }

        //costo del producto +el iva+servicios adicionales
        double costoTotalProducto =
                this.productoSolicitado.getPrecio() + this.productoSolicitado.getIva();
        double costoDespacho = (10 * this.productoSolicitado.getPrecio()) / 100; //multiplica por el producto solicitado

        //si el iva del producto es mayor a 50000 el precio aumenta en 8000 por recargo
        if (this.productoSolicitado.getIva() > 50000) {
            costoTotalProducto += 8000;
        }

        //Al final se debe mostrar el precio total del pedido desglosado en Pedido más servicios, y
        //guardar si el pago fue realizado.
        System.out.println(
                "*---------------------------------------------------------------*"
        );
        System.out.println("Precio del pedido: \t\t\t" + costoTotalProducto + '$');
        System.out.println("Precio de los servicios: \t" + precioServicios + '$');
        System.out.println("Precio del despacho: \t" + costoDespacho + '$');
        System.out.println(
                "*---------------------------------------------------------------*"
        );

        if (this.pagado) this.precio = costoTotalProducto + precioServicios + costoDespacho;
    }

    /**
     * Mostrar todos los ServiciosAdicionales con envío prime asociados a un tipo de transporte particular
     *
     * @param transporte Tipo de transporte a usar
     * @return String con los envio
     */
    public String mostrarEnviosPrimeTipo(TipoTransporte transporte) {
        ArrayList<EnvioPrime> envios =
                this.serviciosAdicionales.stream().filter((p) -> p instanceof EnvioPrime).map(
                        (p) -> {
                            return (EnvioPrime) p;
                        }).filter((p) -> p.getTipo().equals(transporte))
                        .collect(Collectors.toCollection(ArrayList<EnvioPrime>::new));

        if (envios.isEmpty()) return "No hubieron resultados para la búsqueda...";

        //Mostrar la información de cada uno de los Pedidos encontrados
        StringBuilder toPrint = new StringBuilder();
        envios.forEach(p -> {
            toPrint.append(p.toString());
            toPrint.append('\n');
        });

        return toPrint.toString();
    }
}
