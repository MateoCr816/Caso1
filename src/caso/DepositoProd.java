package caso;

import java.util.LinkedList;
import java.util.Queue;

public class DepositoProd {
    private int capacidad;
    private Queue<Producto> productos;

    public DepositoProd(int capacidad) {
        this.capacidad = capacidad;
        this.productos = new LinkedList<>();
    }

    public boolean almacenarProducto(Producto producto) {
        if (productos.size() < capacidad) {
            productos.add(producto);
            System.out.println("Producto almacenado: " + producto);
            return true;
        } else {
            System.out.println("Deposito de Producción lleno. No se puede almacenar más productos.");
            return false;
        }
    }

    public Producto retirarProducto() {
        if (!productos.isEmpty()) {
            Producto producto = productos.poll();
            System.out.println("Producto retirado del depósito: " + producto);
            return producto;
        } else {
            System.out.println("Deposito de Producción vacío.");
            return null;
        }
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }

    public boolean estaLleno() {
        return productos.size() >= capacidad;
    }
}
