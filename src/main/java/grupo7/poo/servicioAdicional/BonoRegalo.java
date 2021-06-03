package grupo7.poo.servicioAdicional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bonoRegalo")
public class BonoRegalo extends ServicioAdicional { //subclase de la clase de servicio adicional

    //Atributos
    public String comercioAsociado;
    public Calendar fechaVencimiento;
    public String mensaje;

    public BonoRegalo(
            long codigoServicio,
            String nombreServicio,
            double precio,
            String comercioAsociado,
            String mensaje,
            Calendar fechaVencimiento
    ) {
        super(codigoServicio, nombreServicio, precio);
        this.comercioAsociado = comercioAsociado;
        this.fechaVencimiento = fechaVencimiento;
        this.mensaje = mensaje;
    }

    public BonoRegalo(
            long codigoServicio,
            double precio,
            String comercioAsociado
    ) {
        super(codigoServicio, precio);
        this.comercioAsociado = comercioAsociado;
    }

    public BonoRegalo() {
    }

    //Getter n Setter

    public String getComercioAsociado() {
        return comercioAsociado;
    }

    public void setComercioAsociado(String comercioAsociado) {
        this.comercioAsociado = comercioAsociado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Calendar getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Calendar fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public double calcularPrecio() {
        return this.precio;
    }

    @Override
    public String toString() {
        return "BonoRegalo \n" +
                       "\tComercioAsociado: \t" + comercioAsociado + '\n' +
                       "\tMensaje: \t" + mensaje + '\n' +
                       "\tFechaVencimiento: \t" + fechaVencimiento + '\n' +
                       "\tCodigoServicio: \t" + codigoServicio + '\n' +
                       "\tNombreServicio: \t" + nombreServicio + '\n' +
                       "\tPrecio: \t" + precio + '$';
    }
}
