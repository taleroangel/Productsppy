package grupo7.poo.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "pedidos")
public class ListXmlExporter {
    private ArrayList<Pedido> listaProductos;

    public ListXmlExporter() {
    }

    public ListXmlExporter(ArrayList<Pedido> lista) {
        listaProductos = lista;
    }

    @XmlElement(name = "pedido")
    public ArrayList<Pedido> getListaProductos() {
        return listaProductos;
    }
}
