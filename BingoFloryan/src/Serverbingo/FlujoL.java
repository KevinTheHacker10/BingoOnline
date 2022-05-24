
package Serverbingo;

/**
 * @author Floryan Potuguez, Samir López, Kevin Murillo, Wesly Sánchez y Edwin Gómez.
 */
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import bingofloryan.BingoFloryan;

public class FlujoL extends Thread
{
  Socket nsfd;
  DataInputStream FlujoLectura;
  DataOutputStream FlujoEscritura;
  String nombre;
  public FlujoL (Socket sfd, String nombre)
  {
      this.nombre = nombre;
    nsfd = sfd;
    try
    {
      FlujoLectura = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
      FlujoEscritura = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
    }

catch(IOException ioe)
    {
      System.out.println("IOException(Flujo): "+ioe);
    }
  }

  public void run()
  {
    broadcast(nombre +"> se ha conectado");
    ServidorL.usuarios.add ((Object) this);
    
    ServidorL.nombres.add(nombre);
    broadcast(nombre + " se ha conectado");
    broadcast("Server");
    while(true)
    {
      try
      {
          String linea="";
         linea =linea+FlujoLectura.readUTF();
        if (!linea.equals(""))
	{
          linea = nombre +"> "+ linea;
	  broadcast("/n"+linea);
	}
      }
catch(IOException ioe)
      {
	ServidorL.usuarios.removeElement(this);
	broadcast(nombre+"> se ha desconectado");
	break;
      }
    }
  }
  
  public void broadcast(String mensaje)
  {
    synchronized (ServidorL.usuarios)
    {
      Enumeration e = ServidorL.usuarios.elements();
      while (e.hasMoreElements())
      {
	FlujoL f = (FlujoL) e.nextElement();
try
	{
          synchronized(f.FlujoEscritura)
	  {
            f.FlujoEscritura.writeUTF(mensaje);
	    f.FlujoEscritura.flush();
	  }
        }
	catch(IOException ioe)
	{
	  System.out.println("Error: "+ioe);
	}
      }
    }
  }
}
