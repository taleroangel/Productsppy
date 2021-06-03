package grupo7.poo.controller;

import grupo7.poo.entity.Cliente;
import grupo7.poo.entity.EmergentWindow;
import grupo7.poo.entity.Pedido;
import grupo7.poo.exceptions.NoInfoException;
import grupo7.poo.servicioAdicional.ServicioAdicional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class ControlPersonaPedidos implements Initializable, EmergentWindow {

    @FXML
    private ChoiceBox<Long> cedulaPersonaBox;

    @FXML
    private Button volverBtn;

    @FXML
    private TableView<ServicioAdicional> tablaServiciosAdicionales;

    @FXML
    private TableColumn<ServicioAdicional, Long> listaCodigoServicio = new TableColumn<ServicioAdicional, Long>("CodigoServicio");

    @FXML
    private TableColumn<ServicioAdicional, String> listaTipoServicio = new TableColumn<ServicioAdicional, String>("WhatType");

    @FXML
    private TableColumn<ServicioAdicional, String> listaServicio = new TableColumn<ServicioAdicional, String>("NombreServicio");

    @FXML
    private TableColumn<ServicioAdicional, Double> listaPrecioServicio = new TableColumn<>("Precio");

    @FXML
    private TableView<Pedido> tablaPedidosPersona;

    @FXML
    private TableColumn<Pedido, UUID> listaIdPedidos = new TableColumn<Pedido, UUID>("NumPedido");

    @FXML
    private TableColumn<Pedido, String> listaIdProductos = new TableColumn<Pedido, String>("ProductoNombre");

    @FXML
    private TableColumn<Pedido, Boolean> listaPagado = new TableColumn<Pedido, Boolean>("Pagado");

    // Archivos con los datos de las personas
    private ControlDespacho datos;

    @FXML
    void actualizarListas(ActionEvent event) {
        try {
            if (cedulaPersonaBox.getSelectionModel().isEmpty())
                throw new NoInfoException();

            Cliente cliente = datos.getGestionCliente().buscarCliente(
                    cedulaPersonaBox.getSelectionModel().getSelectedItem()
            );

            clearFields();
            tablaPedidosPersona.getItems().addAll(
                    datos.getPedidosCliente(cliente)
            );

        } catch (NoInfoException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setHeaderText("No seleccionaste ningún cliente!");
            alert.showAndWait();
        }
    }

    @FXML
    void updateAdditionalServices(MouseEvent event) {
        Pedido pedido = tablaPedidosPersona.getSelectionModel().getSelectedItem();
        if (pedido == null) return;

        if (pedido.getServiciosAdicionales().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notificación");
            alert.setHeaderText("Este pedido no tiene servicios adicionales!");
            alert.showAndWait();
        }

        tablaServiciosAdicionales.getItems().clear();
        tablaServiciosAdicionales.getItems().addAll(
                pedido.getServiciosAdicionales()
        );
    }

    @FXML
    void regresar(ActionEvent event) {
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cedulaPersonaBox.getItems().clear();
        clearFields();
    }

    public void clearFields() {
        tablaPedidosPersona.getItems().clear();
        tablaServiciosAdicionales.getItems().clear();
    }

    public void initData(ControlDespacho datos) {
        try {
            if (datos == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }

        this.datos = datos;

        for (Cliente c : datos.getGestionCliente().getListaClientes().values())
            cedulaPersonaBox.getItems().add(c.getCedula());
    }
}
