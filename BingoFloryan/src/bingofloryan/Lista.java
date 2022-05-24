package bingofloryan;

/**
 * @author Floryan Potuguez, Samir López, Kevin Murillo, Wesly Sánchez y Edwin Gómez.
 */
public class Lista {
    private Nodo inicio;
    private int tamanio;
    
    public void Lista(){
        inicio = null;
        tamanio = 0;
    }
    
    public boolean esVacia(){
        return inicio == null;
    }
    public int getTamanio(){
        return tamanio;
    }
    
    public void agregarAlFinal(String valor){
        Nodo nuevo = new Nodo();
        nuevo.setPremio(valor);
        if (esVacia()) {
            inicio = nuevo;
        } else{
            Nodo aux = inicio;
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
        tamanio++;
    }
    
    public void listar(){
        if (!esVacia()) {
            Nodo aux = inicio;
            int i = 0;
            System.out.println("------------------ PREMIOS DISPONIBLES ------------------");
            while(aux != null){
                System.out.print("***** " + aux.getPremio()+ " *****\n");
                aux = aux.getSiguiente();
                i++;
            }
            System.out.println("-----------------------------------------------");
        }
    }
    
    public void removerPorPosicion(int posicion){
        if(posicion>=0 && posicion<tamanio){
            System.out.println("**********************************");
            System.out.println("PREMIO SE GANÓ : " + inicio.getPremio());
            System.out.println("**********************************");
            System.out.println("");
            if(posicion == 0){
                inicio = inicio.getSiguiente();
            }
            else{
                Nodo aux = inicio;
                for (int i = 0; i < posicion-1; i++) {
                    aux = aux.getSiguiente();
                }
                Nodo siguiente = aux.getSiguiente();
                aux.setSiguiente(siguiente.getSiguiente());
            }
            tamanio--;
        }
    }
    
}
