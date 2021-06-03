package grupo7.poo.controller;

import grupo7.poo.entity.EmergentWindow;
import grupo7.poo.entity.Pedido;
import grupo7.poo.entity.TipoServicio;
import grupo7.poo.entity.TipoTransporte;
import grupo7.poo.exceptions.NoInfoException;
import grupo7.poo.servicioAdicional.BonoRegalo;
import grupo7.poo.servicioAdicional.EnvioPrime;
import grupo7.poo.servicioAdicional.ServicioAdicional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class ControladorServicios implements Initializable, EmergentWindow {

    @FXML
    private ComboBox<Long> listaCodigoAmodificar;

    @FXML
    private Button eliminarcodigoAdicionalbtn;

    @FXML
    private Button regresarmodbtn;

    @FXML
    private TextField nuevoNombreComercio;

    @FXML
    private TextField nuevomensaje;

    @FXML
    private Button modificarBonobtn;

    @FXML
    private DatePicker nuevaFechaDeVencimiento;

    @FXML
    private TextField nuevocodigocamp;

    @FXML
    private TextField nuevoServicioCamp;

    @FXML
    private TextField nuevoPrecioCamp;

    @FXML       //String parte de TipoServicio
    private ChoiceBox<TipoServicio> nuevoTipoDeServicio;

    @FXML
    private TextField nuevaDistancia;

    @FXML
    private Spinner<Integer> nuevoNumerodeCajas;

    @FXML
    private ComboBox<TipoTransporte> nuevoTipoDeTransporte;

    @FXML
    private Button modificarEnvioPrime;

    @FXML
    private Button selectBtnServicio;

    private Pedido modificar;

    @FXML
    void eliminarServicio(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Borrar un servicio?");
            alert.setHeaderText("Quiere eliminar este servicio?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.CANCEL)
                return;

            modificar.getServiciosAdicionales().remove(
                    modificar.buscarServicioPorId(listaCodigoAmodificar.getValue())
            );
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No rellenaste algún campo!");
            alert.showAndWait();
        }

        render();
    }

    @FXML
    void modificarBonoRegalo(ActionEvent event) {
        try {
            BonoRegalo servicio = (BonoRegalo) modificar.buscarServicioPorId(listaCodigoAmodificar.getValue());
            servicio.setCodigoServicio(Long.parseLong(nuevocodigocamp.getText()));
            servicio.setNombreServicio(nuevoServicioCamp.getText());
            servicio.setPrecio(Double.parseDouble(nuevoPrecioCamp.getText()));

            servicio.setComercioAsociado(nuevoNombreComercio.getText());
            servicio.setMensaje(nuevomensaje.getText());

            Date date = Date.from(nuevaFechaDeVencimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            servicio.setFechaVencimiento(calendar);

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No rellenaste algún campo!");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No puedes colocar letras en campos numéricos!");
            alert.showAndWait();
        } catch (ClassCastException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No puedes cambiar el tipo de ServicioAdicional");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("Error interno de la aplicación");
            alert.setContentText("Contacta a los desarrolladores de la aplicación");
            alert.showAndWait();
            e.printStackTrace();
        }
        render();
    }

    @FXML
    void modificarEnvioPrime(ActionEvent event) {
        try {
            EnvioPrime servicio = (EnvioPrime) modificar.buscarServicioPorId(listaCodigoAmodificar.getValue());
            servicio.setCodigoServicio(Long.parseLong(nuevocodigocamp.getText()));
            servicio.setNombreServicio(nuevoServicioCamp.getText());
            servicio.setPrecio(Double.parseDouble(nuevoPrecioCamp.getText()));

            servicio.setDistancia(Double.parseDouble(nuevaDistancia.getText()));
            servicio.setNumeroCajas(nuevoNumerodeCajas.getValue());
            servicio.setTipo(nuevoTipoDeTransporte.getValue());

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No rellenaste algún campo!");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No puedes colocar letras en campos numéricos!");
            alert.showAndWait();
        } catch (ClassCastException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No puedes cambiar el tipo de ServicioAdicional");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("Error interno de la aplicación");
            alert.setContentText("Contacta a los desarrolladores de la aplicación");
            alert.showAndWait();
            e.printStackTrace();
        }
        render();
    }

    @FXML
    void seleccionarServicio(ActionEvent event) {
        try {
            ServicioAdicional servicio = modificar.buscarServicioPorId(listaCodigoAmodificar.getValue());
            nuevocodigocamp.setText(Long.toString(servicio.getCodigoServicio()));
            nuevoServicioCamp.setText(servicio.getNombreServicio());
            nuevoPrecioCamp.setText(Double.toString(servicio.getPrecio()));

            if (servicio instanceof BonoRegalo) {
                nuevoTipoDeServicio.setValue(TipoServicio.BONOREGALO);
                nuevoNombreComercio.setText(((BonoRegalo) servicio).comercioAsociado);
                nuevomensaje.setText(((BonoRegalo) servicio).getMensaje());
                Date date = ((BonoRegalo) servicio).getFechaVencimiento().getTime();
                LocalDate new_date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                nuevaFechaDeVencimiento.setValue(new_date);
            } else if (servicio instanceof EnvioPrime) {
                nuevoTipoDeServicio.setValue(TipoServicio.ENVIOPRIME);
                nuevaDistancia.setText(((EnvioPrime) servicio).getDistancia().toString());
                nuevoNumerodeCajas.getValueFactory().setValue(((EnvioPrime) servicio).getNumeroCajas());
                nuevoTipoDeTransporte.setValue(((EnvioPrime) servicio).getTipo());
            }

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ocurrió un error!");
            alert.setHeaderText("No rellenaste algún campo!");
            alert.showAndWait();
            render();
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        closeWindow(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> factory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        nuevoNumerodeCajas.setValueFactory(factory);
        nuevoTipoDeServicio.setDisable(true);
    }

    public void initData(Pedido pedido) {
        try {
            if (pedido == null)
                throw new NoInfoException(this.getClass().getCanonicalName(), true);
        } catch (NoInfoException e) {
            e.printCause();
        }

        modificar = pedido;
        render();
    }

    public void clean() {
        //Borrar choice boxes
        listaCodigoAmodificar.getItems().clear();
        nuevoTipoDeTransporte.getItems().clear();
        nuevoTipoDeTransporte.getItems().clear();

        //Borrar inputs
        nuevoNombreComercio.clear();
        nuevomensaje.clear();
        nuevocodigocamp.clear();
        nuevoServicioCamp.clear();
        nuevoPrecioCamp.clear();
        nuevaDistancia.clear();

        nuevoNumerodeCajas.getEditor().clear();
        nuevaFechaDeVencimiento.getEditor().clear();
    }

    public void render() {
        clean();
        for (ServicioAdicional s : modificar.getServiciosAdicionales())
            listaCodigoAmodificar.getItems().add(s.getCodigoServicio());

        nuevoTipoDeServicio.getItems().addAll(TipoServicio.values());
        nuevoTipoDeTransporte.getItems().addAll(TipoTransporte.values());
    }
}
