package grupo7.poo.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

/**
 * Clase base para Fruver y Aseo
 */
@XmlRootElement(name = "Producto")
public class Producto {

    // Atributos
    protected UUID prodId;
    protected String nombreComercial;
    protected double precio;
    protected String tienda;
    protected double iva;

    // Static
    public static final int VALOR_IVA = 19;

    /**
     * Calcular el IVA del producto
     *
     * @param precio Precio del producto sin iva
     * @return El IVA del producto
     */
    private static double calcularIVA(double precio) {
        return (precio * VALOR_IVA) / 100;
    }

    // Constructores
    public Producto(
            String nombreComercial,
            double precio,
            String tienda,
            UUID id) {
        this.nombreComercial = nombreComercial;
        this.precio = precio;
        this.tienda = tienda;
        this.prodId = id;

        this.iva = calcularIVA(this.precio); // lo lleva al metodo de arriba para calcular el iva
    }

    public Producto(String nombreComercial, double precio, String tienda) {
        this(nombreComercial, precio, tienda, UUID.randomUUID());
    }

    public Producto() {
        prodId = UUID.randomUUID();
    }

    // Getters y Setters

    /**
     * Obtener el ID de un producto
     *
     * @return UUID del producto
     */

    @XmlElement
    public UUID getProdId() {
        return prodId;
    }

    /**
     * UUID del producto en String
     *
     * @return UUID String version
     */
    public String getProdIDString() {
        return prodId.toString();
    }

    /**
     * Establecer el UUID de un producto
     *
     * @param prodId UUID del producto
     */
    public void setProdId(UUID prodId) {
        this.prodId = prodId;
    }

    /**
     * Obtener el nombre comercial de un producto
     *
     * @return Nombre comercial de un producto
     */
    @XmlElement
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * Establecer el nombre comercial de un producto
     *
     * @param nombreComercial Nombre comercial del producto
     */
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * Obtener el precio de un producto
     *
     * @return Precio del producto
     */
    @XmlElement
    public double getPrecio() {
        return precio;
    }

    /**
     * Establecer el precio de un producto
     *
     * @param precio Nuevo precio a establecer
     */
    public void setPrecio(double precio) {
        this.precio = precio;
        // Recalcular el IVA
        this.iva = calcularIVA(this.precio);
    }

    /**
     * Obtener el nombre de la tienda
     *
     * @return Nombre de la tienda
     */
    @XmlElement
    public String getTienda() {
        return tienda;
    }

    /**
     * Establecer la tienda
     *
     * @param tienda Nombre de la tienda a establecer
     */
    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    /**
     * Calcula el precio del IVA y lo retorna
     *
     * @return precio del IVA de un producto
     */
    @XmlElement(name = "IVAProducto")
    public double getIva() {
        return iva;
    }

    /**
     * Calcula el precio total de un producto con su IVA
     *
     * @return Precio del producto con el IVA
     */
    public double calcularPrecio() {
        return this.precio + (this.precio * (this.iva/100.0));
    }

    @Override
    public String toString() {
        return "Producto: \n" +
                       "\tID del producto: \t" + this.prodId + '\n' +
                       "\tNombre Comercial: \t" + this.nombreComercial + '\n' +
                       "\tNombre Tienda: \t\t" + this.tienda + '\n' +
                       "\tPrecio: \t\t\t" + this.precio + '$' + '\n' +
                       "\tIVA: \t\t\t\t" + this.iva + '$';
    }
}
