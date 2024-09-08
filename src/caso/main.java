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

        System.out.print("Ingrese la capacidad del deposito de distribución: ");
        int capDepDist = scanner.nextInt();

        // Inicialización de depósito, cinta y pila final
        DepositoProd depositoProduccion = new DepositoProd(capDepProd);
        Cinta cinta = new Cinta();
        DepositoDist depositoDistribucion = new DepositoDist(capDepDist);

        // Inicialización de productores (2 de tipo "A" y 2 de tipo "B")
        Productor productorA1 = new Productor("A", numProductos, depositoProduccion);
        Productor productorA2 = new Productor("A", numProductos, depositoProduccion);
        Productor productorB1 = new Productor("B", numProductos, depositoProduccion);
        Productor productorB2 = new Productor("B", numProductos, depositoProduccion);

        // Inicialización de distribuidores (2 de tipo "A" y 2 de tipo "B")
        Distribuidor distribuidorA1 = new Distribuidor("A", depositoDistribucion);
        Distribuidor distribuidorA2 = new Distribuidor("A", depositoDistribucion);
        Distribuidor distribuidorB1 = new Distribuidor("B", depositoDistribucion);
        Distribuidor distribuidorB2 = new Distribuidor("B", depositoDistribucion);


        // Producir productos
        productorA1.start();
        productorA2.start();
        productorB1.start();
        productorB2.start();


        // Operario interno para mover productos de la cinta a la pila
        OperarioInterno operarioInterno = new OperarioInterno(depositoProduccion, cinta, depositoDistribucion, capDepDist);
        operarioInterno.moverProductos();

        // Distribuir productos
        //distribuidorA1.distribuir();
        //distribuidorA2.distribuir();
        //distribuidorB1.distribuir();
        //distribuidorB2.distribuir();

        scanner.close();
    }
}
