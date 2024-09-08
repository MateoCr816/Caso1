package caso;

public class OperarioInterno extends Thread {
    private DepositoProd depositoProduccion;
    private Cinta cinta;
    private DepositoDist depositoDistribucion;
    private int capDepDist;

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
                    while (!cinta.estaVacia() || depositoProduccion.estaVacio()) { //Cinta llena o deposito de produccion vacio
                        depositoProduccion.wait();
                    }
                    Producto producto = depositoProduccion.retirarProducto();
                    if (producto != null) {
                        synchronized (cinta) {
                            cinta.moverProducto(producto);
                            //System.out.println("Operario interno movió producto a la cinta: " + producto);
                            cinta.notifyAll();  // Notificar a otros hilos que hay un producto en la cinta
                        }
                    }
                }

                // Mover producto de la cinta a la pila final
                synchronized (cinta) {
                    while (cinta.estaVacia() || depositoDistribucion.estaLleno()) {
                        // Esperar si la cinta está vacía o el depósito de distribución está lleno
                        cinta.wait();
                    }
                    Producto producto = cinta.retirarProducto();
                    if (producto != null) {
                        synchronized (depositoDistribucion) {
                            depositoDistribucion.almacenarProducto(producto);
                            //System.out.println("Operario interno movió producto terminado a la pila final: " + producto);
                            depositoDistribucion.notifyAll();  // Notificar que se movió un producto al depósito
                        }
                    }
                }

                // Verificar si la pila ha alcanzado su capacidad máxima
                if (depositoDistribucion.estaLleno()) {
                    System.out.println("La pila final ha alcanzado su capacidad máxima. Finalizando el programa.");
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("El operario interno fue interrumpido.");
        }
    }
}
