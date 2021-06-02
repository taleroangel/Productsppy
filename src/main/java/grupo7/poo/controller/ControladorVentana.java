package grupo7.poo.controller;

import grupo7.poo.entity.*;
import grupo7.poo.exceptions.EmptyFieldException;
import grupo7.poo.exceptions.NoInfoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControladorVentana implements Initializable {

    public static final String NOMBRE_GUARDAR = "Guardar la información";
    public static final String FXML_GUARDAR = "../saveView.fxml";

    // Variable que contiene todos los datos a manejar, Mapas de clientes y productos y listas con pedidos
    private ArchivoDatos datosAplicacion = new ArchivoDatos();
    private ControlDespacho controlDespacho = new ControlDespacho(datosAplicacion);

    //Método para actualizar pantalla

    @FXML
    private Button botonGuardar;

    @FXML
    private Button guardarbtn;

    @FXML
    private Button lecturabtn;

    @FXML
    private Button reportepedidobtn;

    @FXML
    private Button reportesproductobtn;

    @FXML
    private Text mostrarnombreUsuario;

    @FXML
    private TextField mostrarTelefonoUsuario;

    @FXML
    private TextField mostrarDireccionUsuario;

    @FXML
    private Button eliminarClientebtn;

    @FXML
    private CheckBox checkmodificarbtn;

    @FXML
    private Button modificarClientebtn;

    @FXML
    private TextField mostrarIdentificacion;

    @FXML
    private TextField crearnombreUsuario;

    @FXML
    private TextField crearIdUsuario;

    @FXML
    private TextField crearTelefonoUsuario;

    @FXML
    private TextField crearDireccionUsuario;

    @FXML
    private Button agregarUsuario;

    @FXML
    private TableView<Cliente> tablaUsuariosRegistrados;

    @FXML
    private TableColumn<Cliente, String> nombreUsuariosRegistrados = new TableColumn<Cliente, String>("NombreCompleto");

    @FXML
    private TableColumn<Cliente, Long> cedulaUsuariosRegistrados = new TableColumn<Cliente, Long>("Cedula");

    @FXML
    private TextField nombreEmpresaCamp;

    @FXML
    private RadioButton opcionSIA;

    @FXML
    private ToggleGroup grupoinvima;

    @FXML
    private RadioButton opcionNoA;

    @FXML
    private ChoiceBox<TipoProducto> tipoProductoBox1 = new ChoiceBox<>();

    @FXML
    private TextField nombreHaciendaCamp;

    @FXML
    private TextField impuestoLocalCamp;

    @FXML
    private RadioButton opcionSif;

    @FXML
    private ToggleGroup grupoOrganico;

    @FXML
    private RadioButton opcionNof;

    @FXML
    private TextField nombreComercialCampo;

    @FXML
    private TextField nombreTiendaCampo;

    @FXML
    private Spinner<Double> precioCampo;

    @FXML
    private RadioButton tipoAseo;

    @FXML
    private ToggleGroup grupoTipo;

    @FXML
    private RadioButton tipoFruver;

    @FXML
    private Button agregarProductobnt;

    @FXML
    private Button modificarProductobtn;

    @FXML
    private TableView<?> productosRegistados;

    @FXML
    private TableColumn<?, ?> listaIdProducto;

    @FXML
    private TableColumn<?, ?> listaNombreProductos;

    @FXML
    private TableColumn<?, ?> listaPrecioProductos;

    @FXML
    private Button eliminarProductobtn;

    @FXML
    private Button verListadoporSeparadobtn;

    @FXML
    private Text precioTotalProductoTexto;

    @FXML
    private Text ivaProductoTexto;

    @FXML
    private Text precioMasIVATotal;

    @FXML
    private ChoiceBox<?> boxIDcliente;

    @FXML
    private TextField nombreRepartidorCampo;

    @FXML
    private DatePicker seleccionarFechaPedido;

    @FXML
    private ChoiceBox<?> boxIDProducto;

    @FXML
    private RadioButton sip1;

    @FXML
    private ToggleGroup grupoServiciosAdicionales;

    @FXML
    private RadioButton nop1;

    @FXML
    private Button bonoRegalobtn;

    @FXML
    private Button envioPrimeBtn;

    @FXML
    private RadioButton sipag1;

    @FXML
    private ToggleGroup grupoPagado;

    @FXML
    private RadioButton nopag2;

    @FXML
    private Button reservar;

    @FXML
    private TableView<?> pedidosRegistrados;

    @FXML
    private TableColumn<?, ?> listaIDpedidos;

    @FXML
    private TableColumn<?, ?> listaProductosPedidos;

    @FXML
    private TableColumn<?, ?> listaClientesPedidos;

    @FXML
    private TableColumn<?, ?> listaRepartidorPedidos;

    @FXML
    private TableColumn<?, ?> listaFechaPedidos;

    @FXML
    private TableColumn<?, ?> listaPrecioPedidos;

    @FXML
    private Button verListado1btn;

    @FXML
    private Button verListado2btn;

    @FXML
    private ChoiceBox<?> idPedidobox;

    @FXML
    private Button eliminarPedido;

    @FXML
    private Button modificarPedido;

    GestionCliente controlCliente = new GestionCliente();
    ControlDespacho gestionDespacho=new ControlDespacho();
    /* -------------------------------------------------- Métodos --------------------------------------------------- */
    public void initData(ArchivoDatos datos) {
        this.datosAplicacion = datos;
    }

    @FXML
    void agregarProducto(ActionEvent event) {
        Producto producto;

        try {
            // Campos obligatorios
            if (nombreComercialCampo.getText().isEmpty() || nombreTiendaCampo.getText().isEmpty())
                throw new EmptyFieldException();

            

        } catch (EmptyFieldException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setTitle("Algunos campos quedaron sin completar!");
            return;
        }
    }

    @FXML
    void agregarUsuario(ActionEvent event) {

      /*  try {
            Cliente usuario = new Cliente(Long.parseLong(crearIdUsuario.getText()),crearnombreUsuario.getText(), Long.parseLong(crearnombreUsuario.getText()), crearDireccionUsuario.getText());
            controlCliente.insertarCliente(usuario);

        } /*catch () {                      //por si ingresa un ID igual


        }*/

        // renderWindow();

    }

    @FXML
    void mostrarCliente(ActionEvent event) {

        Long cedula = tablaUsuariosRegistrados.getSelectionModel().getSelectedItem().getCedula();//guarda la cedula del cliente que selecciono en la tabla

        Cliente clienteAbuscar = controlCliente.buscarCliente(cedula);//busca la cedula del cliente

        mostrarnombreUsuario.setText(clienteAbuscar.getNombreCompleto());//muestra en los campos los datos
        mostrarIdentificacion.setText(String.valueOf(clienteAbuscar.getCedula()));
        mostrarDireccionUsuario.setText(clienteAbuscar.getDireccion());
        mostrarTelefonoUsuario.setText(String.valueOf(clienteAbuscar.getTelefonoContacto()));

    }

    @FXML
    void eliminarCliente(ActionEvent event) {    //no se puede eliminar un cliente asociado a un pedido
        //no se puede eliminar un cliente que no existe
        //pondre un aviso
        Long cedulaClienteAeliminar = Long.parseLong(mostrarIdentificacion.getText());

        if (gestionDespacho.clienteEnPedido(cedulaClienteAeliminar) == null) {
            controlCliente.eliminarCliente(cedulaClienteAeliminar);

        }
        controlCliente.eliminarCliente(cedulaClienteAeliminar);

        renderWindow();
//--------------------------------------------------------------------------------------------------------------------------------------
    /*    System.out.print("Desea eliminar a (nombre del  cliente)? (s/n): ");

        if (opcion8 == 's' || opcion8 == 'S') {



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
*/


    }

    @FXML
    void eliminarPedido(ActionEvent event) {

    }

    @FXML
    void eliminarProducto(ActionEvent event) {

    }

    @FXML
    void guardarArchivo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../saveView.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle(NOMBRE_GUARDAR);

            ControladorGuardar controller = loader.getController();

            if (datosAplicacion == null)
                throw new NoInfoException();

            controller.initData(datosAplicacion);

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();

        } catch (NoInfoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error en la clase: ControladorVentana, datosAplicacion no se ha inicializado");
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            e.printStackTrace();
        }
    }

    @FXML
        ///??? de donde sale esta acción
    void guardarBoton(ActionEvent event) {

    }

    @FXML
    void leerArchivo(ActionEvent event) {

    }

    @FXML
    void modificarCliente(ActionEvent event) {

    }

    @FXML
    void modificarPedido(ActionEvent event) {

    }

    @FXML
    void modificarProducto(ActionEvent event) {

    }

    @FXML
    void verListadoPedidos(ActionEvent event) {

    }

    @FXML
    void verListadoSeparado(ActionEvent event) {

    }

    @FXML
    void verReportePedidos(ActionEvent event) {

    }

    @FXML
    void verReporteProducto(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoProductoBox1.getItems().addAll(
                TipoProducto.HOGAR, TipoProducto.INDUSTRIAL, TipoProducto.HOSPITALARIO
        );
        tipoProductoBox1.setValue(TipoProducto.HOGAR);

        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 1000.0);
        this.precioCampo.setValueFactory(valueFactory);
    }


    public void renderWindow() {  //pinta la ventana de las columnas
        cleanWindow();

        this.tablaUsuariosRegistrados.getItems().addAll(controlCliente.getListaClientes().values());//lista de usuarios registrados


    }

    public void cleanWindow() {
        tablaUsuariosRegistrados.getItems().clear();

    }


}
