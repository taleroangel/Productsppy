package grupo7.poo.entity;

public enum TipoServicio {
    BONOREGALO("Bono de regalo"),
    ENVIOPRIME("Envío prime");

    private String value;

    TipoServicio(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
