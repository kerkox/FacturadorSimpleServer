package Obj;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Persona implements Serializable {

    @Id
    private String identificacion;
    @Column(length = 50, nullable = false)
    private String nombre;
    @Column(length = 255, nullable = true)
    private String telefono;
    @Column(length = 255, nullable = true)
    private String direccion;

    public Persona(String id, String nom, String tel, String dir) throws Exception {
        if (id.equals("".trim()) || nom.equals("".trim())) {
            throw new Exception("No se permiten parametros vacios");
        }
        this.identificacion = id;
        this.nombre = nom;
        this.telefono = tel;
        this.direccion = dir;
    }

    public Persona(String id, String nombre) throws Exception {
        if (id.equals("".trim()) || nombre.equals("".trim())) {
            throw new Exception("No se permiten parametros vacios");
        }
        this.identificacion = id;
        this.nombre = nombre;
    }

    public Persona() {
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identificacion == null) ? 0 : identificacion.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Persona other = (Persona) obj;
        if (identificacion == null) {
            if (other.identificacion != null) {
                return false;
            }
        } else if (!identificacion.equals(other.identificacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return identificacion + " - " + nombre;
    }

}
