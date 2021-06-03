package grupo7.poo.controller;

import grupo7.poo.entity.EmergentWindow;
import grupo7.poo.entity.Pedido;
import grupo7.poo.entity.Producto;
import grupo7.poo.entity.TipoTransporte;
import grupo7.poo.exceptions.NoInfoException;
import grupo7.poo.servicioAdicional.EnvioPrime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class ControladorFechas implements EmergentWindow {

    @FXML
    private DatePicker vistaPedidosFecha;

    @FXML
    private ChoiceBox<UUID> idProductoDiaBox;

    @FXML
    private ChoiceBox<TipoTransporte> tipoTransporteBox;

    @FXML
    private Button volverBtn;

    @FXML
    private ChoiceBox<UUID> idPedidoBox;

    @FXML
    private Button verPedidosBtn;

    @FXML
    private TableView<EnvioPrime> tablaServiciosAdicionales;

    @FXML
    private TableColumn<EnvioPrime, Long> listaCodigoServicios = new TableColumn<>("CodigoServicio");

    @FXML
    private TableColumn<EnvioPrime, String> listaNombreServicios = new TableColumn<>("NombreServicio");

    @FXML
    private TableColumn<EnvioPrime, Double> listaDistanciaServicios = new TableColumn<>("Distancia");

    @FXML
    private TableColumn<EnvioPrime, Integer> listaCajasSevicios = new TableColumn<>("NumeroCajas");

    @FXML
    private TableColumn<EnvioPrime, Double> listaPrecioServicios = new TableColumn<>("Precio");

    @FXML
    private TableView<Pedido> tablaPedidosDia;

    @FXML
    private TableColumn<Pedido, UUID> listaIdDia = new TableColumn<>("NumPedido");

    @FXML
    private TableColumn<Pedido, String> listaProductosDia = new TableColumn<>("ProductoNombre");

    @FXML
    private TableColumn<Pedido, String> listaSolicitanteDia = new TableColumn<>("ClienteNombre");

    @FXML
    private TableColumn<Pedido, String> listaDireccionDia = new TableColumn<>("DireccionSolicitante");

    @FXML
    private TableColumn<Pedido, String> listaRepartidorDia = new TableColumn<>("NombreRepartidor");

    @FXML
    private TableColumn<Pedido, Calendar> listaFecha = new TableColumn<>("FechaRecibidoString");

    @FXML
    private Button showButton;

    private ControlDespacho datos;

    @FXML
    void regresar(ActionEvent event) {
        closeWindow(event);
    }

    @FXML
    void updateList(ActionEvent event) {
        try {
            Date date = Date.from(vistaPedidosFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            if (idProductoDiaBox.getSelectionModel().getSelectedItem() == null)
                throw new NullPointerException();

            tablaPedidosDia.getItems().clear();
            tablaPedidosDia.getItems().addAll(
                    datos.verListadoPedidos_producto_fecha(
                            idProductoDiaBox.getSelectionModel().getSelectedItem(),
                            calendar
                    ));

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error");
            alert.setHeaderText("Campos inválidos!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void updateSecondList(ActionEvent event) {

        try {

            tablaServiciosAdicionales.getItems().clear();
            tablaServiciosAdicionales.getItems().addAll(
                    datos.serviciosTransporte(tipoTransporteBox.getSelectionModel().getSelectedItem())
            );
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error");
            alert.setHeaderText("Campos inválidos!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void initData(ControlDespacho data) {
        try {
            if (data == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }

        this.datos = data;

        idProductoDiaBox.getItems().clear();
        for (Producto p : datos.getGestionProductos().getListaProductos().values())
            idProductoDiaBox.getItems().add(p.getProdId());

        tipoTransporteBox.getItems().clear();
        tipoTransporteBox.getItems().addAll(TipoTransporte.values());

        idPedidoBox.getItems().clear();
        for (Producto p : datos.getGestionProductos().getListaProductos().values())
            idPedidoBox.getItems().add(p.getProdId());

    }

}
