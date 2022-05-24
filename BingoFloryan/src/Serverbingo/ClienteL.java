
package Serverbingo;

/**
 * @author Floryan Potuguez, Samir López, Kevin Murillo, Wesly Sánchez y Edwin Gómez.
 */
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;


public class ClienteL extends Frame implements ActionListener
{
  static Socket sfd = null;
  static DataInputStream EntradaSocket;
  static DataOutputStream SalidaSocket;
  static TextField salida;
  static TextArea entrada;  
  String texto;
  static String X;
  static Scanner entradaTexto = new Scanner(System.in);

  public ClienteL()
  {
    setTitle("Chat");
    setSize(350,200);

    salida = new TextField(30);
    salida.addActionListener(this);
	
    entrada = new TextArea();
    entrada.setEditable(false);
	
    add("South",salida);
    add("Center", entrada);
    setVisible(true);
  }

  public static void main(String[] args)
  {
    ClienteL cliente = new ClienteL();
    //String y=JOptionPane.showInputDialog("Digite la IP a conectar");
    //X=JOptionPane.showInputDialog("Digite su nombre");
    try
    {
      sfd = new Socket("25.65.101.92",8000);
      EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
      SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
        System.out.println("Escriba su nombre");
        SalidaSocket.writeUTF(entradaTexto.nextLine());
        
        System.out.println(X);
    }
    catch (UnknownHostException uhe)
    {
      System.out.println("No se puede acceder al servidor.");
      System.exit(1);
    }
    catch (IOException ioe)
    {
      System.out.println("Comunicación rechazada.");
      System.exit(1);
    }
    while (true)
    {
      try
      { 
          
      
        String linea = EntradaSocket.readUTF();
        entrada.append(linea+"\n");
//          String[] n =new String[1];
//          n[0]=EntradaSocket.readUTF();
//          String[] arrOfStr = n[0].split("%");
//          for (int i = 0; i < arrOfStr.length; i++) {
//             System.out.println(arrOfStr[i]); 
//            }
//        String linea = EntradaSocket.readUTF();
//        entrada.append(linea);
        
      }
      catch(IOException ioe)
      {
	System.exit(1);
      }
  }
  }
  
  public void actionPerformed (ActionEvent e)
  {
    texto = salida.getText();
    salida.setText("");
    try
    {
      SalidaSocket.writeUTF(texto);
      SalidaSocket.flush();
    }
    catch (IOException ioe)
    {
      System.out.println("Error: "+ioe);
    }
  }
  
  public boolean handleEvent(Event e)
  {
if ((e.target == this) && (e.id == Event.WINDOW_DESTROY))
    {
      if (sfd != null)
      {
	try
	{
	  sfd.close();
	}
	catch (IOException ioe)
	{
	  System.out.println("Error: "+ioe);
	}
	this.dispose();
      }
    }
    return true;
  }
}
