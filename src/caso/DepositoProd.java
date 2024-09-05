package caso;

class DepositoProd {
    private int capacidad;
    private int numeroDeposito;

    // Constructor
    public DepositoProd(int capacidad, int numeroDeposito) {
        this.capacidad = capacidad;
        this.numeroDeposito = numeroDeposito;
    }

    // Getter for capacidad
    public int getCapacidad() {
        return capacidad;
    }

    // Setter for capacidad
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    // Getter for numeroDeposito
    public int getNumeroDeposito() {
        return numeroDeposito;
    }

    // Setter for numeroDeposito
    public void setNumeroDeposito(int numeroDeposito) {
        this.numeroDeposito = numeroDeposito;
    }
}