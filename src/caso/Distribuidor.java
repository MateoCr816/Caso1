package caso;

public class Distribuidor {

    private String tipo; // "A" o "B"
    private DepositoDist depositoDistribucion;

    public Distribuidor(String tipo, DepositoDist depositoDistribucion) {
        this.tipo = tipo;
        this.depositoDistribucion = depositoDistribucion;
    }

    public void distribuir(){
        while (!depositoDistribucion.estaVacio()) {
            Producto producto = depositoDistribucion.retirarProducto();
            if (producto != null) {
                System.out.println("Distribuidor de tipo " + tipo + " distribuyó el producto: " + producto);
            }
        }
    }
    
}
