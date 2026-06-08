package modelo;

public class DetalleFactura {

    private String codigo;
    private String producto;
    private double precio;
    private int cantidad;
    private double subtotal;

    public DetalleFactura() {
    }

    public DetalleFactura(String codigo,
                          String producto,
                          double precio,
                          int cantidad) {

        this.codigo = codigo;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = precio * cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
}