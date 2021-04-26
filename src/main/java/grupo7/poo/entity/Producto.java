package grupo7.poo.entity;

import java.util.UUID;

public class Producto { //------------------clase herencia padre de fruver y aseo------------------------------

    // Atributos
    protected UUID prodId;
    protected String nombreComercial;
    protected double precio;
    protected String tienda;
    protected double iva;

    // Static
    protected static final int VALOR_IVA = 19; //para que el hijo tenga acceso

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
    public Producto(String nombreComercial, double precio, String tienda, UUID id) {
        this.nombreComercial = nombreComercial;
        this.precio = precio;
        this.tienda = tienda;
        this.prodId = id;

        this.iva = calcularIVA(this.precio); // lo lleva al metodo de arriba para calcular el iva
    }

    public Producto(String nombreComercial, double precio, String tienda) {
        this(nombreComercial, precio, tienda, UUID.randomUUID());
    }

    // Getters y Setters
    public UUID getProdId() {
        return prodId;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        // Recalcular el IVA
        this.iva = calcularIVA(this.precio);
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public double getIva() {
        return iva;
    }

    /**
     * Calcula el precio total de un producto con su IVA
     *
     * @return Precio del producto con el IVA
     */
    public double calcularPrecio() {
        return this.precio + (this.precio * this.iva);
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
