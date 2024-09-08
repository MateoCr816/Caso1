package caso;

public class Cinta {
    private Producto producto;

    public Cinta() {
        this.producto = null;
    }

    public boolean moverProducto(Producto producto) {
        if (this.producto == null) {
            this.producto = producto;
            System.out.println("Producto movido a la cinta: " + producto);
            return true;
        } else {
            System.out.println("La cinta ya está ocupada.");
            return false;
        }
    }

    public Producto retirarProducto() {
        if (this.producto != null) {
            Producto producto = this.producto;
            this.producto = null;
            System.out.println("Producto retirado de la cinta: " + producto);
            return producto;
        } else {
            System.out.println("La cinta está vacía.");
            return null;
        }
    }

    public boolean estaVacia() {
        return this.producto == null;
    }

}
