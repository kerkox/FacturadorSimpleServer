package Conect;

import Obj.Empleado;
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
    private boolean conectado = false;

    public Conexion(Socket s) {
        try {
            this.conectado = true;
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
     * en la ejecucion cuando se lee lo que envia el cliente segun el codigo que
     * se reciba asi se hace el procedimiento para servirle lo que solicita
     */
    public void run() {
        try {

            while (conectado) {
                int codigo = entrada.readInt();
                Object obj = entrada.readObject();
                System.out.println("Leyo los objetos recibidos");
                switch (codigo) {
                    case 1:
                        this.ClienteID = (String) obj;
//                    System.out.println("CLienteID: "+ this.ClienteID);
                        break;
                    case 2:
                        //Consulta de empleado para loguear
                        System.out.println("Entro a la opcion 2");
                        Empleado e = (Empleado) obj;
//                    System.out.println("Name: "+ e.getNombre());
                        //simulacion de consulta a la BD
                        System.out.println("op 2");
                        System.out.println("e : " + e);
                        if (e.getNombre() == "admin") {
                            if (e.getPassword() == "1234") {
                                salida.writeInt(2);
                                salida.writeObject("true");
                            }
                        }
                        break;
                    case 3:
                        GestorConexion.getInstance().desconecta(this);
                        this.conectado=false;
                        break;

                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR 0x1: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void enviarObjeto(int code, Object obj) {
        try {
            this.salida.writeInt(code);
            this.salida.writeObject(obj);
        } catch (Exception ex) {

        }
    }
}
