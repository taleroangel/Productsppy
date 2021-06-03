package grupo7.poo.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import grupo7.poo.entity.ArchivoDatos;
import javafx.scene.control.Alert;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ControladorGuardar {

    /* -------------------------------------------------- Methods --------------------------------------------------- */
    public static <T> void saveToXml(T archivos, String ruta) {
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

    public static ArchivoDatos getFromXml(String ruta) {
        try (FileReader fr = new FileReader(ruta)) {
            ArchivoDatos archivos = JAXB.unmarshal(fr, ArchivoDatos.class);
            if (archivos == null)
                throw new NullPointerException();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Archivos cargados desde archivos");
            alert.setHeaderText("Los archivos fueron cargados!");
            alert.setContentText("El archivo: " + ruta + " fue cargado");
            alert.showAndWait();

            return archivos;

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("El archivo no existeo o no pudo ser encontrado!");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("El archivo no pudo leerse, puede estar en modo sólo lectura");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("El archivo se encontraba vacío o no pudo leerse correctamente");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("Ha ocurrido un error desconocido");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();
        }

        return null;
    }

    public static <T> void saveToJson(T archivo, String ruta) {
        try (FileWriter out = new FileWriter(ruta);) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, archivo);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error interno inesperado!");
            alert.setHeaderText("No se pudo escribir el archivo XML");
            alert.setContentText("Revisa si el sistema de archivo está en sólo lectura");
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


    public static ArchivoDatos getFromJson(String ruta) {
        //Recuperar archivos desde un .json en RUTA y retornar ArchivoDatos
        try (FileReader fr = new FileReader(ruta);) {

            ObjectMapper mapper = new ObjectMapper();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Archivos cargados desde archivos");
            alert.setHeaderText("Los archivos fueron cargados!");
            alert.setContentText("El archivo: " + ruta + " fue cargado");
            alert.showAndWait();

            return mapper.readValue(fr, ArchivoDatos.class);

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("El archivo no existeo o no pudo ser encontrado!");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("El archivo no pudo leerse, puede estar en modo sólo lectura");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("El archivo se encontraba vacío o no pudo leerse correctamente");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ha ocurrido un error interno...");
            alert.setHeaderText("Ha ocurrido un error desconocido");
            alert.setContentText("Verifica el archivo o contacta al desarrollador de la aplicación");
            alert.showAndWait();
            e.printStackTrace();
        }

        return null;
    }
}
