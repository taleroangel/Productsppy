package grupo7.poo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "fruver")
public class Fruver extends Producto {

    //Atributos
    private boolean esOrganico;
    private double impuestoLocal;
    private String nombreHacienda;

    //Constructores
    public Fruver(
            String nombreComercial,
            double precio,
            String tienda,
            boolean esOrganico,
            double impuestoLocal,
            String nombreHacienda
    ) {
        super(nombreComercial, precio, tienda);
        this.esOrganico = esOrganico;
        this.impuestoLocal = impuestoLocal;
        this.nombreHacienda = nombreHacienda;
    }

    //Constructor vacío requerido por JAXB
    public Fruver() {
    }

    @XmlTransient
    @JsonIgnore
    public Boolean getOrganico() {
        return esOrganico;
    }

    @XmlTransient
    @JsonIgnore
    public boolean isOrganico() {
        return esOrganico;
    }

    public boolean isEsOrganico() {
        return esOrganico;
    }

    /**
     * Saber si un producto es orgánico
     *
     * @return producto orgánico
     */
    @XmlTransient
    @JsonIgnore
    public boolean esOrganico() {
        return esOrganico;
    }

    /**
     * Establecer si un producto es organico
     *
     * @param esOrganico nuevo valor booleano
     */
    public void setEsOrganico(boolean esOrganico) {
        this.esOrganico = esOrganico;
    }

    /**
     * Obtener el valor del impuesto local
     *
     * @return Valor de impuesto local
     */
    public double getImpuestoLocal() {
        return impuestoLocal;
    }

    /**
     * Cambiar el valor del impuesto local
     *
     * @param impuestoLocal nuevo valor del impuesto
     */
    public void setImpuestoLocal(double impuestoLocal) {
        this.impuestoLocal = impuestoLocal;
    }

    /**
     * Obtener el nombre de hacienda
     *
     * @return String con el nombre de hacienda
     */
    public String getNombreHacienda() {
        return nombreHacienda;
    }

    /**
     * Cambiar el nombre de hacienda
     *
     * @param nombreHacienda nuevo nombre de hacienda
     */
    public void setNombreHacienda(String nombreHacienda) {
        this.nombreHacienda = nombreHacienda;
    }


    @XmlTransient
    @JsonIgnore
    @Override
    public double calcularPrecio() {
        double precioTotal = super.calcularPrecio() + this.impuestoLocal;
        if (this.esOrganico) precioTotal += (this.impuestoLocal * 0.2);
        return precioTotal;
    }

    @Override
    public String toString() {
        return "Fruver:\n" +
                       "\tProducto Orgánico: \t" + (esOrganico ? "Sí" : "No") + '\n' +
                       "\tImpuestoLocal: \t\t" + impuestoLocal + '\n' +
                       "\tNombreHacienda: \t" + nombreHacienda + '\n' +
                       "\tProdId: \t\t\t" + prodId + '\n' +
                       "\tNombreComercial: \t" + nombreComercial + '\n' +
                       "\tPrecio: \t\t\t" + precio + +'$' + '\n' +
                       "\tTienda: \t\t\t" + tienda + '\n' +
                       "\tIVA: \t\t\t\t" + iva + '$';
    }
}
