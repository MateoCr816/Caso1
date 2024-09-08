package caso;

public class Productor extends Thread {
    private String tipo;  // "A" o "B"
    private int numProductos;
    private DepositoProd depositoProduccion;
    private static int contador = 0;
    private int producidos = 0;

    public Productor(String tipo, int numProductos, DepositoProd depositoProduccion) {
        this.tipo = tipo;
        this.numProductos = numProductos;
        this.depositoProduccion = depositoProduccion;
    }

    @Override
    public void run() {
        producir();
    }

    public void producir() {
        for (int i = 0; i < numProductos; i++) {
            producidos++;  // Incrementa el número de productos producidos
            int contadorActual;

            // Sincronizar solo el incremento del contador global
            synchronized (Productor.class) {
                contador++;
                contadorActual = contador;
                //System.out.println("Productor de tipo " + tipo + " inició la producción del producto " + contadorActual);
            }

            // Simular el tiempo de producción de cada producto
            try {
                Thread.sleep((long) (Math.random() * 1000));  // Simula una producción en tiempo aleatorio
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Productor de tipo " + tipo + " terminó la producción del producto " + contadorActual);

            // Almacenar el producto 
            synchronized (depositoProduccion) {
                Producto producto = new Producto(contadorActual + tipo);
                depositoProduccion.almacenarProducto(producto);
                System.out.println("Producto terminado " + producto.getTipo() + " almacenado en el depósito de producción.");
            }
        }

        // Producir y almacenar el producto final "FIN_A" o "FIN_B"
        synchronized (depositoProduccion) {
            System.out.println("Productor de tipo " + tipo + " terminó la producción del producto " + "FIN_"+ tipo);
            Producto productoFin = new Producto("FIN_" + tipo);
            depositoProduccion.almacenarProducto(productoFin);
            System.out.println("Producto final " + productoFin.getTipo() + " almacenado en el depósito de producción.");
        }
    }
}

