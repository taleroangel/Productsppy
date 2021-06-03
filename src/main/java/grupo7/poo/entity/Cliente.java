package grupo7.poo.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase de un cliente
 */
@XmlRootElement
public class Cliente {

    //Atributos
    private String nombreCompleto;
    private Long cedula;
    private long telefonoContacto;
    private String direccion;

    public Cliente() {
    }

    //Constructores
    public Cliente(
            Long cedula,
            String nombreCompleto,
            long telefonoContacto,
            String direccion
    ) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefonoContacto = telefonoContacto;
        this.direccion = direccion;
    }

    //Getters y Setters

    /**
     * Obtener el nombre de un cliente
     *
     * @return String con el nombre del cliente
     */
    @XmlElement
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Cambiar el nombre del cliente
     *
     * @param nombreCompleto nuevo nombre para el cliente
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtener la cedula de un clinete
     *
     * @return cedula del cliente
     */

    @XmlElement
    public Long getCedula() {
        return cedula;
    }

    /**
     * Cambiar la cedula de un cliente
     *
     * @param cedula nueva cedula del cliente
     */
    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtener el teléfono del cliente
     *
     * @return numero de teléfono del cliente
     */
    @XmlElement
    public long getTelefonoContacto() {
        return telefonoContacto;
    }

    /**
     * Cambiar el telefono de un cliente
     *
     * @param telefonoContacto nuevo numero de telefono del cliente
     */
    public void setTelefonoContacto(long telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    /**
     * Obtener la dirección de un cliente
     *
     * @return String con la direccion del cliente
     */
    @XmlElement
    public String getDireccion() {
        return direccion;
    }

    /**
     * Obtener la dirección de un cliente
     *
     * @param direccion direccion del cliente
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente: \n" +
                       "\tNombre Completo: \t" + this.nombreCompleto + '\n' +
                       "\tCédula: \t\t" + this.cedula + '\n' +
                       "\tTeléfono: \t\t" + this.telefonoContacto + '\n' +
                       "\tDirección: \t\t" + this.direccion;
    }
}
