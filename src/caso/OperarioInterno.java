package caso;

public class OperarioInterno extends Thread {
    private DepositoProd depositoProduccion;
    private Cinta cinta;
    private DepositoDist depositoDistribucion;
    private int capDepDist;
    private int finA = 0; // Contador de productos "FIN_A"
    private int finB = 0; // Contador de productos "FIN_B"

    public OperarioInterno(DepositoProd depositoProduccion, Cinta cinta, DepositoDist depositoDistribucion, int capDepDist) {
        this.depositoProduccion = depositoProduccion;
        this.cinta = cinta;
        this.depositoDistribucion = depositoDistribucion;
        this.capDepDist = capDepDist;
    }

    @Override
    public void run() {
        moverProductos();
    }

    public void moverProductos() {
        try {
            while (true) {
                // Mover producto terminado del depósito de producción a la cinta
                synchronized (depositoProduccion) {
                    while (!cinta.estaVacia() || depositoProduccion.estaVacio()) { // Cinta llena o depósito vacío
                        depositoProduccion.wait();
                    }
                    Producto producto = depositoProduccion.retirarProducto();
                    if (producto != null) {
                        synchronized (cinta) {
                            cinta.moverProducto(producto);
                            cinta.notifyAll();  // Notificar que hay un producto en la cinta
                        }
                    }
                }

                // Mover producto de la cinta a la pila final
                synchronized (cinta) {
                    while (cinta.estaVacia() || depositoDistribucion.estaLleno()) {
                        cinta.wait();
                    }
                    Producto producto = cinta.retirarProducto();
                    if (producto != null) {
                        synchronized (depositoDistribucion) {
                            depositoDistribucion.almacenarProducto(producto);
                            depositoDistribucion.notifyAll();  // Notificar que se movió un producto al depósito
                        }
                        
                        // Si el producto es "FIN_A" o "FIN_B", aumentar el contador
                        if (producto.getTipo().equals("FIN_A")) {
                            finA++;
                        } else if (producto.getTipo().equals("FIN_B")) {
                            finB++;
                        }
                    }
                }

                // Verificar si ha procesado dos "FIN_A" y dos "FIN_B"
                if (finA == 2 && finB == 2) {
                    System.out.println("El operario interno ha procesado 2 'FIN_A' y 2 'FIN_B'. Terminando el trabajo.");
                }

                // Yield para espera semi-activa
                Thread.yield();
            }
        } catch (InterruptedException e) {
            System.out.println("El operario interno fue interrumpido.");
        }
    }
}
