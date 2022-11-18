package models;

public class PedidoString {


    private String id;

    private String fecha;

    private String cliente;

    private String estado;
    
    private String producto;

    public PedidoString() {
    }

    public PedidoString(String id, String fecha, String cliente, String estado, String producto) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.estado = estado;
        this.producto = producto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    
}
