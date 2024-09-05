package caso;

public class Productor {
    private String tipo;  // "A" o "B"
    private int numProductos;
    private DepositoProd depositoProduccion;

    public Productor(String tipo, int numProductos, DepositoProd depositoProduccion) {
        this.tipo = tipo;
        this.numProductos = numProductos;
        this.depositoProduccion = depositoProduccion;
    }

    public void producir() {
        // Producir productos terminados
        for (int i = 0; i < numProductos; i++) {
            System.out.println("Productor de tipo " + tipo + " inició la producción del producto " + (i + 1));

            // Simulación de producción de un producto
            // Aquí se puede agregar lógica de espera o procesamiento si fuera necesario
            System.out.println("Productor de tipo " + tipo + " terminó la producción del producto " + (i + 1));

            // Almacenar el producto terminado "FIN_A" o "FIN_B" en el depósito de producción
            Producto productoFin = new Producto("FIN_" + tipo);
            depositoProduccion.almacenarProducto(productoFin);
            System.out.println("Producto terminado " + productoFin.getTipo() + " almacenado en el depósito de producción.");
        }
    }
}
