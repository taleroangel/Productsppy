package grupo7.poo.controller;

//Imports

import grupo7.poo.entity.*;
import grupo7.poo.servicioAdicional.BonoRegalo;
import grupo7.poo.servicioAdicional.EnvioPrime;
import grupo7.poo.servicioAdicional.ServicioAdicional;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class ControlDespacho {

    //Static variables
    static final int ANTICIPACION = 2;

    //Asociaciones
    private GestionProductos gestionProductos; //GESTION PRODUCTOS
    private GestionCliente gestionCliente; //GESTION CLIENTE
    private ArrayList<Pedido> listaPedidos; //PEDIDOS

    public ControlDespacho(ArchivoDatos datos) {
        //Datos
        gestionProductos = new GestionProductos(datos);
        gestionCliente = new GestionCliente(datos);
        listaPedidos = datos.getListaPedidos();
    }

    public ControlDespacho() {

    }

    //Getters y Setters

    public ArrayList<EnvioPrime> serviciosTransporte(TipoTransporte e) {
        ArrayList<EnvioPrime> envios = new ArrayList<>();
        for (Pedido p : listaPedidos)
            for (ServicioAdicional s : p.getServiciosAdicionales())
                if (s instanceof EnvioPrime)
                    if (((EnvioPrime) s).getTipo() == e)
                        envios.add((EnvioPrime) s);

        return envios;
    }

    public ArrayList<Pedido> getPedidosCliente(Cliente cliente) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        for (Pedido p : this.listaPedidos)
            if (p.getSolicitante().equals(cliente))
                pedidos.add(p);

        return pedidos;
    }

    public GestionProductos getGestionProductos() {
        return gestionProductos;
    }

    public void setGestionProductos(GestionProductos gestionProductos) {
        this.gestionProductos = gestionProductos;
    }

    public GestionCliente getGestionCliente() {
        return gestionCliente;
    }

    public void setGestionCliente(GestionCliente gestionCliente) {
        this.gestionCliente = gestionCliente;
    }

    @XmlElement(name = "pedido", type = Pedido.class)
    @XmlElementWrapper(name = "listaPedidos")
    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    /**
     * Verificar que los valores de fecha especificada sean válidos, las fechas anteriores a la fecha actual
     * no son fechas válidas así como las fechas anteriores a 2 días
     * <h2> Condiciones para verificar la fecha </h2>
     * <ul>
     *     <li>El año debe ser igual al año actual o mayor</li>
     *     <li>El mes debe ser un número entre 1 y 12</li>
     *     <li>Si el año es igual al año actual entonces el mes tiene que ser mayor o igual al mes actual</li>
     *     <li>El día debe ser un día entre 1 y 31</li>
     *     <li>Si el mes es el mismo, el día tiene que ser mayor al menos 2 días (2 días de antelación)</li>
     * </ul>
     *
     * @param year  Año
     * @param month Mes
     * @param day   Día
     * @return Verdadero si la fecha fue válida, Falso si no
     */
    public static boolean verificarFecha(int year, int month, int day, boolean antelacion) {
        //Si la antelación está desactivada
        if (!antelacion) return verificarFecha(year, month, day);

        //Obtener una instancia de calendario actual
        Calendar calendario_hoy = Calendar.getInstance();
        int yearActual = calendario_hoy.get(Calendar.YEAR);
        int monthActual = calendario_hoy.get(Calendar.MONTH);
        monthActual++; //El mes está desfasado en 1
        int dayActual = calendario_hoy.get(Calendar.DAY_OF_MONTH);

        //Verificar que la fecha sea correcta
        if ((month < 1) || (month > 12)) return false;
        if ((day < 1) || (day > 31)) return false;

        //Si el año es el mismo
        if (year == yearActual) {
            //El mes tiene que ser mayor o igual al mes actual
            if (month >= monthActual) {
                //El día tiene que tener al menos 2 días de antelacion
                if ((day) > (dayActual + ANTICIPACION)) {
                    return true;
                }
            }
        }

        return year > yearActual;//si el año es mayor al año actual retorna true
    }

    /**
     * Verificar que los valores de fecha especificada sean válidos
     * <h2> Condiciones para verificar la fecha </h2>
     * <ul>
     *     <li>El mes debe ser un número entre 1 y 12</li>
     *     <li>El día debe ser un día entre 1 y 31</li>
     * </ul>
     *
     * @param year  Año
     * @param month Mes
     * @param day   Día
     * @return Verdadero si la fecha fue válida, Falso si no
     */
    public static boolean verificarFecha(int year, int month, int day) {
        //Verificar que la fecha sea correcta
        if ((month < 1) || (month > 12)) return false;
        return (day >= 1) && (day <= 31);
    }

    /**
     * Verificar que los valores de fecha especificada sean válidos, las fechas anteriores a la fecha actual
     * no son fechas válidas así como las fechas anteriores a 2 días
     * <h2> Condiciones para verificar la fecha </h2>
     * <ul>
     *     <li>El año debe ser igual al año actual o mayor</li>
     *     <li>El mes debe ser un número entre 1 y 12</li>
     *     <li>Si el año es igual al año actual entonces el mes tiene que ser mayor o igual al mes actual</li>
     *     <li>El día debe ser un día entre 1 y 31</li>
     *     <li>Si el mes es el mismo, el día tiene que ser mayor al menos 2 días (2 días de antelación)</li>
     * </ul>
     *
     * @param calendario Calendario para obtener las fechas
     * @return Verdadero si la fecha fue válida, Falso si no
     */
    public static boolean verificarFecha(Calendar calendario) {
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);
        return verificarFecha(year, ++month, day, true);
    }

    /**
     * Buscar si hay un producto asociado a un pedido
     *
     * @param id UUID del producto a buscar
     * @return Pedido al cual está asociado el producto, si no hay asociación, retorna null
     */
    public Pedido productoEnPedido(UUID id) {
        for (Pedido pedido : this.listaPedidos) {
            if (pedido.getProductoSolicitado().getProdId().equals(id)) {
                return pedido;
            }
        }
        return null;
    }

    /**
     * Buscar si hay un producto asociado a un pedido
     *
     * @param cedula cedula del cliente a verificar
     * @return Pedido al cual está asociado el cluente, si no hay asociacion retorna null
     */
    public Pedido clienteEnPedido(Long cedula) {
        for (Pedido pedido : this.listaPedidos) {
            if (pedido.getSolicitante().getCedula().equals(cedula)) {
                return pedido;
            }
        }
        return null;
    }


    public boolean agregarServicio(
            Pedido pedido, long codigo_S, String nombreServicio, double precio,
            Double distancia, TipoTransporte tipo, Integer nCajas) {
        if (tipo == TipoTransporte.BICICLETA) {
            pedido.getServiciosAdicionales().add(new EnvioPrime(codigo_S, nombreServicio, precio, distancia, tipo, nCajas));
            return true;
        }
        if (tipo == TipoTransporte.MOTO) {

            pedido.getServiciosAdicionales().add(new EnvioPrime(codigo_S, nombreServicio, precio, distancia, tipo, nCajas));
            return true;
        }
        if (tipo == TipoTransporte.MINIVAN) {
            pedido.getServiciosAdicionales().add(new EnvioPrime(codigo_S, nombreServicio, precio, distancia, tipo, nCajas));
            return true;
        }
        return false;
    }

    public boolean agregarServicio(
            Pedido pedido, long codigo_S, String nombre_servicio, double precio,
            String comercioAsociado, String mensaje, Calendar fechaVencimiento) {
        pedido.getServiciosAdicionales().add(
                new BonoRegalo(codigo_S, nombre_servicio, precio, comercioAsociado, mensaje, fechaVencimiento));
        return true;
    }

    public boolean agregarServicio(Pedido pedido, ServicioAdicional servicio) {
        if (pedido == null || servicio == null)
            return false;

        pedido.getServiciosAdicionales().add(servicio);
        return true;
    }

    public boolean reservarPedido(Pedido pedido) {
        //Guardar si el pago fue realizado
        if (pedido.isPagado()) {
            listaPedidos.add(pedido);
            return true;
        }
        return false;
    }


    public void ModificarPedido(
            boolean fecha_modificada,
            boolean nombre_modificado,
            boolean servicios_modificado,
            Pedido pedi,
            ArrayList<ServicioAdicional> services,
            String nuevo_nombre,
            Calendar calendario) {

        //Aplicar los cambios
        if (fecha_modificada) {//del pedido
            modificarFechaPedido(calendario, pedi.getNumPedido());
        }

        if (nombre_modificado) {//del pedido
            //Modificar el nombre del repartidor
            pedi.setNombreRepartidor(nuevo_nombre);
            modificarNombreRepartidor(nuevo_nombre, pedi.getNumPedido());
        }

        if (servicios_modificado) {   //de los servicios adicionales
            pedi.setServiciosAdicionales(services);
        }


    }

    public boolean modificarFechaPedido(Calendar fecha, UUID id) {
        //Verificar si la fecha es válida
        if (!verificarFecha(fecha)) return false;
        //Verificar si el ID es válido
        if (buscarPedido(id) == null) return false;

        buscarPedido(id).setFechaRecibido(fecha);
        return true;
    }

    public boolean modificarNombreRepartidor(String nombre, UUID id) {
        //Verificar si el ID es válido
        if (buscarPedido(id) == null) return false;
        buscarPedido(id).setNombreRepartidor(nombre);
        return true;
    }

    /**
     * Buscar y retornar un pedido
     *
     * @param id UUID del pedido a buscar
     * @return Pedido encontrado o retorna null si no se encontró
     */
    public Pedido buscarPedido(UUID id) {
        for (Pedido p : listaPedidos) {
            if (p.getNumPedido().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Eliminar un pedido de la lista
     *
     * @param id UUID del pedido a retirar
     * @return Pedido retirado
     */
    public Pedido eliminarPedido(UUID id) {
        //Si el pedido no existe
        if (this.buscarPedido(id) == null) return null;
        //Retornar el pedido eliminado
        return listaPedidos.remove(listaPedidos.indexOf(buscarPedido(id)));
    }


    /**
     * Mostrar las personas que tienen un pedido para un mismo producto en una misma fecha
     *
     * @param id_producto UUID del producto a buscar
     * @param fecha       Fecha de envío del producto
     * @return true, si existen pedidos para ese producto y esa ella
     */
    public ArrayList<Pedido> verListadoPedidos_producto_fecha(UUID id_producto, Calendar fecha) {
        Producto producto = gestionProductos.buscarProducto(id_producto);
        if (producto == null) {
            return null;
        }

        ArrayList<Pedido> output = new ArrayList<>();

        boolean elemento_encontrado = false; //Flag para saber si algún elemento ya fue encontrado

        //Calendario del día especificado
        int dia_Parametro = fecha.get(Calendar.DAY_OF_MONTH); //guarda en las variables los datos que diito el usuario en la fehca
        int mes_Parametro = fecha.get(Calendar.MONTH);
        int anno_Parametro = fecha.get(Calendar.YEAR);

        int dia, mes, anno; //Día mes y año de la fecha del pedido

        for (Pedido pedido : this.listaPedidos) { //recorre los pedidos
            //Obtener los valores numéricos del calendario
            dia = pedido.getFechaRecibido().get(Calendar.DAY_OF_MONTH); //guarda en cada variable la fhecha de los pedidos
            mes = pedido.getFechaRecibido().get(Calendar.MONTH);
            anno = pedido.getFechaRecibido().get(Calendar.YEAR);

            if (anno >= anno_Parametro && dia >= dia_Parametro && mes >= mes_Parametro) {
                if (pedido.getProductoSolicitado().getProdId().equals(id_producto)) {
                    //Encontramos un pedido que cumple las condiciones.
                    elemento_encontrado = true;
                    output.add(pedido);
                }
            }
        }
        return output;
    }

    public ArrayList<Producto> productosTipoFruver() {
        ArrayList<Producto> lista = new ArrayList<>(); //retorna la lista de productos fruver
        for (Producto pro : this.gestionProductos.getListaProductos().values()) {
            if (pro != null) {
                if (pro instanceof Fruver) {
                    lista.add(pro);
                }
            }
        }
        return lista;
    }

    public ArrayList<Producto> productosTipoAseo() {
        ArrayList<Producto> lista = new ArrayList<>(); //retorna productos tipo aseo
        for (Producto pro : this.gestionProductos.getListaProductos().values()) {
            if (pro != null) {
                if (pro instanceof Aseo) {
                    lista.add(pro);
                }
            }
        }
        return lista;
    }

    public double precioPedidosDeAseoPorTipo(TipoProducto tipoABuscar) {
        double preciototal = 0.0d;
        for (Pedido pedi : this.getListaPedidos()) {
            if (pedi != null) {
                if (pedi.getProductoSolicitado() instanceof Aseo) {
                    if (((Aseo) pedi.getProductoSolicitado()).getTipo() == tipoABuscar) {
                        preciototal = preciototal + pedi.getPrecio(); //busca en los pedidos el producto dependiendo del tipo a buscar //si los encuentra lo suma
                    }
                }
            }
        }
        return preciototal;
    }
}