package grupo7.poo.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@XmlRootElement(name = "productos")
public class MapXmlExporter {
    private Map<UUID, Producto> listaProductos;

    public MapXmlExporter() {
    }

    public MapXmlExporter(Map<UUID, Producto> lista) {
        listaProductos = lista;
    }

    //@XmlElementWrapper(name = "productos")
    @XmlElement(name = "producto")
    public Map<UUID, Producto> getListaProductos() {
        return listaProductos;
    }
}
