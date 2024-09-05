package caso;

public class Producto {
    private String tipo;

    public Producto(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Producto{" + "tipo='" + tipo + '\'' + '}';
    }
}
