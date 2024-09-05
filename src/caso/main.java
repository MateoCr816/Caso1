package caso;

import java.util.Scanner;
import java.util.Stack;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer la capacidad del depósito de producción y el número de productos a producir por consola
        System.out.print("Ingrese la capacidad del depósito de producción: ");
        int capDepProd = scanner.nextInt();

        System.out.print("Ingrese el número de productos terminados a producir por cada productor: ");
        int numProductos = scanner.nextInt();

        System.out.print("Ingrese la capacidad de la pila final: ");
        int capacidadPila = scanner.nextInt();

        // Inicialización de depósito, cinta y pila final
        DepositoProd depositoProduccion = new DepositoProd(capDepProd);
        Cinta cinta = new Cinta();
        Stack<Producto> pilaFinal = new Stack<>();

        // Inicialización de productores (2 de tipo "A" y 2 de tipo "B")
        Productor productorA1 = new Productor("A", numProductos, depositoProduccion);
        Productor productorA2 = new Productor("A", numProductos, depositoProduccion);
        Productor productorB1 = new Productor("B", numProductos, depositoProduccion);
        Productor productorB2 = new Productor("B", numProductos, depositoProduccion);

        // Producir productos
        productorA1.producir();
        productorA2.producir();
        productorB1.producir();
        productorB2.producir();

        // Operario interno para mover productos de la cinta a la pila
        OperarioInterno operarioInterno = new OperarioInterno(depositoProduccion, cinta, pilaFinal, capacidadPila);
        operarioInterno.moverProductos();

        scanner.close();
    }
}
