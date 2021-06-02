package grupo7.poo.boundary;

import grupo7.poo.controller.ControlDespacho;
import grupo7.poo.controller.ControladorGuardar;
import grupo7.poo.entity.*;
import grupo7.poo.servicioAdicional.BonoRegalo;
import grupo7.poo.servicioAdicional.ServicioAdicional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.UUID;

public class TestArchivos extends Application {

    //Attributes
    private final static String ICON_NAME = "icons/save.png";
    private final static String MAIN_FXML_NAME = "saveView.fxml";
    private final static String STYLE_SHEET_NAME = "style.css";
    private final static String WINDOW_NAME = "Guardar un archivo";

    public static ArchivoDatos infoPrueba() {
        ArchivoDatos datos = new ArchivoDatos();
        ControlDespacho centralDespacho = new ControlDespacho(datos);

        //Agregar productos 3
        centralDespacho.getGestionProductos().insertarProducto(
                new Producto("Shampoo antipulgas para perro", 25900, "Supermercado superéxito")
        );
        centralDespacho.getGestionProductos().insertarProducto(
                new Producto("Chocolates para regalar", 15000, "Dulces sueños")
        );
        centralDespacho.getGestionProductos().insertarProducto(
                new Producto("Jabón para lavar ropa", 1900, "Surtivecino")
        );
        //Agregar productor fruver 3
        Fruver producto4 = new Fruver("canasta de verdurass", 44000, "surtideli", false, 8567, "hacienda san jose");
        Fruver producto5 = new Fruver("fresas 100 frescas", 34000, "surtimax", true, 4678, "hacienda san silvestre");
        Fruver producto6 = new Fruver("fertilizantes", 34000, "plantas y decoraciones", false, 6432, "hacienda de las margaritas");
        Fruver producto7 = new Fruver("canasta de frutas", 32000, "fruteria patty", true, 3432, "hacienda napoles");


        centralDespacho.getGestionProductos().insertarProducto(producto4);
        centralDespacho.getGestionProductos().insertarProducto(producto5);
        centralDespacho.getGestionProductos().insertarProducto(producto6);
        centralDespacho.getGestionProductos().insertarProducto(producto7);

        // Agregar productos Aseo
        Aseo producto8 = new Aseo("spray anti manchas", 34567, "limpia ya!", "home center", true, TipoProducto.HOGAR);
        Aseo producto9 = new Aseo("trapos anti polvo", 98765, "aseo markplace", "poliquimicos", true, TipoProducto.HOGAR);
        Aseo producto10 = new Aseo("Recursos industriales", 12345, "super clean!", "Aseo Yem Sas", true, TipoProducto.INDUSTRIAL);
        Aseo producto11 = new Aseo("kit de limpieza de hospitales", 65437, "burbujas", "MAX center", true, TipoProducto.HOSPITALARIO);

        centralDespacho.getGestionProductos().insertarProducto(producto8);
        centralDespacho.getGestionProductos().insertarProducto(producto9);
        centralDespacho.getGestionProductos().insertarProducto(producto10);
        centralDespacho.getGestionProductos().insertarProducto(producto11);


        //Agregar clientes 5
        Cliente cliente1 = new Cliente(52221850L, "Carlos Alberto Martínez", 755749, "Kr 7ma #58-36 Sur");
        centralDespacho.getGestionCliente().insertarCliente(cliente1);
        Cliente cliente2 = new Cliente(75851566L, "Andrea Juliana Moreno", 30538956, "Calle 94 #89-85 Casa 89");
        centralDespacho.getGestionCliente().insertarCliente(cliente2);
        Cliente cliente3 = new Cliente(55847986L, "Julián Antonio Zamora", 5222185, "Cra. 8 sur #89-89B");
        centralDespacho.getGestionCliente().insertarCliente(cliente3);
        Cliente cliente4 = new Cliente(25839852L, "Nelson Rodriguez Fernandez", 5678543, "Cra. 34a sur #45-14B");
        centralDespacho.getGestionCliente().insertarCliente(cliente4);
        Cliente cliente5 = new Cliente(15889848L, " heyling Burgos Algarin", 53241857, "Cra. 25 sur #13-65A");
        centralDespacho.getGestionCliente().insertarCliente(cliente5);

        centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.APRIL, 29),
                true, "Pedro", producto4, cliente1));

        centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.APRIL, 30),
                true, "Johan", producto5, cliente2));

        centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.MAY, 9),
                true, "Pedro", producto8, cliente3));

        centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.MAY, 15),
                true, "Carlos", producto6, cliente4));

        centralDespacho.reservarPedido(new Pedido(new GregorianCalendar(2021, Calendar.MAY, 20),
                true, "Cristian", producto5, cliente5));

        ServicioAdicional servicio1 = new BonoRegalo(1234L, "Bono spotify", 25000, "Tiendas Éxito", "Sin mensaje", Calendar.getInstance());

        System.out.println("La información de pruebas fue insertada correctamente");

        return datos;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource(MAIN_FXML_NAME))));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLE_SHEET_NAME)).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_NAME))));
        stage.setTitle(WINDOW_NAME);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);

        try {
            ArchivoDatos datos = infoPrueba();

            ControladorGuardar controller = loader.getController();
            controller.initData(datos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.show();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado!");
            alert.setHeaderText("Sucedió un error mientras se cargaba la interfaz!");
            alert.setContentText("Comunícate con el desarrollador: el error ocurrió en la clase TestArchivos");
        }
    }
}
