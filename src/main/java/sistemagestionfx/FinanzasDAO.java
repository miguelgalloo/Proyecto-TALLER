package sistemagestionfx;

import java.util.List;

public class FinanzasDAO {

    private VentaDAO ventaDAO = new VentaDAO();

    public double totalVentas() {

        List<Venta> ventas = ventaDAO.leerVentas();

        double total = 0;

        for (Venta v : ventas) {
            total += v.getValor();
        }

        return total;
    }

    public int cantidadVentas() {

        return ventaDAO.leerVentas().size();
    }

    public double promedioVentas() {

        int cantidad = cantidadVentas();

        if (cantidad == 0) {
            return 0;
        }

        return totalVentas() / cantidad;
    }
}