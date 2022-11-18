package models;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;

    private String cliente;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "producto")
    private Producto producto;

    public Pedido() {
        this.fecha = new Date();
        this.estado = "Pendiente";
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", cliente='" + cliente + '\'' +
                ", estado='" + estado + '\'' +
                ", producto=" + producto.getNombre() +
                '}';
    }
}
