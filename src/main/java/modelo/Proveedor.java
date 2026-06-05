package modelo;

public class Proveedor {

    private String nit;
    private String empresa;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(String nit, String empresa, String telefono) {
        this.nit = nit;
        this.empresa = empresa;
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}