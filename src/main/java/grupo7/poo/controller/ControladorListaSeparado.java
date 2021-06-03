package grupo7.poo.controller;

import grupo7.poo.entity.*;
import grupo7.poo.exceptions.NoInfoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.UUID;

public class ControladorListaSeparado implements EmergentWindow {

    @FXML
    private TableView<Aseo> tablaAseo;

    @FXML
    private TableColumn<Aseo, UUID> listaIdAseo = new TableColumn<Aseo, UUID>("ProdId");

    @FXML
    private TableColumn<Aseo, String> listaNombreAseo = new TableColumn<Aseo, String>("NombreComercial");

    @FXML
    private TableColumn<Aseo, Double> listaPrecioAseo = new TableColumn<Aseo, Double>("Precio");

    @FXML
    private TableColumn<Aseo, String> listaTiendaAseo = new TableColumn<Aseo, String>("Tienda");

    @FXML
    private TableColumn<Aseo, String> listaEmpresaAseo = new TableColumn<Aseo, String>("NombreEmpresa");

    @FXML
    private TableColumn<Aseo, TipoProducto> listaTipoAseo = new TableColumn<Aseo, TipoProducto>("Tipo");

    @FXML
    private TableView<Fruver> tablaFruver;

    @FXML
    private TableColumn<Fruver, UUID> listaIdFruver = new TableColumn<Fruver, UUID>("ProdId");

    @FXML
    private TableColumn<Fruver, String> listaNombreFruver = new TableColumn<Fruver, String>("NombreComercial");

    @FXML
    private TableColumn<Fruver, String> listaTiendaFruver = new TableColumn<Fruver, String>("Tienda");

    @FXML
    private TableColumn<Fruver, String> listaHaciendaFruver = new TableColumn<Fruver, String>("NombreHacienda");

    @FXML
    private TableColumn<Fruver, Double> listaImpuestoFruver = new TableColumn<Fruver, Double>("ImpuestoLocal");

    @FXML
    private TableColumn<Fruver, Double> listaPrecioFruver = new TableColumn<Fruver, Double>("Precio");

    @FXML
    private TableColumn<Fruver, Boolean> listaOrganicoFruver = new TableColumn<Fruver, Boolean>("Organico");

    @FXML
    private Button volverTablasBtn;

    /* --------------------------------------------- Variables privadas --------------------------------------------- */
    private ControlDespacho control;

    /* ---------------------------------------------- Inicializadores ----------------------------------------------- */

    //Inicializador de segunda instancia, tiene que llamarse explícitamente
    public void initData(ControlDespacho datos) {
        try {
            if (datos == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }
        control = datos;

        tablaAseo.getItems().clear();
        tablaFruver.getItems().clear();

        tablaAseo.getItems().clear();
        for (Producto a : control.productosTipoAseo())
            tablaAseo.getItems().add((Aseo) a);

        tablaFruver.getItems().clear();
        for (Producto a : control.productosTipoFruver())
            tablaFruver.getItems().add((Fruver) a);
    }

    /* --------------------------------------------------- Métodos -------------------------------------------------- */

    @FXML
    void regresar(ActionEvent event) {
        closeWindow(event);
    }
}
