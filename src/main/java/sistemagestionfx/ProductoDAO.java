package sistemagestionfx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String ARCHIVO = "productos.json";

    private Gson gson = new Gson();

    public List<Producto> leerProductos() {

        try {

            Type tipoLista =
                    new TypeToken<List<Producto>>() {}.getType();

            FileReader reader =
                    new FileReader(ARCHIVO);

            List<Producto> lista =
                    gson.fromJson(reader, tipoLista);

            reader.close();

            return lista == null
                    ? new ArrayList<>()
                    : lista;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    public void guardarProductos(List<Producto> ventas) {

        try {

            FileWriter writer =
                    new FileWriter(ARCHIVO);

            gson.toJson(ventas, writer);

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void agregarProducto(Producto Producto) {

        List<Producto> lista =
                leerProductos();

        lista.add(Producto);

        guardarProductos(lista);
    }
    
    public Producto buscarProducto(String codigo) {

    List<Producto> lista = leerProductos();

    for (Producto p : lista) {

        if (p.getCodigo().equals(codigo)) {
            return p;
        }
    }

    return null;
}

public boolean actualizarProducto(Producto productoActualizada) {

    List<Producto> lista = leerProductos();

    for (int i = 0; i < lista.size(); i++) {

        if (lista.get(i).getCodigo()
                .equals(productoActualizada.getCodigo())) {

            lista.set(i, productoActualizada);

            guardarProductos(lista);

            return true;
        }
    }

    return false;
}
public List<Producto> obtenerTodasLosProductos() {
    return leerProductos();
}

public boolean eliminarProducto(String codigo) {

    List<Producto> lista = leerProductos();

    boolean eliminado = lista.removeIf(
            v -> v.getCodigo().equals(codigo)
    );

    if (eliminado) {

        guardarProductos(lista);
    }

    return eliminado;
}
}