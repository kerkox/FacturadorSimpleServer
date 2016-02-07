package Obj;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADOS")
public class Empleado extends Persona implements Serializable {

    @Column(length = 255, nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean Administrator = false;

    public Empleado(String id, String nom, String tel, String dir, String password, boolean admin) throws Exception {
        super(id, nom, tel, dir);
        this.password = password;
        this.Administrator = admin;
    }

    public Empleado(String id, String nombre, String password, boolean admin) throws Exception {
        super(id, nombre);
        this.password = password;
        this.Administrator = admin;
    }

    public Empleado(String id, String nombre, String contraseña) throws Exception {
        super(id, nombre);
        this.password = contraseña;
    }

    public Empleado(String id, String nombre) throws Exception {
        super(id, nombre);
    }

    public Empleado() {
    }

    public void setIsAdmin(boolean Administrator) {
        this.Administrator = Administrator;
    }

    public void setPassword(String pass) throws Exception {
        if (pass.length() < 3) {
            throw new Exception("Contraseña debil");
        }
        this.password = pass;
    }

    public boolean isAdministrator() {
        return Administrator;
    }

    public String getPassword() {
        return password;
    }

}
