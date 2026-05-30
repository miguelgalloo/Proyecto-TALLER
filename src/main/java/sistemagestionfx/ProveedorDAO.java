package sistemagestionfx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    private static final String ARCHIVO = "proveedores.json";

    private Gson gson = new Gson();

    public List<Proveedor> leerProveedores() {

        try {

            Type tipoLista =
                    new TypeToken<List<Proveedor>>() {}.getType();

            FileReader reader =
                    new FileReader(ARCHIVO);

            List<Proveedor> lista =
                    gson.fromJson(reader, tipoLista);

            reader.close();

            return lista == null
                    ? new ArrayList<>()
                    : lista;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    public void guardarProveedores(
            List<Proveedor> proveedores) {

        try {

            FileWriter writer =
                    new FileWriter(ARCHIVO);

            gson.toJson(proveedores, writer);

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void agregarProveedor(
            Proveedor proveedor) {

        List<Proveedor> lista =
                leerProveedores();

        lista.add(proveedor);

        guardarProveedores(lista);
    }
    
    public Proveedor buscarProveedor(String nit) {

    List<Proveedor> lista = leerProveedores();

    for (Proveedor p : lista) {

        if (p.getNit().equals(nit)) {
            return p;
        }
    }

    return null;
}

public boolean actualizarProveedor(
        Proveedor proveedorActualizado) {

    List<Proveedor> lista = leerProveedores();

    for (int i = 0; i < lista.size(); i++) {

        if (lista.get(i).getNit()
                .equals(proveedorActualizado.getNit())) {

            lista.set(i, proveedorActualizado);

            guardarProveedores(lista);

            return true;
        }
    }

    return false;
}

public boolean eliminarProveedor(String nit) {

    List<Proveedor> lista = leerProveedores();

    boolean eliminado = lista.removeIf(
            p -> p.getNit().equals(nit)
    );

    if (eliminado) {

        guardarProveedores(lista);
    }

    return eliminado;
}
}