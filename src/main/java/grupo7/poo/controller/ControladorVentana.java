package grupo7.poo.controller;

import grupo7.poo.entity.*;
import grupo7.poo.exceptions.EmptyFieldException;
import grupo7.poo.exceptions.InvalidCalendarException;
import grupo7.poo.exceptions.NoInfoException;
import grupo7.poo.exceptions.NotValidFileException;
import grupo7.poo.servicioAdicional.BonoRegalo;
import grupo7.poo.servicioAdicional.EnvioPrime;
import grupo7.poo.servicioAdicional.ServicioAdicional;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.URL;
import java.time.ZoneId;
import java.util.*;

public class ControladorVentana implements Initializable {

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
    private Button agregarUsuarioBtn;

    //Tablas de usuarios registrados

    @FXML
    private TableView<Cliente> tablaUsuariosRegistrados = new TableView<Cliente>();

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

    // Tablas con los productos

    @FXML
    private TableView<Producto> productosRegistados = new TableView<Producto>();

    @FXML
    private TableColumn<Producto, UUID> listaIdProducto = new TableColumn<Producto, UUID>("ProdId");

    @FXML
    private TableColumn<Producto, String> listaNombreProductos = new TableColumn<Producto, String>("NombreComercial");

    @FXML
    private TableColumn<Producto, Double> listaPrecioProductos = new TableColumn<Producto, Double>("Precio");

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
    private ChoiceBox<Long> boxIDcliente;

    @FXML
    private TextField nombreRepartidorCampo;

    @FXML
    private DatePicker seleccionarFechaPedido;

    @FXML
    private ChoiceBox<UUID> boxIDProducto;

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

    //  Tabla con los pedidos
    @FXML
    private TableView<Pedido> pedidosRegistrados;

    @FXML
    private TableColumn<Pedido, UUID> listaIDpedidos = new TableColumn<Pedido, UUID>("NumPedido");

    @FXML
    private TableColumn<Pedido, String> listaProductosPedidos = new TableColumn<Pedido, String>("ProductoNombre");

    @FXML
    private TableColumn<Pedido, String> listaClientesPedidos = new TableColumn<Pedido, String>("ClienteNombre");

    @FXML
    private TableColumn<Pedido, String> listaRepartidorPedidos = new TableColumn<Pedido, String>("NombreRepartidor");

    @FXML
    private TableColumn<Pedido, String> listaFechaPedidos = new TableColumn<Pedido, String>("FechaRecibidoString");

    @FXML
    private TableColumn<Pedido, Double> listaPrecioPedidos = new TableColumn<Pedido, Double>("PrecioTotal");

    //
    @FXML
    private Button verListado1btn;

    @FXML
    private Button verListado2btn;

    @FXML
    private ChoiceBox<UUID> idPedidobox;

    @FXML
    private Button eliminarPedidoBtn;

    @FXML
    private Button modificarPedido;

    @FXML
    private Button helpButton;

    /* --------------------------------------------- Variables privadas --------------------------------------------- */
    private ArchivoDatos datosAplicacion;
    private ControlDespacho gestionDespacho;
    private ArrayList<ServicioAdicional> servicioNuevo;

    /* -------------------------------------------------- Métodos --------------------------------------------------- */

    //Inicializador de primera instancia
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoProductoBox1.getItems().addAll(
                TipoProducto.HOGAR, TipoProducto.INDUSTRIAL, TipoProducto.HOSPITALARIO
        );
        tipoProductoBox1.setValue(TipoProducto.HOGAR);

        SpinnerValueFactory<Double> valueFactory =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 1000.0);
        this.precioCampo.setValueFactory(valueFactory);

        modificarClientebtn.setDisable(true);
        bonoRegalobtn.setDisable(true);
        envioPrimeBtn.setDisable(true);
    }

    //Inicializador de segunda instancia (Debe ser llamado explícitamente)
    public void initData(ArchivoDatos datos) {

        try {
            if (datos == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }

        this.datosAplicacion = datos;
        gestionDespacho = new ControlDespacho(datos);

        //  Render tabs
        this.renderClienteTab();
        this.renderProductoTab();
        this.renderPedidosTab();
    }

    public void initData(ControlDespacho datos) {

        try {
            if (datos == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }

        gestionDespacho = datos;

        //  Render tabs
        this.renderClienteTab();
        this.renderProductoTab();
        this.renderPedidosTab();
    }

    /* ---------------------------------------------- Métodos públicos ---------------------------------------------- */

    @FXML
    void agregarProducto(ActionEvent event) {

        Producto producto;
        try {
            // Campos obligatorios
            if (nombreComercialCampo.getText().isEmpty() || nombreTiendaCampo.getText().isEmpty())
                throw new EmptyFieldException();

            if (((RadioButton) grupoTipo.getSelectedToggle()).getText().equals("Aseo")) {
                if (nombreEmpresaCamp.getText().isEmpty() || tipoProductoBox1.getValue() == null)
                    throw new EmptyFieldException();

                producto = new Aseo(
                        nombreComercialCampo.getText(),
                        precioCampo.getValue(),
                        nombreTiendaCampo.getText(),
                        nombreEmpresaCamp.getText(),
                        ((RadioButton) grupoinvima.getSelectedToggle()).getText().equals("Sí"),
                        tipoProductoBox1.getValue()
                );
            } else {
                if (nombreHaciendaCamp.getText().isEmpty() ||
                            impuestoLocalCamp.getText().isEmpty())
                    throw new EmptyFieldException();

                producto = new Fruver(
                        nombreComercialCampo.getText(),
                        precioCampo.getValue(),
                        nombreTiendaCampo.getText(),
                        ((RadioButton) grupoOrganico.getSelectedToggle()).getText().equals("Sí"),
                        Double.parseDouble(impuestoLocalCamp.getText()),
                        nombreHaciendaCamp.getText()
                );
            }

        } catch (EmptyFieldException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setHeaderText("Algunos campos quedaron sin completar!");

            alert.showAndWait();
            return;

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setHeaderText("Algunos campos no tienen el formato correcto");
            alert.setContentText("Digitaste texto en un campo numérico!");

            alert.showAndWait();
            return;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setHeaderText("Ocurrió un error interno");
            alert.setContentText("Contacta a los desarrolladores de la apliación");
            e.printStackTrace();

            alert.showAndWait();
            return;
        }

        gestionDespacho.getGestionProductos().insertarProducto(producto);
        renderProductoTab();
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        GestionCliente controlCliente = gestionDespacho.getGestionCliente();

        try {
            Cliente usuario = new Cliente(
                    Long.parseLong(crearIdUsuario.getText()),
                    crearnombreUsuario.getText(),
                    Long.parseLong(crearTelefonoUsuario.getText()),
                    crearDireccionUsuario.getText());

            controlCliente.insertarCliente(usuario);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);//ALERTA,LLAMADA CONFIRMATION
            alert.setTitle("Exito");//TITULO EXITO
            alert.setHeaderText("El cliente ha sido registrado");//LA CUENTA SE AGREGO CORRECTAMENTE
            alert.setContentText("En el sistema hay " + controlCliente.getListaClientes().size() + " clientes");//NUEVO TAMAÑO
            alert.showAndWait();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);//ALERTA,LLAMADA CONFIRMATION
            alert.setTitle("Error en la entrada de datos");//TITULO EXITO
            alert.setHeaderText("Unicamente digita números en los campos numéricos");//LA CUENTA SE AGREGO CORRECTAMENTE
            alert.setContentText("Inténtalo de nuevo");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);//ALERTA,LLAMADA CONFIRMATION
            alert.setTitle("Error");//TITULO EXITO
            alert.setHeaderText("El cliente no pudo ser registrado!");//LA CUENTA SE AGREGO CORRECTAMENTE
            alert.setContentText("Contácta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();
        }

        cleanClienteTab();
        renderClienteTab();
    }

    @FXML
    void mostrarCliente(MouseEvent event) {
        GestionCliente controlCliente = gestionDespacho.getGestionCliente();
        Long cedula = tablaUsuariosRegistrados.getSelectionModel().getSelectedItem().getCedula();//guarda la cedula del cliente que selecciono en la tabla
        Cliente clienteAbuscar = controlCliente.buscarCliente(cedula);//busca la cedula del cliente
        mostrarnombreUsuario.setText(clienteAbuscar.getNombreCompleto());//muestra en los campos los datos
        mostrarIdentificacion.setText(String.valueOf(clienteAbuscar.getCedula()));
        mostrarDireccionUsuario.setText(clienteAbuscar.getDireccion());
        mostrarTelefonoUsuario.setText(String.valueOf(clienteAbuscar.getTelefonoContacto()));
    }

    @FXML
    void eliminarCliente(ActionEvent event) {
        GestionCliente controlCliente = gestionDespacho.getGestionCliente();
        Long cedula = tablaUsuariosRegistrados.getSelectionModel().getSelectedItem().getCedula();

        // Verificar asociación a un pedido
        if (gestionDespacho.clienteEnPedido(cedula) != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error");
            alert.setHeaderText("No puedes eliminar a este cliente");
            alert.setContentText("No puedes eliminar un cliente que esté asociado a un pedido");
            alert.showAndWait();
            return;
        }

        //Mostrar una confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Está seguro que desea eliminar el Cliente con la cédula: " + cedula.toString() + "?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            controlCliente.eliminarCliente(cedula);
            tablaUsuariosRegistrados.getItems().removeAll(
                    tablaUsuariosRegistrados.getSelectionModel().getSelectedItem()
            );
        }
    }

    @FXML
    void enableModify(ActionEvent event) {
        modificarClientebtn.setDisable(!checkmodificarbtn.isSelected());
    }

    @FXML
    void modificarCliente(ActionEvent event) {
        try {
            GestionCliente controlCliente = gestionDespacho.getGestionCliente();
            Long cedula = tablaUsuariosRegistrados.getSelectionModel().getSelectedItem().getCedula();

            //Mostrar una confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Está seguro que desea modificar el Cliente ?");
            alert.showAndWait();

            Cliente cliente = controlCliente.buscarCliente(cedula);

            if (alert.getResult() == ButtonType.OK) {
                cliente.setCedula(Long.parseLong(mostrarIdentificacion.getText()));
                cliente.setTelefonoContacto(Long.parseLong(mostrarTelefonoUsuario.getText()));
                cliente.setDireccion(mostrarDireccionUsuario.getText());
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);//ALERTA,LLAMADA CONFIRMATION
            alert.setTitle("Error en la entrada de datos");//TITULO EXITO
            alert.setHeaderText("Unicamente digita números en los campos numéricos");//LA CUENTA SE AGREGO CORRECTAMENTE
            alert.setContentText("Inténtalo de nuevo");
            alert.showAndWait();
        }

        renderClienteTab();
    }

    @FXML
    void selectProductList(MouseEvent event) {
        Producto producto = gestionDespacho.getGestionProductos().buscarProducto(
                productosRegistados.getSelectionModel().getSelectedItem().getProdId()
        );
        nombreComercialCampo.setText(producto.getNombreComercial());
        nombreTiendaCampo.setText(producto.getTienda());
        precioCampo.getValueFactory().setValue(producto.getPrecio());

        if (producto instanceof Aseo) {
            nombreEmpresaCamp.setText(((Aseo) producto).getNombreEmpresa());
            if (((Aseo) producto).isTieneInvima()) {
                opcionSIA.setSelected(true);
                opcionNoA.setSelected(false);
            } else {
                opcionSIA.setSelected(false);
                opcionNoA.setSelected(true);
            }
            tipoProductoBox1.setValue(((Aseo) producto).getTipo());

        } else if (producto instanceof Fruver) {
            nombreHaciendaCamp.setText(((Fruver) producto).getNombreHacienda());
            impuestoLocalCamp.setText(String.valueOf(((Fruver) producto).getImpuestoLocal()));
            if (((Fruver) producto).isOrganico()) {
                opcionSif.setSelected(true);
                opcionNof.setSelected(false);
            } else {
                opcionSif.setSelected(false);
                opcionNof.setSelected(true);
            }

        }

        precioTotalProductoTexto.setText("Precio Producto: " + producto.getPrecio());
        precioMasIVATotal.setText("Precio Total: " + producto.calcularPrecio());
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        //Mostrar una confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Está seguro que desea eliminar el Producto ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            gestionDespacho.getGestionProductos().eliminarProducto(
                    productosRegistados.getSelectionModel().getSelectedItem().getProdId()
            );
            productosRegistados.getItems().removeAll(
                    productosRegistados.getSelectionModel().getSelectedItem()
            );
        }
    }

    @FXML
    void guardarArchivo(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialFileName("archivosDespacho");
            fc.setTitle("Guardar un archivo...");
            fc.getExtensionFilters().addAll(
                    (new FileChooser.ExtensionFilter(
                            "JavaScript Object Notation",
                            "*.json")),
                    (new FileChooser.ExtensionFilter(
                            "Extensive Markup Language",
                            "*.xml")));

            File selectedFile = fc.showSaveDialog(null);
            if (selectedFile == null)
                throw new NotValidFileException();

            System.out.println("INFO: Leyendo datos del archivo: " + selectedFile.getAbsolutePath());
            if (FilenameUtils.getExtension(selectedFile.toString()).equals("xml")) {
                System.out.println("INFO: Identificado archivo XML");
                ControladorGuardar.saveToXml(this.datosAplicacion, selectedFile.getAbsolutePath());

            } else if (FilenameUtils.getExtension(selectedFile.toString()).equals("json")) {
                System.out.println("INFO: Identificado archivo JSON");
                ControladorGuardar.saveToJson(this.datosAplicacion, selectedFile.getAbsolutePath());
            } else {
                throw new NotValidFileException();
            }

        } catch (NotValidFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al identificar el archivo");
            alert.setHeaderText("Asegurate de seleccionar un nombre apropiado!");
            alert.setContentText("El archivo tiene que estar en formato .xml o .json");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void leerArchivo(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Abrir un archivo...");
            fc.getExtensionFilters().addAll(
                    (new FileChooser.ExtensionFilter(
                            "JavaScript Object Notation",
                            "*.json")),
                    (new FileChooser.ExtensionFilter(
                            "Extensive Markup Language",
                            "*.xml")));

            File selectedFile = fc.showOpenDialog(null);
            if (selectedFile == null)
                throw new NotValidFileException();

            System.out.println("INFO: Leyendo datos del archivo: " + selectedFile.getAbsolutePath());

            if (FilenameUtils.getExtension(selectedFile.toString()).equals("xml")) {
                System.out.println("INFO: Identificado archivo XML");
                this.datosAplicacion = ControladorGuardar.getFromXml(selectedFile.getAbsolutePath());

            } else if (FilenameUtils.getExtension(selectedFile.toString()).equals("json")) {
                System.out.println("INFO: Identificado archivo JSON");
                this.datosAplicacion = ControladorGuardar.getFromJson(selectedFile.getAbsolutePath());
            } else {
                throw new NotValidFileException();
            }

            gestionDespacho = new ControlDespacho(this.datosAplicacion);

        } catch (NotValidFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not a valid file");
            alert.setHeaderText("El archivo seleccionado no fue válido");
            alert.setContentText("Porfavor selecciona otro archivo!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void modificarPedidoSelect(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../fxmlAdicionales/modificar1.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle("Modificar un producto");

            ControladorServicios controller = loader.getController();

            if (idPedidobox.getSelectionModel().getSelectedItem() == null)
                throw new NoInfoException();

            controller.initData(
                    gestionDespacho.buscarPedido(
                            idPedidobox.getSelectionModel().getSelectedItem()
                    )
            );

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();

        } catch (NullPointerException | NoInfoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error");
            alert.setHeaderText("No has seleccionado ningún pedido");
            alert.setContentText("Por favor selecciona un pedido");
            alert.showAndWait();
            e.printStackTrace();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void modificarProducto(ActionEvent event) {
        try {
            Producto producto = gestionDespacho.getGestionProductos().buscarProducto(
                    productosRegistados.getSelectionModel().getSelectedItem().getProdId()
            );

            if (producto == null)
                throw new NullPointerException();

            //Mostrar una confirmación
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Está seguro que desea modificar el Producto ?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                producto.setNombreComercial(nombreComercialCampo.getText());
                producto.setTienda(nombreTiendaCampo.getText());
                producto.setPrecio(precioCampo.getValue());

                if (producto instanceof Aseo) {
                    ((Aseo) producto).setNombreEmpresa(nombreEmpresaCamp.getText());
                    ((Aseo) producto).setTieneInvima((
                            (RadioButton) grupoinvima.getSelectedToggle()).getText().equals("Sí"));
                    ((Aseo) producto).setTipo(
                            tipoProductoBox1.getValue()
                    );
                } else if (producto instanceof Fruver) {
                    ((Fruver) producto).setNombreHacienda(nombreHaciendaCamp.getText());
                    ((Fruver) producto).setImpuestoLocal(
                            Double.parseDouble(impuestoLocalCamp.getText()));
                    ((Fruver) producto).setEsOrganico(
                            ((RadioButton) grupoOrganico.getSelectedToggle()).getText().equals("Sí")
                    );
                }
            }

        } catch (NullPointerException e) {
            //En teoría nunca debería haber null pointer exception pero está por si acaso
            e.printStackTrace();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);//ALERTA,LLAMADA CONFIRMATION
            alert.setTitle("Error en la entrada de datos");//TITULO EXITO
            alert.setHeaderText("Unicamente digita números en los campos numéricos");//LA CUENTA SE AGREGO CORRECTAMENTE
            alert.setContentText("Inténtalo de nuevo");
            alert.showAndWait();
        }

        renderProductoTab();
    }

    @FXML
    void verListadoPedidos(ActionEvent event) {

    }

    @FXML
    void verListadoSeparado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../fxmlAdicionales/listadoPorSeparado.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle("Listado de productos detallado");

            ControladorListaSeparado controller = loader.getController();

            if (gestionDespacho == null)
                throw new NoInfoException();

            controller.initData(gestionDespacho);

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
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    @FXML
    void verReportePedidos(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialFileName("reportePedidos");
            fc.setTitle("Guardar un archivo...");
            fc.getExtensionFilters().addAll(
                    (new FileChooser.ExtensionFilter(
                            "Extensive Markup Language",
                            "*.xml")),
                    (new FileChooser.ExtensionFilter(
                            "JavaScript Object Notation",
                            "*.json")));

            File selectedFile = fc.showSaveDialog(null);
            if (selectedFile == null)
                throw new NotValidFileException();

            System.out.println("INFO: Leyendo datos del archivo: " + selectedFile.getAbsolutePath());
            if (FilenameUtils.getExtension(selectedFile.toString()).equals("xml")) {
                System.out.println("INFO: Identificado archivo XML");
                ControladorGuardar.saveToXml(
                        new ListXmlExporter(gestionDespacho.getListaPedidos()), selectedFile.getAbsolutePath());

            } else if (FilenameUtils.getExtension(selectedFile.toString()).equals("json")) {
                System.out.println("INFO: Identificado archivo JSON");
                ControladorGuardar.saveToJson((gestionDespacho.getListaPedidos()), selectedFile.getAbsolutePath());

            } else {
                throw new NotValidFileException();
            }

        } catch (NotValidFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al identificar el archivo");
            alert.setHeaderText("Asegurate de seleccionar un nombre apropiado!");
            alert.setContentText("El archivo tiene que estar en formato .xml o .json");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void verReporteProducto(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialFileName("reporteProductos");
            fc.setTitle("Guardar un archivo...");
            fc.getExtensionFilters().addAll(
                    (new FileChooser.ExtensionFilter(
                            "Extensive Markup Language",
                            "*.xml")),
                    (new FileChooser.ExtensionFilter(
                            "JavaScript Object Notation",
                            "*.json")));

            File selectedFile = fc.showSaveDialog(null);
            if (selectedFile == null)
                throw new NotValidFileException();

            System.out.println("INFO: Leyendo datos del archivo: " + selectedFile.getAbsolutePath());
            if (FilenameUtils.getExtension(selectedFile.toString()).equals("xml")) {
                System.out.println("INFO: Identificado archivo XML");
                ControladorGuardar.saveToXml(
                        new MapXmlExporter(this.gestionDespacho.getGestionProductos().getListaProductos()),
                        selectedFile.getAbsolutePath());

            } else if (FilenameUtils.getExtension(selectedFile.toString()).equals("json")) {
                System.out.println("INFO: Identificado archivo JSON");
                ControladorGuardar.saveToJson(this.gestionDespacho.getGestionProductos().getListaProductos(),
                        selectedFile.getAbsolutePath());
            } else {
                throw new NotValidFileException();
            }

        } catch (NotValidFileException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al identificar el archivo");
            alert.setHeaderText("Asegurate de seleccionar un nombre apropiado!");
            alert.setContentText("El archivo tiene que estar en formato .xml o .json");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    /* ----------------------------------------------- Tab de pedidos ----------------------------------------------- */

    @FXML
    void updateServiciosBtns(ActionEvent event) {
        if (((RadioButton) grupoServiciosAdicionales.getSelectedToggle()).equals(sip1)) {
            bonoRegalobtn.setDisable(false);
            envioPrimeBtn.setDisable(false);
            servicioNuevo = new ArrayList<>();
        } else {
            bonoRegalobtn.setDisable(true);
            envioPrimeBtn.setDisable(true);
        }
    }

    @FXML
    void abrirBonosRegalo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../fxmlAdicionales/bonoRegalo.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle("Añadir un servicio adicional");

            ControladorBonoRegalo controller = loader.getController();

            ServicioAdicional bonoNuevo = new BonoRegalo();
            controller.initData((BonoRegalo) bonoNuevo);
            servicioNuevo.add(bonoNuevo);

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    @FXML
    void abrirEnvioPrime(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../fxmlAdicionales/envioPrime.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle("Añadir un servicio adicional");

            ControladorEnvioPrime controller = loader.getController();

            ServicioAdicional envioNuevo = new EnvioPrime();
            controller.initData((EnvioPrime) envioNuevo);
            servicioNuevo.add(envioNuevo);

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    @FXML
    void reservarPedido(ActionEvent event) {
        try {

            if (boxIDcliente.getSelectionModel().isEmpty() ||
                        boxIDProducto.getSelectionModel().isEmpty() ||
                        nombreRepartidorCampo.getText().isEmpty() ||
                        seleccionarFechaPedido.getValue() == null
            ) {
                throw new NoInfoException();
            }

            Cliente cliente = gestionDespacho.getGestionCliente().buscarCliente(
                    boxIDcliente.getValue()
            );

            Producto producto = gestionDespacho.getGestionProductos().buscarProducto(
                    boxIDProducto.getValue()
            );

            Date date = Date.from(seleccionarFechaPedido.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            if (!ControlDespacho.verificarFecha(calendar))
                throw new InvalidCalendarException();

            Pedido pedido = new Pedido(
                    calendar,
                    grupoPagado.getSelectedToggle().equals(sipag1),
                    nombreRepartidorCampo.getText(),
                    producto,
                    cliente
            );

            if (servicioNuevo != null)
                pedido.setServiciosAdicionales(new ArrayList<>(servicioNuevo));
            gestionDespacho.reservarPedido(pedido);

        } catch (NoInfoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error");
            alert.setHeaderText("No completaste todos los campos de datos!");
            alert.setHeaderText("Por favor completa todos los campos");
            alert.showAndWait();
            return;

        } catch (InvalidCalendarException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error");
            alert.setHeaderText("La fecha establecida no cumple los requisitos!");
            alert.setContentText("Recuerda que los pedidos deben realizarse con al menos "
                                         + ControlDespacho.ANTICIPACION + " días de anticipación");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operación finalizada");
        alert.setHeaderText("Se ha insertado el nuevo pedido con éxito!");
        alert.showAndWait();

        renderPedidosTab();
    }

    @FXML
    void eliminarPedido(ActionEvent event) {
        try {
            if (idPedidobox.getSelectionModel().isEmpty())
                throw new EmptyFieldException();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar un pedido");
            alert.setHeaderText("Estás seguro que deseas eliminar este pedido?");
            alert.setContentText("Esta acción es irreversible, " +
                                         "te recomendamos guardar un archivo con una copia de seguridad antes de proceder");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.CANCEL)
                return;

            gestionDespacho.eliminarPedido(
                    idPedidobox.getSelectionModel().getSelectedItem()
            );

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Eliminar un pedido");
            alert.setHeaderText("El pedido fue eliminado");
            alert.showAndWait();

            renderPedidosTab();

        } catch (EmptyFieldException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error");
            alert.setHeaderText("No has seleccionado ningún pedido!");
            alert.setContentText("Por favor selecciona un pedido del menú");
            alert.showAndWait();
        }
    }

    @FXML
    void verListadoProductoPersona(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../fxmlAdicionales/pedidosegunPersona.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle("Añadir un servicio adicional");

            ControlPersonaPedidos controller = loader.getController();
            controller.initData(gestionDespacho);

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    @FXML
    void listadoFechas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource("../fxmlAdicionales/listaFecha.fxml"))));
            Parent root = (Parent) loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../style.css")).toExternalForm());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("../icon.png"))));
            stage.setTitle("Ver fechas");

            ControladorFechas controller = loader.getController();
            controller.initData(gestionDespacho);

            stage.setScene(scene);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error fatal inesperado");
            alert.setHeaderText("Ocurrió un error inesperado");
            alert.setContentText("Contácta a los desarrolladores, aquí hay más información por si lo necesitas:\n" +
                                         "Error al cargar archivos de la nueva ventana");
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    @FXML
    void showCredits(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de...");
        alert.setHeaderText("Productsppy: Gestión de despacho de productos\n\n" +
                                    "Proyecto de Programación Orientada a Objetos 2021\n" +
                                    "Pontificia Universidad Javeriana, Bogotá D.C - Colombia\n" +
                                    "Carrera de Ingeniería de Sistemas");
        alert.setContentText("Desarrolladores:\n" +
                                     "Ángel David Talero Peñuela\n" +
                                     "Karol Geraldine Ceballos Castro\n" +
                                     "Sebastián Camilo Hernando Murcia Sierra\n" +
                                     "Valentina Rozo Gonzalez\n\n" +
                                     "Profesor: Julian Camilo Daza Rodriguez\n");
        alert.showAndWait();
    }

    /* ---------------------------------------------- Renders y Cleans ---------------------------------------------- */

    @FXML
    void requestRender(Event event) {
        renderClienteTab();
        renderProductoTab();
        renderPedidosTab();
    }

    public void renderClienteTab() {  //pinta la ventana de las columnas
        cleanClienteTab();
        this.tablaUsuariosRegistrados.getItems().addAll(gestionDespacho.getGestionCliente().getListaClientes().values());
        mostrarnombreUsuario.setText("Nombre del Usuario");
    }

    public void cleanClienteTab() {
        //Outputs
        tablaUsuariosRegistrados.getItems().clear();
        mostrarnombreUsuario.setText(" ");
        mostrarTelefonoUsuario.setText(" ");
        mostrarDireccionUsuario.setText(" ");
        mostrarIdentificacion.setText(" ");

        //Inputs
        crearnombreUsuario.clear();
        crearIdUsuario.clear();
        crearTelefonoUsuario.clear();
        crearDireccionUsuario.clear();
        checkmodificarbtn.setSelected(false);
        modificarClientebtn.setDisable(true);
    }

    public void renderProductoTab() {
        cleanProductoTab();
        productosRegistados.getItems().addAll(gestionDespacho.getGestionProductos().getListaProductos().values());
        ivaProductoTexto.setText("IVA: " + Producto.VALOR_IVA + "%");
    }

    public void cleanProductoTab() {
        productosRegistados.getItems().clear();
        nombreEmpresaCamp.clear();
        nombreHaciendaCamp.clear();
        impuestoLocalCamp.clear();
        nombreComercialCampo.clear();
        nombreTiendaCampo.clear();
        precioCampo.getValueFactory().setValue(1000.0);
    }

    public void cleanPedidosTab() {
        pedidosRegistrados.getItems().clear();
        idPedidobox.getItems().clear();
        boxIDcliente.getItems().clear();
        boxIDProducto.getItems().clear();
        nombreRepartidorCampo.clear();
        seleccionarFechaPedido.getEditor().clear();
        grupoServiciosAdicionales.selectToggle(nop1);
        grupoPagado.selectToggle(sipag1);
        updateServiciosBtns(new ActionEvent());
    }

    public void renderPedidosTab() {
        cleanPedidosTab();
        pedidosRegistrados.getItems().addAll(gestionDespacho.getListaPedidos());

        for (Pedido p : gestionDespacho.getListaPedidos())
            idPedidobox.getItems().add(p.getNumPedido());

        for (Cliente c : gestionDespacho.getGestionCliente().getListaClientes().values())
            boxIDcliente.getItems().add(c.getCedula());

        for (Producto p : gestionDespacho.getGestionProductos().getListaProductos().values()) {
            boxIDProducto.getItems().add(p.getProdId());
        }
    }
}
