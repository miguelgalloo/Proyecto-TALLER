package dao;
import modelo.Venta;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    private static final String ARCHIVO = "ventas.json";

    private Gson gson = new Gson();

    public List<Venta> leerVentas() {

        try {

            Type tipoLista =
                    new TypeToken<List<Venta>>() {}.getType();

            FileReader reader =
                    new FileReader(ARCHIVO);

            List<Venta> lista =
                    gson.fromJson(reader, tipoLista);

            reader.close();

            return lista == null
                    ? new ArrayList<>()
                    : lista;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    public void guardarVentas(List<Venta> ventas) {

        try {

            FileWriter writer =
                    new FileWriter(ARCHIVO);

            gson.toJson(ventas, writer);

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void agregarVenta(Venta venta) {

        List<Venta> lista =
                leerVentas();

        lista.add(venta);

        guardarVentas(lista);
    }
    
    public Venta buscarVenta(String codigo) {

    List<Venta> lista = leerVentas();

    for (Venta v : lista) {

        if (v.getCodigo().equals(codigo)) {
            return v;
        }
    }

    return null;
}

public boolean actualizarVenta(Venta ventaActualizada) {

    List<Venta> lista = leerVentas();

    for (int i = 0; i < lista.size(); i++) {

        if (lista.get(i).getCodigo()
                .equals(ventaActualizada.getCodigo())) {

            lista.set(i, ventaActualizada);

            guardarVentas(lista);

            return true;
        }
    }

    return false;
}
public List<Venta> obtenerTodasLasVentas() {
    return leerVentas();
}

public boolean eliminarVenta(String codigo) {

    List<Venta> lista = leerVentas();

    boolean eliminado = lista.removeIf(
            v -> v.getCodigo().equals(codigo)
    );

    if (eliminado) {

        guardarVentas(lista);
    }

    return eliminado;
}
}