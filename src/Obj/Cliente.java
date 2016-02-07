package Obj;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTES")
public class Cliente extends Persona implements Serializable {

    public Cliente(String id, String nom, String tel, String dir) throws Exception {
        super(id, nom, tel, dir);
    }

    public Cliente(String id, String nombre) throws Exception {
        super(id, nombre);
    }

    public Cliente() {
        super();
    }

}
