package grupo7.poo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "aseo")
public class Aseo extends Producto {

    //Atributos
    private String nombreEmpresa;
    private boolean tieneInvima;
    private TipoProducto tipo;

    //Constructores
    public Aseo(
            String nombreComercial,
            double precio,
            String tienda,
            String nombreEmpresa,
            boolean tieneInvima,
            TipoProducto tipo
    ) {
        super(nombreComercial, precio, tienda);
        this.nombreEmpresa = nombreEmpresa;
        this.tieneInvima = tieneInvima;
        this.tipo = tipo;
    }

    //Constructor vacío requerido por JAXB
    public Aseo() {
    }

    /**
     * Obtener el nombre de la empresa
     *
     * @return String nombre de la empresa
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Cambiar el nombre de la empresa
     *
     * @param nombreEmpresa nueva nombre para la empresea
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Saber si tiene Invima
     *
     * @return Invima
     */
    public boolean isTieneInvima() {
        return tieneInvima;
    }

    /**
     * Cambiar el valor del Invima
     *
     * @param tieneInvima nuevo Invima
     */
    public void setTieneInvima(boolean tieneInvima) {
        this.tieneInvima = tieneInvima;
    }

    /**
     * Obtener el tipo de producto
     *
     * @return Tipo de producto
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Cambiar el tipo de producto
     *
     * @param tipo tipo del producto
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }


    /**
     * Calcular el precio de un producto de Aseo
     *
     * @return Precio del producto
     */
    @XmlTransient
    @JsonIgnore
    @Override
    public double calcularPrecio() {
        double precioTotal = super.calcularPrecio();
        switch (this.tipo) {
            case HOGAR:
            case INDUSTRIAL:
                precioTotal -= (precioTotal * 0.1);
                break;
            case HOSPITALARIO:
                precioTotal -= (precioTotal * 0.3);
                break;
        }
        return precioTotal;
    }

    @Override
    public String toString() {
        return "Aseo: \n" +
                       "\tNombreComercial: \t" + nombreComercial + '\n' +
                       "\tNombreEmpresa: \t\t" + nombreEmpresa + '\n' +
                       "\tTieneInvima: \t\t" + (tieneInvima ? "Sí" : "No") + '\n' +
                       "\tTipo: \t\t\t\t" + tipo + '\n' +
                       "\tProdId: \t\t\t" + prodId + '\n' +
                       "\tPrecio: \t\t\t" + precio + '$' + '\n' +
                       "\tTienda: \t\t\t" + tienda + '\n' +
                       "\tIVA: \t\t\t\t" + iva + '$' + '\n';
    }
}
