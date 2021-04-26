package grupo7.poo.boundary;

import grupo7.poo.controller.ControlDespacho;

import java.util.*;

import grupo7.poo.entity.*;
import grupo7.poo.servicioAdicional.ServicioAdicional;

import static grupo7.poo.controller.ControlDespacho.verificarFecha;

public class PantallaDeDespacho {
    //Constantes

    //Constante para definir separadores
    private static final String separador = "\u001B[35m*---------------------------------------------------------------*\u001B[0m";
    private static final String separador_largo = "\u001B[35m#---------------------------------------------------------------------------------------------#\u001B[0m";

    //Instance initializer
    {
        this.centralDespacho = new ControlDespacho();
    }

    //Asociaciones
    private final ControlDespacho centralDespacho;  //Central de despacho

    public PantallaDeDespacho(boolean modo_de_pruebas) {
        if (modo_de_pruebas) {
            //Agregar productos 3
            this.centralDespacho.getGestionProductos().insertarProducto(
                    new Producto("Shampoo antipulgas para perro", 25900, "Supermercado superéxito")
            );
            this.centralDespacho.getGestionProductos().insertarProducto(
                    new Producto("Chocolates para regalar", 15000, "Dulces sueños")
            );
            this.centralDespacho.getGestionProductos().insertarProducto(
                    new Producto("Jabón para lavar ropa", 1900, "Surtivecino")
            );
            //Agregar productor fruver 3
            Fruver producto4 = new Fruver("canasta de verdurass", 44000, "surtideli", false, 8567, "hacienda san jose");
            Fruver producto5 = new Fruver("fresas 100 frescas", 34000, "surtimax", true, 4678, "hacienda san silvestre");
            Fruver producto6 = new Fruver("fertilizantes", 34000, "plantas y decoraciones", false, 6432, "hacienda de las margaritas");
            Fruver producto7 = new Fruver("canasta de frutas", 32000, "fruteria patty", true, 3432, "hacienda napoles");


            this.centralDespacho.getGestionProductos().insertarProducto(producto4);
            this.centralDespacho.getGestionProductos().insertarProducto(producto5);
            this.centralDespacho.getGestionProductos().insertarProducto(producto6);
            this.centralDespacho.getGestionProductos().insertarProducto(producto7);

            // Agregar productos Aseo
            Aseo producto8 = new Aseo("spray anti manchas", 34567, "limpia ya!", "home center", true, TipoProducto.HOGAR);
            Aseo producto9 = new Aseo("trapos anti polvo", 98765, "aseo markplace", "poliquimicos", true, TipoProducto.HOGAR);
            Aseo producto10 = new Aseo("Recursos industriales", 12345, "super clean!", "Aseo Yem Sas", true, TipoProducto.INDUSTRIAL);
            Aseo producto11 = new Aseo("kit de limpieza de hospitales", 65437, "burbujas", "MAX center", true, TipoProducto.HOSPITALARIO);

            this.centralDespacho.getGestionProductos().insertarProducto(producto8);
            this.centralDespacho.getGestionProductos().insertarProducto(producto9);
            this.centralDespacho.getGestionProductos().insertarProducto(producto10);
            this.centralDespacho.getGestionProductos().insertarProducto(producto11);


            //Agregar clientes 5
            Cliente cliente1 = new Cliente(52221850L, "Carlos Alberto Martínez", 755749, "Kr 7ma #58-36 Sur");
            this.centralDespacho.getGestionCliente().insertarCliente(cliente1);
            Cliente cliente2 = new Cliente(75851566L, "Andrea Juliana Moreno", 30538956, "Calle 94 #89-85 Casa 89");
            this.centralDespacho.getGestionCliente().insertarCliente(cliente2);
            Cliente cliente3 = new Cliente(55847986L, "Julián Antonio Zamora", 5222185, "Cra. 8 sur #89-89B");
            this.centralDespacho.getGestionCliente().insertarCliente(cliente3);
            Cliente cliente4 = new Cliente(25839852L, "Nelson Rodriguez Fernandez", 5678543, "Cra. 34a sur #45-14B");
            this.centralDespacho.getGestionCliente().insertarCliente(cliente4);
            Cliente cliente5 = new Cliente(15889848L, " heyling Burgos Algarin", 53241857, "Cra. 25 sur #13-65A");
            this.centralDespacho.getGestionCliente().insertarCliente(cliente5);

            this.centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.APRIL, 29),
                    true, "Pedro", producto4, cliente1));

            this.centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.APRIL, 30),
                    true, "Johan", producto5, cliente2));

            this.centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.MAY, 9),
                    true, "Pedro", producto8, cliente3));

            this.centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.MAY, 15),
                    true, "Carlos", producto6, cliente4));

            this.centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.MAY, 20),
                    true, "Cristian", producto5, cliente5));

            System.out.println("La información de pruebas fue insertada correctamente");
        }
    }

    //Métodos
    public static void main(String[] args) {

        int n_anno, n_mes, n_dia;
        Calendar calendario = Calendar.getInstance();
        boolean dummy;
        char selec;
        short sel;
        String ID_P;

        //Variables
        short opcion0 = 0;       // opcion del menu en general

        //Variables para producto
        PantallaDeDespacho pantalla = new PantallaDeDespacho(true);

        //Crear el scanner
        Scanner scaner = new Scanner(System.in);
        scaner.useDelimiter("\n"); //Usar \n como delimitador de Strings

        //------------------------------------------- // 4.5 INTERACCION DE OBJETOS // ----------------------------------------
        System.out.println(pantalla.centralDespacho.productosTipoFruver().toString());
        System.out.println(pantalla.centralDespacho.productosTipoAseo().toString());

        do {
            System.out.println(separador_largo);
            System.out.println("\u001B[36mMenú Principal\u001B[0m");
            System.out.println("1. Ver listado de productos disponibles");
            System.out.println("2. Insertar productos");
            System.out.println("3. Modificar Productos");
            System.out.println("4. Eliminar productos");
            System.out.println("5. Ver listado de clientes");
            System.out.println("6. Insertar cliente");
            System.out.println("7. Modificar datos de un cliente");
            System.out.println("8. Eliminar un cliente");
            System.out.println("9. Realizar el pedido de un producto");
            System.out.println("10. Modificar un Pedido de un Producto");
            System.out.println("11. Eliminar un pedido de un producto");
            System.out.println("12. Ver listado de Pedidos existentes");
            System.out.println("13. Ver listado de Pedidos existentes para un Producto y posterior a una fecha específica");
            System.out.println("14. Salir");

            //Leer opción del usuario
            System.out.print("Seleccione una opción: ");
            opcion0 = scaner.nextShort();                        //por si alguien pone string?

            //En caso de que se coloque una opción inválida
            while (opcion0 < 1 || opcion0 > 14) {
                System.out.println("Opción inválida!!");
                System.out.print("Porfavor seleccione una opción correcta: ");
                opcion0 = scaner.nextShort();
            }

            switch (opcion0) {
                case 1://Ver listado de productos disponibles (REPARADO)
                    System.out.println(separador_largo);
                    System.out.print("Desea ver la lista de productos? (s/n): ");
                    char opcion1 = scaner.next().charAt(0);

                    while (opcion1 != 'n' && opcion1 != 'N' && opcion1 != 's' && opcion1 != 'S') {
                        System.out.println("Opción inválida");
                        System.out.println("  ");
                        opcion1 = scaner.next().charAt(0);
                    }
                    if (opcion1 == 's' || opcion1 == 'S') {
                        System.out.println(pantalla.centralDespacho.getGestionProductos().imprimirListaProductos());
                    } else break;
                    break;

                case 2: //Insertar productos (REPARADO)
                    System.out.println(separador_largo);
                    System.out.print("Desea agregar un Producto? (s/n): ");
                    char opcion2 = scaner.next().charAt(0);

                    while (opcion2 != 'n' && opcion2 != 'N' && opcion2 != 's' && opcion2 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea agregar un producto? (s/n):  ");
                        opcion2 = scaner.next().charAt(0);
                    }

                    if (opcion2 == 's' || opcion2 == 'S') {
                        System.out.print("Digite el nombre del producto: ");//creo el producto
                        String nombre_comercial = scaner.next();

                        System.out.print("Digite el nombre de la tienda: ");
                        String nombre_tienda = scaner.next();

                        System.out.print("Digite el precio del producto: ");
                        double precio = scaner.nextDouble();
                        Producto obj = new Producto(nombre_comercial, precio, nombre_tienda);
                        System.out.println(separador);
                        System.out.println(
                                pantalla.centralDespacho.getGestionProductos().insertarProducto(obj)
                                        ? "Producto insertado :D!" : "El producto ya existe!"
                        );
                    } else break;

                    break;

                case 3: //Modificar (REPARADO)
                    System.out.println(separador_largo);

                    System.out.print("Desea modificar un producto? (s/n): ");
                    char opcion3 = scaner.next().charAt(0);

                    while (opcion3 != 'n' && opcion3 != 'N' && opcion3 != 's' && opcion3 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea modificar un producto? (s/n): ");
                        opcion3 = scaner.next().charAt(0);

                    }
                    if (opcion3 == 's' || opcion3 == 'S') {

                        System.out.print("Porfavor Ingrese el ID del producto: ");//pedimos el codigo del producto
                        String ID = scaner.next();

                        if (pantalla.centralDespacho.getGestionProductos().buscarProducto(UUID.fromString(ID)) == null) {
                            System.out.println("El producto no existe!!");
                            break;
                        }

                        //While loop
                        do {
                            //Qué variable será modificada?
                            System.out.println("Qué desea modificar?...");
                            System.out.println("1. Nombre Comercial");                                                              //DATOS CONSOLA
                            System.out.println("2. Nombre de la tienda");
                            System.out.println("3. Precio del producto");
                            System.out.println("4. Salir");

                            //Pedir una opción por consola
                            System.out.print("Seleccione una opción: ");
                            opcion0 = scaner.nextShort();

                            //En caso de digitar una opción incorrecta
                            while (opcion0 < 1 || opcion0 > 4) {
                                System.out.println("Opción incorrecta...");
                                System.out.print("Seleccione una opción: ");
                                opcion0 = scaner.nextShort();
                            }

                            //Manejar los casos
                            String dummyString;
                            double precio;

                            System.out.println(separador);
                            switch (opcion0) {//Se escoge que dato se desea modificar
                                case 1:
                                    System.out.print("Escoga un nuevo nombre para el producto: ");
                                    dummyString = scaner.next();
                                    System.out.println(
                                            pantalla.centralDespacho.getGestionProductos().modificarNombreProducto(
                                                    dummyString, UUID.fromString(ID))
                                                    ? "Cambio realizado con éxito" : "El producto no existe!"
                                    );
                                    break;

                                case 2:
                                    System.out.print("Escoga un nuevo nombre para el comercio: ");
                                    dummyString = scaner.next();
                                    System.out.println(
                                            pantalla.centralDespacho.getGestionProductos().modificarNombreTienda(
                                                    dummyString, UUID.fromString(ID))
                                                    ? "Cambio realizado con éxito" : "El producto no existe!"
                                    );
                                    break;

                                case 3:
                                    System.out.print("Escoga un nuevo precio para el producto: ");
                                    precio = scaner.nextDouble();
                                    System.out.println(
                                            pantalla.centralDespacho.getGestionProductos().modificarPrecioProducto(
                                                    precio, UUID.fromString(ID))
                                                    ? "Cambio realizado con éxito" : "El producto no existe!"
                                    );
                                    break;

                                default:
                                    System.out.println("Opción incorrecta, intente de nuevo");
                                    break;

                            }
                            Producto producto_nuevo = pantalla.centralDespacho.getGestionProductos().buscarProducto(UUID.fromString(ID));
                            if (producto_nuevo != null) {
                                System.out.println("*---------------------- Producto modificado --------------------*");
                                System.out.println(producto_nuevo);
                                System.out.println(separador);
                            }
                        } while (opcion0 != 4);
                    } else break;
                    //Modificar productos
                    break;

                case 4: //Eliminar productos (REPARADO)
                    System.out.println(separador_largo);

                    System.out.print("Desea eliminar un producto? (s/n): ");
                    opcion3 = scaner.next().charAt(0);

                    while (opcion3 != 'n' && opcion3 != 'N' && opcion3 != 's' && opcion3 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea eliminar un producto? (s/n): ");
                        opcion3 = scaner.next().charAt(0);
                    }

                    if (opcion3 == 's' || opcion3 == 'S') {

                        //Primero verificar si el producto existe
                        System.out.println("Porfavor Ingrese el ID del producto:"); //pedimos el codigo del producto
                        String ID = scaner.next();
                        if (pantalla.centralDespacho.getGestionProductos().buscarProducto(UUID.fromString(ID)) != null) {//verifica si existe el producto

                            //Verificar que ningún pedido lo tenga
                            if (pantalla.centralDespacho.productoEnPedido(UUID.fromString(ID)) == null) {//si el producto no tiene el pedido
                                //verifica si el producto no esta en los pedido
                                System.out.print("Seguro que desea eliminar el producto? (s/n): ");
                                opcion3 = scaner.next().charAt(0);

                                while (opcion3 != 'n' && opcion3 != 'N' && opcion3 != 's' && opcion3 != 'S') {
                                    System.out.println("Opción Invalida");
                                    System.out.print("Seguro que desea eliminar el producto? (s/n): ");
                                    opcion3 = scaner.next().charAt(0);
                                }

                                if (opcion3 == 's' || opcion3 == 'S') {     //definitivamente lo quiero eliminar
                                    System.out.println(separador);
                                    System.out.println(
                                            "El producto de código: '"
                                                    + pantalla.centralDespacho.getGestionProductos().eliminarProducto(UUID.fromString(ID)) +
                                                    "' fue eliminado con éxito...");
                                } else {
                                    System.out.println("Cancelaste la operación....");
                                }

                            } else {
                                System.out.println("No puedes eliminar este producto, se encuentra asociado a un pedido....");
                            }
                            //FIN verificar que ningún pedido tenga el producto


                        } else {
                            System.out.println("El producto no existe...");
                        }
                        //FIN verificar que el producto existe

                    } else System.out.println("Cancelaste la operación....");

                    break;

                case 5: //Ver listado de Clientes registrados en el sistema (REPARADO)
                    System.out.println(separador_largo);

                    System.out.print("Desea ver la lista de clientes? (s/n): ");
                    char opcion5 = scaner.next().charAt(0);

                    while (opcion5 != 'n' && opcion5 != 'N' && opcion5 != 's' && opcion5 != 'S') {
                        System.out.println("Opción inválida");
                        System.out.print("Desea ver la lista de clientes? (s/n): ");
                        opcion5 = scaner.next().charAt(0);
                    }
                    if (opcion5 == 's' || opcion5 == 'S') {
                        System.out.println(pantalla.centralDespacho.getGestionCliente().verListadoClientes());
                    } else break;

                    break;

                case 6: //Insertar Clientes (REPARADO)
                    System.out.println(separador_largo);
                    System.out.print("Desea agregar un cliente? (s/n): ");
                    char opcion6 = scaner.next().charAt(0);

                    while (opcion6 != 'n' && opcion6 != 'N' && opcion6 != 's' && opcion6 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea agregar un cliente? (s/n):  ");
                        opcion6 = scaner.next().charAt(0);
                    }

                    if (opcion6 == 's' || opcion6 == 'S') {
                        System.out.print("Ingrese el nombre completo: ");//creo el cliente
                        String nombre_completo = scaner.next();

                        System.out.print("Ingrese su cedula: ");
                        long cedula = scaner.nextLong();

                        System.out.print("Ingrese el número de teléfono: ");
                        long telefono = scaner.nextLong();
                        System.out.print("Ingrese la direccion: ");
                        String direccion = scaner.next();
                        System.out.println(
                                pantalla.centralDespacho.getGestionCliente().insertarCliente(
                                        new Cliente(cedula, nombre_completo, telefono, direccion))
                                        ? "Cliente insertado con éxito!" : "El cliente ya existe!");
                    } else break;
                    break;

                case 7: //Modificar datos de Cliente
                    System.out.println(separador_largo);
                    System.out.print("Desea modificar un cliente? (s/n): ");
                    char opcion7 = scaner.next().charAt(0);

                    while (opcion7 != 'n' && opcion7 != 'N' && opcion7 != 's' && opcion7 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea modificar un cliente? (s/n): ");
                        opcion7 = scaner.next().charAt(0);
                    }

                    if (opcion7 == 's' || opcion7 == 'S') {
                        System.out.println("Digite la cedula del cliente que quiere modificar: ");
                        long cedula = scaner.nextLong();
                        /*pantalla.centralDespacho.getGestionCliente().modificarCliente(cedula);*/


                        // Buscar si el cliente existe
                        // si el cliente existe
                        // Coger el cliente


                        // MENU DATO A
                        // MODIFICAR*---------------------------------------------------------------*-------
                        sel = 0; // SeleccionarMenu
                        String nuevoDatoS; // Nombre o direccion
                        long nuevoDatoL; // cedula o teléfono

                        do {
                            System.out.println("1. Modificar nombre");
                            System.out.println("2. Modificar teléfono");
                            System.out.println("3. Modificar Direccion");
                            System.out.println("0. Salir");

                            System.out.print("Por favor seleccione una opción: ");
                            sel = scaner.nextShort();

                            while (sel < 0 || sel > 3) {
                                System.out.println("Opción inválida!!");
                                System.out.print("Por favor seleccione una opción correcta: ");
                                sel = scaner.nextShort();
                            }

                            switch (sel) {
                                case 1:
                                    System.out.print("Ingrese el nuevo nombre del cliente: ");
                                    nuevoDatoS = scaner.next();
                                    if (pantalla.centralDespacho.getGestionCliente().modificarCliente(cedula, sel, nuevoDatoS)) {//modificar cliente retorna bool
                                        System.out.println(separador);
                                        System.out.println("Cliente modificado!!");

                                        System.out.println(separador);


                                    } else {
                                        System.out.println("no se pudo modificar el ciente");
                                        System.out.println(separador);

                                    }

                                    break;
                                case 2:
                                    System.out.println("Ingrese el nuevo teléfono del cliente: ");
                                    nuevoDatoL = scaner.nextLong();
                                    System.out.println(separador);
                                    if (pantalla.centralDespacho.getGestionCliente().ModificarCliente(cedula, nuevoDatoL)) {

                                        System.out.println("Cliente modificado!!");

                                        System.out.println(separador);
                                    } else {
                                        System.out.println("no se pudo modificar el ciente");
                                        System.out.println(separador);
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese la nueva direccion del cliente: ");
                                    nuevoDatoS = scaner.next();

                                    if (pantalla.centralDespacho.getGestionCliente().modificarCliente(cedula, sel, nuevoDatoS)) {
                                        System.out.println(separador);
                                        System.out.println("Cliente modificado!!");
                                        System.out.println(separador);
                                    } else {
                                        System.out.println("no se pudo modificar el ciente");
                                        System.out.println(separador);
                                    }
                                    break;
                                case 0:
                                    System.out.println("Gracias por su atencion :D");
                                    break;
                            }

                        } while (sel != 0);


                    } else break;

                    break;

                case 8: // Eliminar un Cliente (REPARADO)
                    System.out.println(separador_largo);

                    System.out.print("Desea eliminar un cliente? (s/n): ");
                    char opcion8 = scaner.next().charAt(0);

                    while (opcion8 != 'n' && opcion8 != 'N' && opcion8 != 's' && opcion8 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea eliminar un cliente? (s/n): ");
                        opcion8 = scaner.next().charAt(0);
                    }

                    if (opcion8 == 's' || opcion8 == 'S') {

                        //Primero verificar si el cliente existe
                        System.out.println("Porfavor Ingrese la cedula del cliente:"); //pedimos el codigo del producto
                        long cedula = scaner.nextLong();
                        if (pantalla.centralDespacho.getGestionCliente().buscarCliente(cedula) != null) {//verifica si esta en la lista

                            //Verificar que ningún pedido lo tenga
                            if (pantalla.centralDespacho.clienteEnPedido(cedula) == null) {//si el cliente no esta asociado al pedido

                                System.out.print("Seguro que desea eliminar el cliente? (s/n): ");
                                opcion8 = scaner.next().charAt(0);

                                while (opcion8 != 'n' && opcion8 != 'N' && opcion8 != 's' && opcion8 != 'S') {
                                    System.out.println("Opción Invalida");
                                    System.out.print("Seguro que desea eliminar el cliente? (s/n): ");
                                    opcion8 = scaner.next().charAt(0);
                                }

                                if (opcion8 == 's' || opcion8 == 'S') {     //definitivamente lo quiero eliminar
                                    //ELIMINAR EL CLIENTE
                                    Cliente clnte = pantalla.centralDespacho.getGestionCliente().eliminarCliente(cedula);
                                    if (clnte != null)
                                        System.out.println("El cliente de cedula: " + clnte.getCedula() + " fue eliminado con éxito...");
                                } else {
                                    System.out.println("Cancelaste la operación....");
                                }

                            } else {
                                System.out.println("No puedes eliminar este cliente, se encuentra asociado a un pedido....");
                            }
                            //FIN verificar que ningún pedido tenga el cliente


                        } else {
                            System.out.println("El cliente no existe...");
                        }
                        //FIN verificar que el cliente existe

                    } else {
                        System.out.println("Cancelaste la operación....");
                    }

                    break;
                case 9: // Realizar el Pedido de un Producto
                    System.out.println(separador_largo);
                    ID_P = "n";
                    long cedula_C = 0;
                    System.out.print("Desea realizar el pedido de un producto? (s/n): ");
                    char opcion9 = scaner.next().charAt(0);

                    while (opcion9 != 'n' && opcion9 != 'N' && opcion9 != 's' && opcion9 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea realizar el pedido de un producto? (s/n): ");
                        opcion9 = scaner.next().charAt(0);
                    }

                    if (opcion9 == 's' || opcion9 == 'S') {
                        System.out.print("Digite el ID del producto: ");
                        ID_P = scaner.next();

                        System.out.print("Digite la cédula del cliente: ");
                        cedula_C = scaner.nextLong();
                    } else break;
                    selec = 0; //Variable para guardar la seleccion (s/n) del usuario

                    Cliente cliente = pantalla.centralDespacho.getGestionCliente().buscarCliente(cedula_C); //Cliente asociado a la cedula
                    Producto producto = pantalla.centralDespacho.getGestionProductos().buscarProducto(UUID.fromString(ID_P)); //Producto asociado al UUID

                    //Verificar que el pedido exista
                    if (producto == null) {
                        System.out.println("El producto no existe!!");
                        return;
                    }

                    //Verificar que el cliente exista
                    if (cliente == null) {
                        System.out.println("El cliente no existe!!");
                        return;
                    }

                    //---------PEDIR INFORMACIÓN DEL PEDIDO--------------!!

                    System.out.println(separador);

                    //Construir el calendario
                    calendario = Calendar.getInstance(); // Calendario

                    do {
                        System.out.print("Para que año quiere su domicilio?: ");
                        n_anno = scaner.nextInt();
                        System.out.print("Para que mes quiere su domicilio?: ");
                        n_mes = scaner.nextInt();
                        System.out.print("Para que dia quiere su domicilio?: ");
                        n_dia = scaner.nextInt();

                        dummy = verificarFecha(n_anno, n_mes, n_dia, true);
                        if (!dummy) System.out.println("\nFecha inválida!!, inténte de nuevo");
                        else calendario.set(n_anno, --n_mes, n_dia);

                    } while (!dummy);

                    String nombre_repartidor;
                    System.out.print("Nombre del repartidor: ");
                    nombre_repartidor = scaner.next();

                    //Verificar si el pedido ha sido pagado

                    //Verificar que no hayan dos pedidos con ese mismo producto para esa fecha y cliente
                    for (Pedido ped : pantalla.centralDespacho.getPedidos()) {
                        //Pedidos con el mismo cliente
                        if (ped.getSolicitante().getCedula().equals(cliente.getCedula())) {
                            //recorre  los pedidos y revisa si la cedula de ese pedido es la cedula del cliente que encontro anteriormente

                            //Si comparten el calendario
                            while (ped.getFechaRecibido().get(Calendar.DAY_OF_MONTH) == n_dia && ped.getFechaRecibido().get(Calendar.MONTH) == n_mes && ped.getFechaRecibido().get(Calendar.YEAR) == n_anno) {
                                //Nuevo calendario
                                System.out.println("Ya existe un pedido para esta fecha, escoga una nueva");
                                System.out.println(separador);

                                //Obtener una fecha con antelación
                                do {
                                    System.out.print("Para que año quiere su domicilio?: ");
                                    n_anno = scaner.nextInt();
                                    System.out.print("Para que mes quiere su domicilio?: ");
                                    n_mes = scaner.nextInt();
                                    System.out.print("Para que dia quiere su domicilio?: ");
                                    n_dia = scaner.nextInt();

                                    dummy = verificarFecha(n_anno, n_mes, n_dia, true);
                                    if (!dummy) System.out.println("\nFecha inválida!!, inténte de nuevo");
                                    else calendario.set(n_anno, --n_mes, n_dia);
                                } while (!dummy);
                            }
                        }
                    }

                    System.out.print("El pedido ya fue pagado? (s/n): ");
                    selec = scaner.next().charAt(0);

                    boolean pagado = false; // Pagado ?
                    while (selec != 'S' && selec != 's' && selec != 'N' && selec != 'n') {
                        System.out.println("Opción incorrecta...");
                        System.out.print("El pedido ya fue pagado? (s/n): ");
                        selec = scaner.next().charAt(0);
                    }

                    if (selec == 'S' || selec == 's') {
                        pagado = true;
                    } else {
                        System.out.println("El pedido no fue pagado!");
                    }

                    //Crear el pedido
                    Pedido pedido = new Pedido(calendario, pagado, nombre_repartidor, producto, cliente);

                    //Solicitar servicios adicionales
                    do {
                        System.out.println(separador);
                        System.out.print("Desea un nuevo servicio adicional? (s/n): ");
                        selec = scaner.next().charAt(0);

                        //Respuesta incorrecta
                        while (selec != 'S' && selec != 's' && selec != 'N' && selec != 'n') {
                            System.out.println("Opción incorrecta...");
                            System.out.print("Desea un nuevo servicio adicional? (s/n)");
                            selec = scaner.next().charAt(0);
                        }

                        if (selec == 'S' || selec == 's') {
                            System.out.print("Digite el código de servicio: ");
                            long codigo_S = scaner.nextLong();
                            System.out.print("Digite el nombre del servicio: ");
                            String nombre_S = scaner.next();
                            System.out.print("Digite el precio del servicio: ");
                            double precio_S = scaner.nextDouble();

                            //Agregar el servicio al pedido
                            System.out.print("Desea 1)Envio Prime o 2)Bono de Regalo: ");
                            short servicio = scaner.nextShort();
                            while (servicio < 1 || servicio > 2) {
                                System.out.print("opcion invalida! ");
                                System.out.print("Desea 1)Envio Prime o 2)Bono de Regalo: ");
                                servicio = scaner.nextShort();
                            }
                            if (servicio == 1) {
                                System.out.print("Digite el tipo de transporte deseado 1)Bicicleta, 2)Moto o 3)Minivan: ");
                                short transporte = scaner.nextShort();
                                while (transporte < 1 || transporte > 3) {
                                    System.out.print("opcion invalida! ");
                                    System.out.print("Digite el tipo de transporte deseado 1)Bicicleta, 2)Moto o 3)Minivan: ");
                                    transporte = scaner.nextShort();
                                }
                                System.out.print("Indique la distancia: ");
                                double distancia = scaner.nextDouble();
                                System.out.print("Indique el numero de cajas: ");
                                int nCajas = scaner.nextInt();

                                TipoTransporte tip;
                                if (transporte == 1) {
                                    System.out.println(
                                            pantalla.centralDespacho.agregarServicio(pedido, codigo_S, nombre_S, precio_S, distancia, TipoTransporte.BICICLETA, nCajas)
                                                    ? "Servicio Añadido correctamente" : "El servicio no fue añadido"
                                    );
                                }
                                if (transporte == 2) {
                                    System.out.println(
                                            pantalla.centralDespacho.agregarServicio(pedido, codigo_S, nombre_S, precio_S, distancia, TipoTransporte.MOTO, nCajas)
                                                    ? "Servicio Añadido correctamente" : "El servicio no fue añadido"
                                    );
                                }
                                if (transporte == 3) {
                                    System.out.println(
                                            pantalla.centralDespacho.agregarServicio(pedido, codigo_S, nombre_S, precio_S, distancia, TipoTransporte.MINIVAN, nCajas)
                                                    ? "Servicio Añadido correctamente" : "El servicio no fue añadido"
                                    );
                                }
                            }
                            if (servicio == 2) {
                                String comercioAsociado;
                                System.out.print("Ingrese el nombre del comercio: ");
                                comercioAsociado = scaner.next();

                                //Construir el calendario
                                calendario = Calendar.getInstance(); // Calendario

                                do {
                                    System.out.print("Para que año vence su tarjeta?: ");
                                    n_anno = scaner.nextInt();
                                    System.out.print("Para que mes vence su tarjeta?: ");
                                    n_mes = scaner.nextInt();
                                    System.out.print("Para que dia vence su tarjeta?: ");
                                    n_dia = scaner.nextInt();

                                    dummy = verificarFecha(n_anno, n_mes, n_dia, false);
                                    if (!dummy) System.out.println("\nFecha inválida!!, inténte de nuevo");
                                    else calendario.set(n_anno, --n_mes, n_dia);

                                } while (!dummy);

                                System.out.print("Digite su mensaje personalizado: ");
                                String mensaje = scaner.next();

                                System.out.println(
                                        pantalla.centralDespacho.agregarServicio(
                                                pedido, codigo_S, nombre_S, precio_S, comercioAsociado, mensaje, calendario
                                        ) ? "Servicio Añadido correctamente" : "El servicio no fue añadido"
                                );
                            }
                        } else {
                            System.out.println("No se agregarán más servicios...");
                        }
                    } while (selec != 'N' && selec != 'n');//do while porque es un arreglo
                    pantalla.centralDespacho.reservarPedido(pedido);
                    break;

                case 10: // Modificar un Pedido de un Producto (REPARANDO)
                    System.out.println(separador_largo);
                    System.out.print("Desea modificar un pedido? (s/n): "); //se modifica en control despacho
                    char opcion10 = scaner.next().charAt(0);

                    while (opcion10 != 'n' && opcion10 != 'N' && opcion10 != 's' && opcion10 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea modificar un pedido? (s/n): ");
                        opcion10 = scaner.next().charAt(0);
                    }

                    if (opcion10 == 's' || opcion10 == 'S') {
                        System.out.println("Digite el número del pedido que quiere modificar: ");
                        UUID numPedido = UUID.fromString(scaner.next());

                        int optionf = 0; //Variable para almacenar respuesta del usuario
                        char seleccion = 0; //Variable para almacenar respuesta del usuario

                        //Verificar que el pedido existe
                        Pedido pedi = pantalla.centralDespacho.buscarPedido(numPedido);
                        if (pedi != null) {
                            boolean fecha_modificada = false; //Flag para saber si algunas variables fueron modificadas
                            boolean nombre_modificado = false; //se usa a lo ultimo
                            boolean servicios_modificado = false;

                            //Variable para corregir el calendario
                            boolean val;
                            //Variables que van a ser modificadas
                            Calendar calendari = Calendar.getInstance();
                            String nuevo_nombre = "";

                            //Copiar arreglo
                            ArrayList<ServicioAdicional> services = new ArrayList<>(pedi.getServiciosAdicionales());

                            // --- MENÚ MODIFICAR PEDIDO --- //
                            do {
                                //---------------------MENÚ----------------------//
                                System.out.println(separador);
                                System.out.println("Qué desea modificar del pedido?");
                                System.out.println("1. Modificar fecha de entrega");
                                System.out.println("2. Modificar el nombre del repartidor");
                                System.out.println("3. Modificar los servicios adicionales");
                                System.out.println("4. Regresar...");
                                System.out.print("Seleccione una opción: ");
                                optionf = scaner.nextInt();

                                while (optionf < 1 || optionf > 4) {
                                    System.out.println("Opción incorrecta..");
                                    System.out.print("Seleccione una opción: ");
                                    optionf = scaner.nextInt();
                                }

                                switch (optionf) {
                                    case 1: //Cambiar la fecha *---------------------------------------------------------------*---------------- 1
                                        System.out.print("Desea cambiar la fecha? (s/n):  ");
                                        seleccion = scaner.next().charAt(0);

                                        while (seleccion != 'S' && seleccion != 's' && seleccion != 'n' && seleccion != 'N') {
                                            System.out.println("Opción incorrecta...");
                                            System.out.print("Desea cambiar la fecha? (s/n):  ");
                                            seleccion = scaner.next().charAt(0);
                                        }

                                        if (seleccion == 'S' || seleccion == 's') {
                                            int anio2, mes2, dia2;

                                            System.out.println(separador);
                                            do {
                                                System.out.print("Para que año quiere su domicilio?: ");
                                                anio2 = scaner.nextInt();
                                                System.out.print("Para que mes quiere su domicilio?: ");
                                                mes2 = scaner.nextInt();
                                                System.out.print("Para que dia quiere su domicilio?: ");
                                                dia2 = scaner.nextInt();

                                                val = verificarFecha(anio2, mes2, dia2, true);
                                                if (!val) System.out.println("\nFecha inválida!!, inténte de nuevo");
                                                else calendari.set(anio2, --mes2, dia2);

                                            } while (!val);

                                            //Verificar que no hayan dos pedidos con ese mismo producto para esa fecha y cliente
                                            for (Pedido ped : pantalla.centralDespacho.getPedidos()) {
                                                //Pedidos con el mismo cliente
                                                if (ped.getSolicitante().getCedula().equals(pedi.getSolicitante().getCedula())
                                                ) { //verifica si el solicitante esta en esos pedidos
                                                    //verifica si ese solicitante digito una fecha del cual ya se habia registrado  ese producto
                                                    while (
                                                            ped.getFechaRecibido().get(Calendar.DAY_OF_MONTH) == dia2 &&
                                                                    ped.getFechaRecibido().get(Calendar.MONTH) == mes2 &&
                                                                    ped.getFechaRecibido().get(Calendar.YEAR) == anio2
                                                    ) {
                                                        //Nuevo calendario
                                                        System.out.println(
                                                                "Ya existe un pedido para esta fecha, escoga una nueva"
                                                        );

                                                        System.out.println(separador);
                                                        do {
                                                            System.out.print("Para que año quiere su domicilio?: ");
                                                            anio2 = scaner.nextInt();
                                                            System.out.print("Para que mes quiere su domicilio?: ");
                                                            mes2 = scaner.nextInt();
                                                            System.out.print("Para que dia quiere su domicilio?: ");
                                                            dia2 = scaner.nextInt();

                                                            val = verificarFecha(anio2, mes2, dia2);
                                                            if (!val)
                                                                System.out.println("\nFecha inválida!!, inténte de nuevo");
                                                            else calendari.set(anio2, --mes2, dia2);

                                                        } while (!val);
                                                    }
                                                }
                                            }

                                            System.out.println("La fecha fue actualizada!");
                                            fecha_modificada = true;
                                        }
                                        break;
                                    case 2: //Cambiar nombre del repartidor *---------------------------------------------------------------*---- 2
                                        System.out.print("Desea cambiar el nombre del repartidor? (s/n):  ");
                                        selec = scaner.next().charAt(0);

                                        while (selec != 'S' && selec != 's' && selec != 'n' && selec != 'N') {
                                            System.out.println("Opción incorrecta...");
                                            System.out.print(
                                                    "Desea cambiar el nombre del repartidor? (s/n):  "
                                            );
                                            selec = scaner.next().charAt(0);
                                        }

                                        if (selec == 'S' || selec == 's') {
                                            System.out.println(
                                                    "Nombre del repartidor actualmente: " +
                                                            pedi.getNombreRepartidor()
                                            );
                                            System.out.print("Digite el nuevo nombre del repartidor: ");
                                            nuevo_nombre = scaner.next();

                                            System.out.println("Nuevo nombre actualizado con éxito!");
                                            nombre_modificado = true;
                                        }
                                        break;
                                    case 3: //Cambiar los servicios adicionales *---------------------------------------------------------------* 3
                                        System.out.print("Desea cambiar algún servicio? (s/n):  ");
                                        selec = scaner.next().charAt(0);

                                        while (selec != 'S' && selec != 's' && selec != 'n' && selec != 'N') {
                                            System.out.println("Opción incorrecta...");
                                            System.out.print("Desea cambiar algún servicio? (s/n):  ");
                                            selec = scaner.next().charAt(0);
                                        }

                                        long CodigoServicio = 0; //Codigo de servicio del servicio a modificar

                                        if (selec == 'S' || selec == 's') {
                                            System.out.print(
                                                    "Digite el código del servicio que desea modificar: "
                                            );
                                            CodigoServicio = scaner.nextLong();
                                        }

                                        //Buscar el servicio adicional
                                        for (ServicioAdicional servicio : services) {
                                            //Modificar variables del servicio
                                            if (servicio.getCodigoServicio() == CodigoServicio) { //busca el codigo del serviio
                                                // -------------- SUB MENU SERVICIOS --------------- //
                                                int sub_option = 0;
                                                do {
                                                    System.out.println(
                                                            "*---------------------------------------------------------------*--"
                                                    );
                                                    System.out.println("1. Modificar código de servicio");
                                                    System.out.println("2. Modificar el nombre del servicio");
                                                    System.out.println("3. Modificar el precio del servicio");
                                                    System.out.println("4. Cancelar y regresar...");
                                                    System.out.print("Seleccione una opción: ");
                                                    sub_option = scaner.nextInt();

                                                    while (sub_option < 1 || sub_option > 4) {
                                                        System.out.println("Opción inválida...");
                                                        System.out.print("Seleccione una opción: ");
                                                        sub_option = scaner.nextInt();
                                                    }

                                                    String nuevo_NombreServicio; //Nuevo nombre para el servicio
                                                    long nuevo_CodigoServicio; //Nuevo código para el servicio
                                                    double nuevo_PrecioServicio; //Nuevo precio para el servicio

                                                    switch (sub_option) {
                                                        case 1:
                                                            System.out.print("Digite el nuevo código de servicio: ");
                                                            nuevo_CodigoServicio = scaner.nextLong();
                                                            servicio.setCodigoServicio(nuevo_CodigoServicio);
                                                            System.out.println("Código actualizado con éxito...");
                                                            servicios_modificado = true;
                                                            break;
                                                        case 2:
                                                            System.out.print("Digite el nuevo nombre del servicio: ");
                                                            nuevo_NombreServicio = scaner.next();
                                                            servicio.setNombreServicio(nuevo_NombreServicio);
                                                            System.out.println("Nombre actualizado con éxito...");
                                                            servicios_modificado = true;
                                                            break;
                                                        case 3:
                                                            System.out.print("Digite el nuevo precio del servicio: ");
                                                            nuevo_PrecioServicio = scaner.nextDouble();
                                                            servicio.setPrecio(nuevo_PrecioServicio);
                                                            System.out.println("Precio actualizado con éxito...");
                                                            servicios_modificado = true;
                                                            break;
                                                        default:
                                                            System.out.println("Regresando...");
                                                            break;
                                                    }
                                                } while (sub_option != 4);
                                            }
                                        } //FIN DE LA BUSQUEDA DEL SERVICIO
                                        break;
                                    default:
                                        System.out.println("Regresando....");
                                        break;
                                }
                            } while (optionf != 4);

                            //Mostrar precio total
                            pedi.precioTotal();
                            char opc;
                            //Mostrar confirmación
                            System.out.print("Desea aplicar los cambios (s/n):  ");
                            opc = scaner.next().charAt(0);

                            while (opc != 'S' && opc != 's' && opc != 'N' && opc != 'n') {
                                System.out.println("Opción incorrecta...");
                                System.out.print("Desea aplicar los cambios (s/n):  ");
                                opc = scaner.next().charAt(0);
                            }

                            if (opc == 'S' || opc == 's') {
                                pantalla.centralDespacho.ModificarPedido(fecha_modificada, nombre_modificado, servicios_modificado, pedi, services, nuevo_nombre, calendari);
                            }                                              //bool               bool                   bool         obj   lista   nombreactualizado   calendario
                        }
                    } else break;

                    break;

                case 11: // Eliminar un Pedido de un Producto (REPARADO)
                    System.out.println(separador_largo);
                    System.out.print("Desea eliminar un pedido? (s/n): ");
                    char opcion11 = scaner.next().charAt(0);

                    while (opcion11 != 'n' && opcion11 != 'N' && opcion11 != 's' && opcion11 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea eliminar un pedido? (s/n): ");
                        opcion11 = scaner.next().charAt(0);
                    }

                    if (opcion11 == 's' || opcion11 == 'S') {
                        System.out.println("Digite el número del pedido que quiere modificar: ");
                        UUID numPedido = UUID.fromString(scaner.next());

                        System.out.print("Está seguro que desea retirar el elemento (s/n):  ");
                        selec = scaner.next().charAt(0);

                        while (selec != 'S' && selec != 's' && selec != 'N' && selec != 'n') {
                            System.out.println("Opción incorrecta...");
                            System.out.print("Está seguro que desea retirar el elemento (s/n):  ");
                            selec = scaner.next().charAt(0);
                        }

                        if (selec == 'S' || selec == 's') {
                            Pedido pedido_eliminado = pantalla.centralDespacho.eliminarPedido(numPedido);
                            if (pedido_eliminado != null) {
                                System.out.println("Pedido eliminado: ");
                                System.out.println(pedido_eliminado);
                                System.out.println("Fue eliminado con éxito!");
                            } else
                                System.out.println("El pedido no existe!");
                        }
                    } else break;
                    break;

                case 12: // Ver listado de Pedidos existentes (REPARADO)
                    System.out.println(separador_largo);
                    System.out.print("Desea ver la lista de los pedidos existentes? (s/n): ");
                    char opcion12 = scaner.next().charAt(0);

                    while (opcion12 != 'n' && opcion12 != 'N' && opcion12 != 's' && opcion12 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea ver la lista de los pedidos existentes? (s/n): ");
                        opcion12 = scaner.next().charAt(0);
                    }

                    if (opcion12 == 's' || opcion12 == 'S') {
                        System.out.println(pantalla.centralDespacho.verListadoPedidos());
                    } else break;
                    break;

                case 13: //Ver listado de Pedidos existentes para un Producto y posterior a una fecha específica (REPARADO)

                    System.out.println(separador_largo);
                    System.out.print("Desea ver la lista de los pedidos existentes para cierta fecha? (s/n): ");
                    char opcion13 = scaner.next().charAt(0);

                    while (opcion13 != 'n' && opcion13 != 'N' && opcion13 != 's' && opcion13 != 'S') {
                        System.out.println("Opción Invalida");
                        System.out.print("Desea ver la lista de los pedidos existentes para cierta fecha? (s/n): ");
                        opcion13 = scaner.next().charAt(0);
                    }

                    if (opcion13 == 's' || opcion13 == 'S') {
                        System.out.println("*---------------------------------------------------------------*--");

                        calendario = Calendar.getInstance();
                        do {
                            System.out.print("Para que año quiere su domicilio?: ");
                            n_anno = scaner.nextInt();
                            System.out.print("Para que mes quiere su domicilio?: ");
                            n_mes = scaner.nextInt();
                            System.out.print("Para que dia quiere su domicilio?: ");
                            n_dia = scaner.nextInt();

                            dummy = verificarFecha(n_anno, n_mes, n_dia);
                            if (!dummy) System.out.println("\nFecha inválida!!, inténte de nuevo");
                            else calendario.set(n_anno, --n_mes, n_dia);

                        } while (!dummy);

                        //Pedir el UUID del producto
                        System.out.print("Ingrese el ID del producto: ");
                        ID_P = scaner.next();

                        System.out.println(
                                pantalla.centralDespacho.verListadoPedidos_producto_fecha(UUID.fromString(ID_P), calendario));
                    } else break;
                    break;

                default: //Salir
                    System.out.println("Gracias por su atencion :D");
                    break;

            }

        } while (opcion0 != 14);
    }
}
