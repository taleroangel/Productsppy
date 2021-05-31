package grupo7.poo.boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class VentanaDespacho extends Application {

    //Attributes
    private final static String ICON_NAME = "icon.png";
    private final static String MAIN_FXML_NAME = "mainView.fxml";
    private final static String STYLE_SHEET_NAME = "style.css";
    private final static String WINDOW_NAME = "Gestión de productos - Grupo 7";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader((Objects.requireNonNull(getClass().getResource(MAIN_FXML_NAME))));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(STYLE_SHEET_NAME)).toExternalForm());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream(ICON_NAME))));
        stage.setTitle(WINDOW_NAME);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo fxml!");
        }
    }
}
