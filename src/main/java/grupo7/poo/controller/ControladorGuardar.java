package grupo7.poo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControladorArchivos {

    /* ----------------------------------------------- FXML Controls ------------------------------------------------ */

    //Toggle group
    @FXML
    private ToggleGroup selectGroup;
    @FXML
    private RadioButton xmlSel;
    @FXML
    private RadioButton jsonSel;

    //Botones
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;

    @FXML
    void closeFrame(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void saveFile(ActionEvent event) {
        // Entrar en bucle mientras termina el procedimiento

        boolean procedureDone = false;
        //Ningún botón
        if (selectGroup.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Un error ha ocurrido...");
            alert.setHeaderText("Ninguna opción fue seleccionada!");
            alert.setContentText("Porfavor selecciona una opción");
            alert.showAndWait();
        }

        //Boton de XML
        else if (selectGroup.getSelectedToggle().equals(xmlSel)) {
            procedureDone = true;
        }

        //Botón de JSON
        else if (selectGroup.getSelectedToggle().equals(jsonSel)) {
            procedureDone = true;
        }

        // Cerrar la ventana luego de terminar el procedimiento
        if (procedureDone)
            closeFrame(event);
    }

    /* -------------------------------------------------- Methods --------------------------------------------------- */

    public ControlDespacho saveToXml() {
        return true;
    }

    public boolean saveToJson() {
        return false;
    }
}
