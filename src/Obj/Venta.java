package Obj;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VENTAS")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Empleado vendedor;
    @OneToOne
    private Cliente cliente = null;
    @Temporal(TemporalType.DATE)
    private Date fecha = new Date();
    @Column
    private boolean Activa = true;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Item> items = new ArrayList<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Abono> abonos = new ArrayList<>();
    @Column
    private double subtotal = 0;
    @Column
    private double saldoPorPagar = 0;

    public Venta(Empleado empl, Cliente clin) {
        this.vendedor = empl;
        this.cliente = clin;
    }

    public Venta(Empleado vendedor) {
        this.vendedor = vendedor;
    }

    public Venta() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendedor(Empleado vendedor) {
        this.vendedor = vendedor;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setActiva(boolean Activa) {
        this.Activa = Activa;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setAbonos(List<Abono> abonos) {
        this.abonos = abonos;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setSaldoPorPagar(double saldoPorPagar) {
        this.saldoPorPagar = saldoPorPagar;
    }

    public Long getId() {
        return id;
    }

    public Empleado getVendedor() {
        return vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public boolean isActiva() {
        return Activa;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Abono> getAbonos() {
        return abonos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getSaldoPorPagar() {
        return saldoPorPagar;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venta other = (Venta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return id + " : " + vendedor + " - " + cliente + " : " + sdf.format(fecha) + " : " + subtotal + " : " + saldoPorPagar;
    }

    public void add(Item item) throws Exception {
        this.items.add(item);
        this.saldoPorPagar = this.subtotal += item.getSubtotal();
    }

    public void add(Abono abono) throws Exception {
        this.abonos.add(abono);
        this.saldoPorPagar -= abono.getTotalAbono();
        if (this.saldoPorPagar <= (double) 0) {
            this.Activa = false;
        }
    }

    public void remove(Item item) throws Exception {
        this.items.remove(item);
        this.subtotal -= item.getSubtotal();
    }

}
