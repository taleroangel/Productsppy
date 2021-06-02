package grupo7.poo.controller;

import grupo7.poo.entity.EmergentWindow;
import grupo7.poo.entity.TipoTransporte;
import grupo7.poo.exceptions.NoInfoException;
import grupo7.poo.servicioAdicional.EnvioPrime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladorEnvioPrime implements Initializable, EmergentWindow {

    @FXML
    private Button anteriorPrimeBtn;

    @FXML
    private Button agregarPrimeBtn;

    @FXML
    private TextField distanciaPrimeCampo;

    @FXML
    private Spinner<Integer> cajasPrimeBox;

    @FXML
    private RadioButton bicicletaBtn;

    @FXML
    private ToggleGroup tipoTransporte;

    @FXML
    private RadioButton minivanBtn;

    @FXML
    private RadioButton motoBtn;

    @FXML
    private TextField nombrePrimeCampo;

    @FXML
    private TextField codigoPrimeCampo;

    @FXML
    private TextField precioPrimeCampo;

    private EnvioPrime datos;

    @FXML
    void agregarEnvio(ActionEvent event) {
        try {
            datos.setCodigoServicio(
                    Long.parseLong(codigoPrimeCampo.getText()));

            datos.setNombreServicio(nombrePrimeCampo.getText());

            datos.setPrecio(
                    Double.parseDouble(precioPrimeCampo.getText()));

            datos.setDistancia(
                    Double.parseDouble(distanciaPrimeCampo.getText())
            );

            datos.setNumeroCajas(
                    cajasPrimeBox.getValue()
            );

            if (((RadioButton) tipoTransporte.getSelectedToggle()).equals(bicicletaBtn)) {
                datos.setTipo(TipoTransporte.BICICLETA);
            } else if (((RadioButton) tipoTransporte.getSelectedToggle()).equals(minivanBtn)) {
                datos.setTipo(TipoTransporte.MINIVAN);
            } else if (((RadioButton) tipoTransporte.getSelectedToggle()).equals(motoBtn)) {
                datos.setTipo(TipoTransporte.MOTO);
            }
            //Servicios prime
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
        alert.setHeaderText("Nuevo servicio insertado con éxito!");
        alert.setContentText("Regresa a la pestaña anterior");
        alert.showAndWait();
        this.regresar(event);
    }

    //Inicializador de primera instancia
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 1);
        cajasPrimeBox.setValueFactory(factory);
    }

    //Inicializador de segunda instancia
    public void initData(EnvioPrime data) {
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
    void regresar(ActionEvent event) {
        closeWindow(event);
    }
}

