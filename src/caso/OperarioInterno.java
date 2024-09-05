package caso;

import java.util.Stack;

public class OperarioInterno {
    private DepositoProd depositoProduccion;
    private Cinta cinta;
    private Stack<Producto> pilaFinal;
    private int capacidadPila;

    public OperarioInterno(DepositoProd depositoProduccion, Cinta cinta, Stack<Producto> pilaFinal, int capacidadPila) {
        this.depositoProduccion = depositoProduccion;
        this.cinta = cinta;
        this.pilaFinal = pilaFinal;
        this.capacidadPila = capacidadPila;
    }

    public void moverProductos() {
        while (!depositoProduccion.estaVacio() || !cinta.estaVacia()) {
            // Mover producto terminado del depósito de producción a la cinta
            if (cinta.estaVacia() && !depositoProduccion.estaVacio()) {
                Producto producto = depositoProduccion.retirarProducto();
                if (producto != null) {
                    cinta.moverProducto(producto);
                    System.out.println("Operario interno movió producto a la cinta: " + producto);
                }
            }

            // Mover producto de la cinta a la pila final si es "FIN_A" o "FIN_B"
            if (!cinta.estaVacia() && pilaFinal.size() < capacidadPila) {
                Producto producto = cinta.retirarProducto();
                if (producto != null) {
                    pilaFinal.push(producto);
                    System.out.println("Operario interno movió producto terminado a la pila final: " + producto);
                }
            }

            // Verificar si la pila ha alcanzado su capacidad máxima
            if (pilaFinal.size() >= capacidadPila) {
                System.out.println("La pila final ha alcanzado su capacidad máxima. Finalizando el programa.");
                break;
            }
        }
    }
}
