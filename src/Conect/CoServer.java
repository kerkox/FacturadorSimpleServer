package Conect;

import Obj.Empleado;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulker
 */
public class CoServer extends Thread {

    private ServerSocket server = null;
    private Socket socket = null;
    private Object recibido;
    private ObjectInputStream in;
    private int Port = 5000;

    public CoServer(int puerto) {
        this.Port = puerto;
    }

    public CoServer() {
    }

    @Override
    public void run() {
        //Aqui ejecuto la conexion del servidor para quedar a la espera 
        server = null;
        try {
            server = new ServerSocket(Port);
            while (true) {
                System.out.println("paso 1");
                socket = server.accept();
                System.out.println("--paso 2");
                GestorConexion.getInstance().conexionNueva(new Conexion(socket));
                Thread.sleep(1000);
                System.out.println("Lista: ");
                
                for(Conexion con : GestorConexion.getInstance().getConexiones()){
                    System.out.println("+"+con.getClienteID());
                }
                socket = null;
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "ERROR AL ABRIR EL PUERTO");
            try {
                server.close();
            } catch (Exception ex1) {
            }
        }

//        try {
//            while (true) {
//                socket = server.accept();
//                in = new ObjectInputStream(socket.getInputStream());
//                //Aqui para el gestor agregar una conexion activa
//                System.out.println("Se ha conectado al servidor ");
//
//            }
//        } catch (Exception ex) {
//            System.out.println("FIn conexion");
//        }
    }
}
