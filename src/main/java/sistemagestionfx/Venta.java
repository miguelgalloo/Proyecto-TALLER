package sistemagestionfx;

public class Venta {

    private String codigo;
    private String producto;
    private double valor;

    public Venta() {
    }

    public Venta(String codigo, String producto, double valor) {
        this.codigo = codigo;
        this.producto = producto;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}