package bingofloryan;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import InterfazBingo.Bingo;

/**
 * @author Floryan Potuguez, Samir López, Kevin Murillo, Wesly Sánchez y Edwin Gómez.
 */
public class BingoFloryan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bingo nr = new Bingo();
        nr.setVisible(true);
        Scanner input = new Scanner(System.in);
        ArrayList<int[][]> cartones = new ArrayList<>();
        ArrayList<Integer> ListaNumeros = new ArrayList<>();
        String tipoJuego ;
        String opcion ;
        int numeroAlAzar = 0;
        boolean repetir = true;
        boolean valid;
        boolean jugando = true;
        Lista premios = new Lista();
        int numCarton;
        premios.agregarAlFinal("100 mil colones");
        premios.agregarAlFinal("Lavadora 12 KG");
        premios.agregarAlFinal("200 mil colones");
        premios.agregarAlFinal("Refrigeradora");
        
        while(jugando){
            if(premios.esVacia()){
                System.out.println("********************************");
                System.out.println("NO HAY PREMIOS");
                System.out.println("SALIENDO DEL JUEGO");
                jugando = false;
                repetir = false;
                System.out.println("********************************");
                break;
            }
            numCarton = 0;
            cartones.clear();
            System.out.println("****BINGO****");
            premios.listar();
            System.out.println();
            System.out.println("Generación de cartones");
            System.out.print("Introduce el numero de cartones a jugar: ");
            
            numCarton = input.nextInt();
            while (numCarton <= 1) {
                System.out.println("El numero no es válido, tiene que ser 2 o mayor a 2.");
                System.out.println("Introduce un nuevo número");
                numCarton = input.nextInt();
            }

            for (int i = 0; i < numCarton; i++) {
                cartones.add(Cartones(i));
            }  
            System.out.println("CARTONES GENERADOS");
            System.out.println("");
            System.out.println("Selecione el modo de juego");
            System.out.println("1. Cuatro esquinas \n2. Linea Horizontal");
            tipoJuego = input.nextLine();
            while (!tipoJuego.equals("1") && !tipoJuego.equals("2")) {
                System.out.println("El numero tiene que ser 1 o 2.");
                System.out.println("Introduce un número");
                tipoJuego = input.nextLine();
            }

            ListaNumeros.clear();
            valid = false;
            repetir = true;
            while (repetir){
                System.out.println("************MENU************");
                if(tipoJuego.equals("1")){ 
                    System.out.println("JUGANDO 4 ESQUINAS");
                }else{
                    System.out.println("JUGANDO LINE HORIZONTAL");
                }
                System.out.println("1. Ver Cartones en juego actual");
                System.out.println("2. Generar Número");
                System.out.println("3. Ver Lista de premios");
                System.out.println("4. Salir");
                opcion = input.nextLine();

                switch(opcion) {
                    case "1":
                        for (int i = 0; i < cartones.size(); i++) {
//                            Listcartones(cartones.get(i),i);
//                           imprimir(cartones.get(i), i);

                            
                        }
                      break;                  
                    case "2":
                        while (!valid) {
                            BingoFloryan n = new BingoFloryan();  
                            numeroAlAzar = n.Numeros();
                            if (!ListaNumeros.contains(numeroAlAzar)) {
                                valid = true;
                                ListaNumeros.add(numeroAlAzar);
                            }
                        }
                        System.out.println("Numero.... " + numeroAlAzar);
                        valid = false;
                        if(validarCartonesConNumero(cartones,numeroAlAzar,tipoJuego)){
                            premios.removerPorPosicion(0);
                             repetir = false;
                        }
                        break;
                    case "3":
                        premios.listar();
                        break;
                    case "5":
                        repetir = false;
                        jugando = false;
                        break;
              } 
            }
        }
    }
    
    public int Numeros() {
        Random rand = new Random();
        int num = rand.nextInt(75) + 1;
        return num;
    }

    public static int[][] Cartones(int num) {
        int[][] card = new int[5][5];
        ArrayList<Integer> alreadyUsed = new ArrayList<>();
        boolean valid = false;
        int a = 0;
        
        alreadyUsed.clear();
        for (int i = 0; i <= 4; i++) {
            for (int row = 0; row < card.length; row++) {
                while (!valid) {
                    a = (int) (Math.random() * 15) + 1 + 15 * i;
                    if (!alreadyUsed.contains(a)) {
                        valid = true;
                        alreadyUsed.add(a);
                    }
                }
                card[row][i] = a;
                valid = false;
            }
        }
        card[2][2] = -1;
        
        //imprimir(card,num);
        return card;
    }
    
    private static void imprimir(int[][] card,int num) {
        //Arreglo del titulo 
        String title[] = {"B", "I", "N", "G", "O","%"};
        System.out.println("");
        System.out.println("CARTÓN # "+(num+1));
        for (int i = 0; i < title.length; i++) {
            System.out.print(title[i] + "\t");
        }
        System.out.println();
        for (int row = 0; row < card.length; row++) {
            for (int col = 0; col < card[row].length; col++) {
                if(card[row][col] == -1){
                    System.out.print("X" + "\t");
                }else{
                    System.out.print(card[row][col] + "\t");
                }
                if (col==4) {
                    System.out.println("%");
                }
            }
            System.out.println();
            
        }
    }

    private static boolean validarCartonesConNumero(ArrayList<int[][]> cartones, int numeroAlAzar, String tipoJuego) {
        int cont = 0;
        boolean ganador = false;
        for (int[][] carton : cartones) {
            for (int i = 0; i < carton.length; i++) {
                for (int j = 0; j < carton.length ; j++) {
                    if(carton[i][j]==numeroAlAzar){
                        carton[i][j] = -1;
                    }
                }
            }
        }
        if (tipoJuego.equals("1")){
            for (int[][] carton : cartones) {
                if(carton[0][0] == -1 &&
                        carton[0][4] == -1 &&
                        carton[4][0] == -1 &&
                        carton[4][4] == -1){
                    System.out.println("");
                    System.out.println("******************************************");
                    System.out.println("BINGOOOO CARTÓN # " + (cont+1) + " GANADOR ");
                    System.out.println("******************************************");
                    System.out.println("");
                    ganador = true;
                }
                cont++;
            }
        }
        if (tipoJuego.equals("2")){
            for (int[][] carton : cartones) {
                for (int i = 0; i < carton.length; i++) {
                    if(carton[i][0] == -1 &&
                        carton[i][1] == -1 &&
                        carton[i][2] == -1 &&
                        carton[i][3] == -1 &&
                        carton[i][4] == -1
                        ){
                        System.out.println("");
                        System.out.println("******************************************");
                        System.out.println("BINGOOOO CARTÓN # " + (cont+1) + " GANADOR ");
                        System.out.println("******************************************");
                        System.out.println("");
                        ganador = true;
                    }
                }
                cont++;
            }
        }
        
        return ganador;        
    }
    
    public String[] Listcartones(int[][] card,int num){
    String[] ListadeNumeros = new String[1];
    String title[] = {"B    ", "    I   ", "    N   ", "    G   ", "    O   "};
        System.out.println("");
        ListadeNumeros[0]="CARTON # "+(Integer.toString(num+1)+"    %"); 
        for (int i = 0; i < title.length; i++) {
            ListadeNumeros[0]=ListadeNumeros[0]+(title[i]);
            if (i==4) {
                    ListadeNumeros[0]=ListadeNumeros[0]+"   %";
                }
        }
        for (int row = 0; row < card.length; row++) {
            for (int col = 0; col < card[row].length; col++) {
                if(card[row][col] == -1){
                    ListadeNumeros[0]=ListadeNumeros[0]+"   X   ";
                }else{
                    ListadeNumeros[0]=ListadeNumeros[0]+card[row][col]+"    ";
                }
                if (col==4) {
                    ListadeNumeros[0]=ListadeNumeros[0]+"   %   ";
                }
            }
            
        }
        System.out.println(ListadeNumeros[0]);
        return ListadeNumeros;

    }
    
}
