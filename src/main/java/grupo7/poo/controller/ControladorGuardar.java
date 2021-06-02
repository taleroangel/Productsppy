package grupo7.poo.controller;

import grupo7.poo.entity.ArchivoDatos;
import grupo7.poo.entity.FileHandler;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorGuardar implements Initializable, FileHandler {

    @FXML
    private ArchivoDatos internalData;

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
            saveToXml(this.internalData, DEFAULT_FILE_NAME + ".xml");
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

    public void initData(ArchivoDatos archivo) {
        this.internalData = archivo;
    }

    public static void saveToXml(ArchivoDatos archivos, String ruta) {

        //Exportar la clase de archivos
        try (FileWriter fw = new FileWriter(ruta)) {
            JAXBContext context = JAXBContext.newInstance(archivos.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(archivos, fw);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error interno inesperado!");
            alert.setHeaderText("No se pudo escribir el archivo XML");
            alert.setContentText("Revisa si el sistema de archivo está en sólo lectura");
            alert.showAndWait();
            e.printStackTrace();
            return;

        } catch (JAXBException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error interno inesperado!");
            alert.setHeaderText("Error interno, no se encontraron datos para escribir");
            alert.setContentText("Comunícate con los desarrolladores, si lo necesitas aquí está el error detallado:\n" +
                                         "Error en JAXB, archivos.getClass() no existe esa clase o Marshaller no pudo continuar");
            alert.showAndWait();
            e.printStackTrace();
            return;

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error interno inesperado!");
            alert.setHeaderText("Error interno, causa desconocida");
            alert.setContentText("Comunícate con los desarrolladores: stackTrace was printed!");
            alert.showAndWait();
            e.printStackTrace();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operación finalizó");
        alert.setHeaderText("El archivo fue escrito correctamente!");
        alert.setContentText("El archivo se llama: " + ruta + ", puedes buscarlo en la carpeta del programa!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
