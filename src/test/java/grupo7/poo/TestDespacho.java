package grupo7.poo;

import static org.junit.Assert.assertTrue;

import grupo7.poo.controller.ControlDespacho;
import grupo7.poo.controller.GestionCliente;
import grupo7.poo.controller.GestionProductos;
import grupo7.poo.entity.Cliente;
import grupo7.poo.entity.Pedido;
import grupo7.poo.entity.Producto;
import grupo7.poo.entity.TipoTransporte;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class TestDespacho {
    //1
    @Test
    public void testListadoProductoValido() {
        //Test válido
        Assert.assertNotNull(new GestionProductos().imprimirListaProductos());
    }

    @Test
    public void testListadoProductoFallido() {
        //Test fallido
        Assert.assertNull(new GestionProductos().imprimirListaProductos());
    }

    //2
    @Test
    public void testInsertarProductoValido() {
        //Test válido
        GestionProductos gestor = new GestionProductos();
        Producto producto = new Producto("Nombre", 0L, "Tienda");
        Assert.assertTrue(gestor.insertarProducto(producto));
    }

    @Test
    public void testInsertarProductoFallido() {
        GestionProductos gestor = new GestionProductos();
        Producto producto = new Producto("Nombre", 0L, "Tienda");
        gestor.insertarProducto(producto);
        //Test fallido
        Assert.assertTrue(gestor.insertarProducto(producto));
    }

    //3
    @Test
    public void testModificarProductoValido() {
        GestionProductos gestor = new GestionProductos();
        Producto producto = new Producto("Nombre", 0L, "Tienda");
        gestor.insertarProducto(producto);

        //Test válido
        Assert.assertTrue(gestor.modificarNombreProducto("Nuevo Nombre", producto.getProdId()));
        Assert.assertTrue(gestor.modificarNombreTienda("NombreTienda", producto.getProdId()));
        Assert.assertTrue(gestor.modificarPrecioProducto(1L, producto.getProdId()));
    }

    @Test
    public void testModificarProductoFallido() {
        GestionProductos gestor = new GestionProductos();
        Producto producto = new Producto("Nombre", 0L, "Tienda");
        //Test fallido
        Assert.assertTrue(gestor.modificarNombreProducto("Nuevo Nombre", producto.getProdId()));
        Assert.assertTrue(gestor.modificarNombreTienda("NombreTienda", producto.getProdId()));
        Assert.assertTrue(gestor.modificarPrecioProducto(1L, producto.getProdId()));
    }

    //4
    @Test
    public void testEliminarProductoValido() {
        GestionProductos gestor = new GestionProductos();
        Producto producto = new Producto("Nombre", 0L, "Tienda");
        gestor.insertarProducto(producto);

        //Prueba válida
        Assert.assertNotNull(gestor.eliminarProducto(producto.getProdId()));
    }

    @Test
    public void testEliminarProductoFallido() {
        GestionProductos gestor = new GestionProductos();
        Producto producto = new Producto("Nombre", 0L, "Tienda");
        gestor.insertarProducto(producto);
        gestor.eliminarProducto(producto.getProdId());

        //Prueba inválida
        Assert.assertNotNull(gestor.eliminarProducto(producto.getProdId()));
    }

    //5
    @Test
    public void testVerListadoClienteValido() {
        //Prueba válida
        GestionCliente gestor = new GestionCliente();
        Assert.assertNotNull(gestor.verListadoClientes());
    }

    @Test
    public void testVerListadoClienteFallido() {
        //Prueba Fallida
        GestionCliente gestor = new GestionCliente();
        Assert.assertNull(gestor.verListadoClientes());
    }

    //6
    @Test
    public void testInsertarClienteValido() {
        GestionCliente gestor = new GestionCliente();
        Cliente nuevo = new Cliente(0L, "Nombre", 0L, "Dirección");
        Assert.assertTrue(gestor.insertarCliente(nuevo));
        Assert.assertFalse(gestor.insertarCliente(nuevo));
    }

    @Test
    public void testInsertarClienteFallido() {
        GestionCliente gestor = new GestionCliente();
        Cliente nuevo = new Cliente(0L, "Nombre", 0L, "Dirección");
        gestor.insertarCliente(nuevo);
        Assert.assertTrue(gestor.insertarCliente(nuevo));
    }

    //7
    @Test
    public void testModificarDatosDeClienteValido() {
        GestionCliente gestor = new GestionCliente();
        Cliente nuevo = new Cliente(0L, "Nombre", 0L, "Dirección");
        Assert.assertTrue(gestor.insertarCliente(nuevo));
        Assert.assertNotNull(gestor.buscarCliente(nuevo.getCedula()));
        Cliente obtener = gestor.buscarCliente(nuevo.getCedula());
        Assert.assertNotNull(obtener.toString());
    }

    @Test
    public void testModificarDatosDeClienteFallido() {
        GestionCliente gestor = new GestionCliente();
        Cliente nuevo = new Cliente(0L, "Nombre", 0L, "Dirección");
        Assert.assertNotNull(gestor.buscarCliente(nuevo.getCedula()));
    }

    //8
    @Test
    public void testEliminarClienteValida() {
        GestionCliente gestor = new GestionCliente();
        Cliente nuevo = new Cliente(0L, "Nombre", 0L, "Dirección");
        Assert.assertTrue(gestor.insertarCliente(nuevo));
        Assert.assertNotNull(gestor.buscarCliente(nuevo.getCedula()));
        Cliente obtener = gestor.buscarCliente(nuevo.getCedula());
        Assert.assertNotNull(gestor.eliminarCliente(obtener.getCedula()));
    }

    @Test
    public void testEliminarClienteFallida() {
        GestionCliente gestor = new GestionCliente();
        Cliente nuevo = new Cliente(0L, "Nombre", 0L, "Dirección");
        Assert.assertFalse(gestor.insertarCliente(nuevo));
        Assert.assertNull(gestor.buscarCliente(nuevo.getCedula()));
        Cliente obtener = gestor.buscarCliente(nuevo.getCedula());
        Assert.assertNull(gestor.eliminarCliente(obtener.getCedula()));
    }

    //9
    @Test
    public void testRealizarPedidoValido() {
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Assert.assertNotNull(gc.buscarCliente(cedula));
        Assert.assertNotNull(gp.buscarProducto(idp));

        Assert.assertFalse(ControlDespacho.verificarFecha(Calendar.getInstance()));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                true,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        ControlDespacho cd = new ControlDespacho();
    }

    @Test
    public void testRealizarPedidoFallido() {
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Assert.assertNotNull(gc.buscarCliente(cedula));
        Assert.assertNotNull(gp.buscarProducto(idp));

        Assert.assertFalse(ControlDespacho.verificarFecha(Calendar.getInstance()));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                false,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        ControlDespacho cd = new ControlDespacho();
        Assert.assertTrue(cd.reservarPedido(pedido));
    }

    //10
    @Test
    public void testModificarPedidoValido() {
        //Crear un pedido
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Assert.assertNotNull(gc.buscarCliente(cedula));
        Assert.assertNotNull(gp.buscarProducto(idp));

        Assert.assertFalse(ControlDespacho.verificarFecha(Calendar.getInstance()));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                true,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        ControlDespacho cd = new ControlDespacho();

        //Verificacion
        Assert.assertNotNull(pedido.getNumPedido());
        Assert.assertNotNull(pedido.getNombreRepartidor());
        Assert.assertNotNull(pedido.getServiciosAdicionales());
    }

    @Test
    public void testModificarPedidoFallido() {
        ControlDespacho cd = new ControlDespacho();
        Pedido pedido = cd.buscarPedido(UUID.randomUUID());
        Assert.assertNotNull(pedido);
        //Verificacion
        Assert.assertNotNull(pedido.getNumPedido());
        Assert.assertNotNull(pedido.getNombreRepartidor());
        Assert.assertNotNull(pedido.getServiciosAdicionales());
    }

    //11
    @Test
    public void testEliminarPedidoValido() {
        //Realizar un pedido
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Assert.assertNotNull(gc.buscarCliente(cedula));
        Assert.assertNotNull(gp.buscarProducto(idp));

        Assert.assertFalse(ControlDespacho.verificarFecha(Calendar.getInstance()));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                true,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        ControlDespacho cd = new ControlDespacho();
        cd.reservarPedido(pedido);

        //Eliminar un pedido
        Assert.assertNotNull(cd.eliminarPedido(pedido.getNumPedido()));
    }

    @Test
    public void testEliminarPedidoFallido() {
        //Realizar un pedido
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Assert.assertNotNull(gc.buscarCliente(cedula));
        Assert.assertNotNull(gp.buscarProducto(idp));

        Assert.assertFalse(ControlDespacho.verificarFecha(Calendar.getInstance()));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                true,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        ControlDespacho cd = new ControlDespacho();

        //Eliminar un pedido
        Assert.assertNotNull(cd.eliminarPedido(pedido.getNumPedido()));
    }

    //12
    @Test
    public void testVerListadoPedidosValido() {
        ControlDespacho cd = new ControlDespacho();
        Assert.assertNotNull(cd.verListadoPedidos());
    }

    @Test
    public void testVerListadoPedidosFallido() {
        ControlDespacho cd = new ControlDespacho();
        Assert.assertNull(cd.verListadoPedidos());
    }

    //13
    @Test
    public void testVerListadoPedidos_Producto_FechaValido() {
        //Obtener un pedido
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Assert.assertNotNull(gc.buscarCliente(cedula));
        Assert.assertNotNull(gp.buscarProducto(idp));

        Assert.assertFalse(ControlDespacho.verificarFecha(Calendar.getInstance()));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                true,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        ControlDespacho cd = new ControlDespacho();
        Assert.assertNotNull(cd.verListadoPedidos_producto_fecha(
                pedido.getNumPedido(), Calendar.getInstance()
        ));
    }

    @Test
    public void testVerListadoPedidos_Producto_FechaFallido() {
        //Obtener un pedido
        GestionCliente gc = new GestionCliente();
        GestionProductos gp = new GestionProductos();
        ControlDespacho cd = new ControlDespacho();

        long cedula = 0L;
        UUID idp = UUID.randomUUID();
        gc.insertarCliente(new Cliente(cedula, "Nombre", 0L, "Direccion"));
        gp.insertarProducto(new Producto("Producto", 0L, "Tienda", idp));

        Pedido pedido = new Pedido(
                Calendar.getInstance(),
                true,
                "Nombre Repartido",
                gp.buscarProducto(idp),
                gc.buscarCliente(cedula)
        );

        Assert.assertNull(cd.verListadoPedidos_producto_fecha(
                pedido.getNumPedido(), Calendar.getInstance()
        ));
    }
}
