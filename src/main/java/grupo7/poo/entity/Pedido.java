package grupo7.poo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import grupo7.poo.servicioAdicional.BonoRegalo;
import grupo7.poo.servicioAdicional.EnvioPrime;
import grupo7.poo.servicioAdicional.ServicioAdicional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

//Imports
@XmlRootElement
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

    @XmlElement
    public Double getPrecio() {
        this.getPrecioTotal();
        return precio;
    }

    @XmlElement
    public UUID getNumPedido() {
        return numPedido;
    }

    @XmlElement
    public Calendar getFechaRecibido() {
        return fechaRecibido;
    }

    @JsonIgnore
    public String getFechaRecibidoString() {
        if (fechaRecibido == null)
            fechaRecibido = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = fechaRecibido.getTime();
        return dateFormat.format(date);
    }

    @JsonIgnore
    public ServicioAdicional buscarServicioPorId(Long id) {
        for (ServicioAdicional s : this.serviciosAdicionales)
            if (s.getCodigoServicio() == id)
                return s;
        return null;
    }

    public void setFechaRecibido(Calendar fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    @XmlElement
    public boolean isPagado() {
        return pagado;
    }

    @JsonIgnore
    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    @XmlElement
    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    //Getters y Setters (Asociaciones)
    @XmlElement
    public Cliente getSolicitante() {
        return solicitante;
    }

    @JsonIgnore
    public String getDireccionSolicitante() {
        return solicitante.getDireccion();
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @JsonIgnore
    public String getClienteNombre() {
        return getSolicitante().getNombreCompleto();
    }

    public void setSolicitante(Cliente solicitante) {
        this.solicitante = solicitante;
    }

    //Important XML Element
    @XmlElementWrapper(name = "servicios")
    @XmlElements({
            @XmlElement(name = "bonoRegalo", type = BonoRegalo.class),
            @XmlElement(name = "envioPrime", type = EnvioPrime.class)
    })
    public ArrayList<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(
            ArrayList<ServicioAdicional> serviciosAdicionales
    ) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    @XmlElement
    public Producto getProductoSolicitado() {
        return productoSolicitado;
    }

    @JsonIgnore
    public String getProductoNombre() {
        return productoSolicitado.getNombreComercial();
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
    @JsonIgnore
    public double getPrecioTotal() {
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

        if (this.pagado) this.precio = costoTotalProducto + precioServicios + costoDespacho;

        return costoTotalProducto + precioServicios + costoDespacho;
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
