package bingofloryan;

/**
 * @author Floryan Potuguez, Samir López, Kevin Murillo, Wesly Sánchez y Edwin Gómez.
 */
public class Nodo {
    private String premio;
    private Nodo siguiente;
    
    public void Nodo(){
        this.premio = "";
        this.siguiente = null;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
