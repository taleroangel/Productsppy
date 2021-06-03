package grupo7.poo.controller;

import grupo7.poo.entity.EmergentWindow;
import grupo7.poo.exceptions.NoInfoException;
import grupo7.poo.servicioAdicional.BonoRegalo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;


public class ControladorBonoRegalo
        implements EmergentWindow {

    @FXML
    private TextField precioBonoCampo;

    @FXML
    private TextField mensajeBonoCampo;

    @FXML
    private DatePicker vencimientoBonoFecha;

    @FXML
    private Button anteriorBonobtn;

    @FXML
    private Button agregarBonobtn;

    @FXML
    private TextField comercioBonoCampo;

    @FXML
    private TextField nombreServicioCampo;

    @FXML
    private TextField codigoBonoCampo;

    private BonoRegalo datos;

    //Inicializador de primera instancia
    public void initData(BonoRegalo data) {
        try {
            if (data == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }
        datos = data;
    }

    //Métodos

    @FXML
    void agregarBono(ActionEvent event) {
        try {
            datos.setCodigoServicio(
                    Long.parseLong(codigoBonoCampo.getText()));
            datos.setNombreServicio(nombreServicioCampo.getText());
            datos.setPrecio(
                    Double.parseDouble(precioBonoCampo.getText()));
            datos.setComercioAsociado(comercioBonoCampo.getText());
            datos.setMensaje(mensajeBonoCampo.getText());

            Date date = Date.from(vencimientoBonoFecha.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            datos.setFechaVencimiento(calendar);

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error!");
            alert.setHeaderText("Algunos campos no tienen el formato correcto");
            alert.setContentText("Digitaste texto en un campo numérico!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operación finalizada");
        alert.setHeaderText("Nuevo bono insertado con éxito!");
        alert.setContentText("Regresa a la pestaña anterior");
        alert.showAndWait();
        this.regresar(event);
    }

    @FXML
    void regresar(ActionEvent event) {
        closeWindow(event);
    }
}
