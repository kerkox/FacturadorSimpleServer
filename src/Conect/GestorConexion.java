package Conect;

import java.util.ArrayList;

/**
 *
 * @author Paulker
 */
public class GestorConexion {
    private static GestorConexion singleton = new GestorConexion();
    
    
    public static GestorConexion getInstance(){
        return singleton;
    }
    
    
    private ArrayList<Conexion> conexiones = new ArrayList();
    
    /**
     * Este se usara para enviar mensajes a los clientes
     */
    public void enviarTrama(int Codigo, String Trama){
        for(Conexion conex : this.conexiones){
            //agregado de enviar trama a cada uno
        }
    }

    public ArrayList<Conexion> getConexiones() {
        return conexiones;
    }
    
    
    
    public void conexionNueva(Conexion nueva){
        this.conexiones.add(nueva);
       
    }
    
    public void desconecta(Conexion eliminar){
        //Elimina la conexion de la lista del gestor de conexiones al server
        this.conexiones.remove(this.conexiones.indexOf(eliminar));
    }
}
