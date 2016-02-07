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
        try {
            server = new ServerSocket(Port);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "ERROR AL ABRIR EL PUERTO");
            try {
                server.close();
            } catch (Exception ex1) {
            }
        }

        try {
            while (true) {
                socket = server.accept();
                in = new ObjectInputStream(socket.getInputStream());
                //Aqui para el gestor agregar una conexion activa
                System.out.println("Se ha conectado al servidor ");

                //lectura de objeto recibido
                try {
//                    while (true) {
                        System.out.println("---Entro Al 2do ciclo");
                        recibido = (Object) in.readObject();
                        System.out.println("Leyo el Objecto");
                        if (recibido instanceof Empleado) {
                            Empleado e = (Empleado) recibido;
                            System.out.println("Se recibio un Empleado");
                            System.out.println("nombre: " + e.getNombre());
                            System.out.println("id: " + e.getIdentificacion());
                        } else {
                            System.out.println("Es otro tipo de objeto");
                        }
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("error: "+ e.getMessage());
                    System.err.println("fin conexion----");

                }
            }
        } catch (Exception ex) {
            System.out.println("FIn conexion");
        }
    }
}
