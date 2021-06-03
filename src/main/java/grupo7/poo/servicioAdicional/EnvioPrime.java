package grupo7.poo.servicioAdicional;

import grupo7.poo.entity.TipoTransporte;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "envioPrime")
public class EnvioPrime extends ServicioAdicional {// subclase de la clase de servicio adicional

    //Atributos
    private Double distancia;
    private TipoTransporte tipo;
    private Integer numeroCajas;

    //Constructor
    public EnvioPrime(
            long codigoServicio,
            String nombreServicio,
            double precio,
            Double distancia,
            TipoTransporte tipo,
            Integer numeroCajas) {
        super(codigoServicio, nombreServicio, precio);
        this.distancia = distancia;
        this.tipo = tipo;
        this.numeroCajas = numeroCajas;
    }

    public EnvioPrime(long codigoServicio, double precio, Double distancia, TipoTransporte tipo, Integer numeroCajas) {
        super(codigoServicio, precio);
        this.distancia = distancia;
        this.tipo = tipo;
        this.numeroCajas = numeroCajas;
    }

    public EnvioPrime() {
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public TipoTransporte getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransporte tipo) {
        this.tipo = tipo;
    }

    public Integer getNumeroCajas() {
        return numeroCajas;
    }

    public void setNumeroCajas(Integer numeroCajas) {
        this.numeroCajas = numeroCajas;
    }

    //Metodos

    @Override
    public double calcularPrecio() {
        //Recargos por tipo de transporte
        double precioTotal = this.precio;
        switch (this.tipo) {
            case BICICLETA:
                break;

            case MOTO:
                precioTotal *= 1.10d;
                break;

            case MINIVAN:
                precioTotal *= 1.25d;
                break;

        }
        //Recargo por las cajas y la distnacia
        precioTotal += (this.numeroCajas * this.distancia * 1200d);
        return precioTotal;
    }

    @Override
    public String toString() {
        return "EnvioPrime: \n " +
                       "\tDistancia: \t" + distancia + '\n' +
                       "\tTipo: \t" + tipo + '\n' +
                       "\tNumeroCajas: \t" + numeroCajas + '\n' +
                       "\tCodigoServicio: \t" + codigoServicio + '\n' +
                       "\tNombreServicio: \t" + nombreServicio + '\n' +
                       "\tPrecio: \t" + precio + '$';
    }
}
