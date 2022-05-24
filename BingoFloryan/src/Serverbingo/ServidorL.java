
package Serverbingo;

/**
 * @author Floryan Potuguez, Samir López, Kevin Murillo, Wesly Sánchez y Edwin Gómez.
 */
import java.io.*;
import java.net.*;
import java.util.*;


public class ServidorL extends Thread
{
  public static ArrayList<String> nombres = new ArrayList();
  public static Vector usuarios = new Vector();
  public static void main (String args[])
  {
    ServerSocket sfd = null;
    try
    {
      sfd = new ServerSocket(8000);
    }
    catch (IOException ioe)
    {
      System.out.println("Comunicación rechazada."+ioe);
      System.exit(1);
    }
        while (true)
    {
      try
      {
        Socket nsfd = sfd.accept();
        DataInputStream FlujoLectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
        String nombre = FlujoLectura.readUTF();
        System.out.println("Conexion aceptada de: "+ nombre);
	FlujoL flujo = new FlujoL(nsfd, nombre);
	Thread t = new Thread(flujo);
        t.start();
      }
      catch(IOException ioe)
      {
        System.out.println("Error: "+ioe);
      }
    }
  }
}


