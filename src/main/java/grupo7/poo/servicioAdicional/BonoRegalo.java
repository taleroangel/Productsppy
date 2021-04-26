package grupo7.poo.servicioAdicional;

import grupo7.poo.controller.ControlDespacho;
import grupo7.poo.entity.Pedido;

import java.util.Calendar;
import java.util.Scanner;
import java.util.UUID;

public class BonoRegalo extends ServicioAdicional { //subclase de la clase de servicio adicional

    //Atributos
    public String comercioAsociado;
    public String mensaje;
    public Calendar fechaVencimiento;

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
        this.mensaje = mensaje;
        this.fechaVencimiento = fechaVencimiento;
    }

    public BonoRegalo(
            long codigoServicio,
            double precio,
            String comercioAsociado
    ) {
        super(codigoServicio, precio);
        this.comercioAsociado = comercioAsociado;
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
        //metodo sobreescrito
        Scanner scaner = new Scanner(System.in);
        double precioTotal = this.precio;
        System.out.println("Desea adquirir una tarjeta de regalo? S/N");
        char respuesta = scaner.next().charAt(0);

        while (
                respuesta != 'n' &&
                        'N' != respuesta &&
                        respuesta != 's' &&
                        respuesta != 'S'
        ) {
            System.out.println("Opción inválida");
            System.out.println("  ");
            respuesta = scaner.next().charAt(0);
        }
        if (respuesta == 's' || respuesta == 'S') {
            //System.out.println("Ingrese el valor de la tarjeta: ");
            //double valorTarjeta = scaner.nextDouble();
            System.out.println(
                    "Ingrese el mensaje personalizado que quiere en la tarjeta: "
            );
            this.mensaje = scaner.next();
            System.out.println("Ingrese el codigo de su pedido");
            String id = scaner.next();

            Pedido pedido = new Pedido();
            ControlDespacho control = new ControlDespacho();
            pedido = control.buscarPedido(UUID.fromString(id));
            int dia = pedido.getFechaRecibido().getTime().getDay();
            int mes = pedido.getFechaRecibido().getTime().getMonth();
            int anno = pedido.getFechaRecibido().getTime().getYear();

            this.fechaVencimiento.set(anno, mes + 6, dia);
        }
        return precioTotal;
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
