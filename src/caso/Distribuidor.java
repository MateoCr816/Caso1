package caso;

public class Distribuidor extends Thread {

    private String tipo; // "A" o "B"
    private DepositoDist depositoDistribucion;

    public Distribuidor(String tipo, DepositoDist depositoDistribucion) {
        this.tipo = tipo;
        this.depositoDistribucion = depositoDistribucion;
    }

    @Override
    public void run() {
        distribuir();  // Llamada al método distribuir cuando el hilo se ejecuta
    }

    public void distribuir() {
        try {
            while (true) {
                synchronized (depositoDistribucion) {
                    // Esperar mientras el depósito está vacío o no hay productos del tipo que distribuye
                    while (depositoDistribucion.estaVacio() || !depositoDistribucion.hayProductoDeTipo(tipo)) {
                        depositoDistribucion.wait();  // Espera pasiva
                    }

                    // Retirar y distribuir producto del tipo correspondiente
                    Producto producto = depositoDistribucion.retirarProductoDeTipo(tipo);
                    if (producto != null) {
                        System.out.println("Distribuidor de tipo " + tipo + " distribuyó el producto: " + producto);
                    }

                    // Notificar a otros hilos que podrían estar esperando
                    depositoDistribucion.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("El distribuidor fue interrumpido.");
        }
    }
}
