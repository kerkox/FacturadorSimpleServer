package Conect;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Paulker
 */
public class Conexion extends Thread {

    private Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String ClienteID;

    public Conexion(Socket s) {
        try {
            this.socket = s;
            this.entrada = new ObjectInputStream(socket.getInputStream());
            this.salida = new ObjectOutputStream(socket.getOutputStream());
            start();
        } catch (Exception ex) {
        }
        
    }

    public String getClienteID() {
        return ClienteID;
    }
   /**
    * en la ejecucion cuando se lee lo que envia el cliente segun el
    * codigo que se reciba asi se hace el procedimiento
    * para servirle lo que solicita
    */
    public void run(){
        try{
            int codigo = entrada.readInt();
            Object obj = entrada.readObject();
            switch(codigo){
                case 1:
                    this.ClienteID = entrada.readUTF();
                    break;
                case 2:
                    //Consulta de empleado para loguear
                    break;
                case 3:
                    GestorConexion.getInstance().desconecta(this);
                    break;
                    
            }
        }catch(Exception ex){
            System.out.println("ERROR: "+ ex.getMessage());
        }
    }
    
    public void enviarObjeto(Object obj){
        try{
            this.salida.writeObject(obj);
        }catch(Exception ex){
            
        }
    }
}
