package sistemagestionfx;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String ARCHIVO = "clientes.json";

    private Gson gson = new Gson();

    public List<Clientes> leerClientes() {

        try {

            Type tipoLista = new TypeToken<List<Clientes>>() {}.getType();

            FileReader reader = new FileReader(ARCHIVO);

            List<Clientes> lista =
                    gson.fromJson(reader, tipoLista);

            reader.close();

            return lista == null
                    ? new ArrayList<>()
                    : lista;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    public void guardarClientes(List<Clientes> clientes) {

        try {

            FileWriter writer =
                    new FileWriter(ARCHIVO);

            gson.toJson(clientes, writer);

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void agregarCliente(Clientes cliente) {

        List<Clientes> lista = leerClientes();

        lista.add(cliente);

        guardarClientes(lista);
    }
    
    public Clientes buscarCliente(String documento) {

    List<Clientes> lista = leerClientes();

    for (Clientes c : lista) {

        if (c.getDocumento().equals(documento)) {
            return c;
        }
    }

    return null;
}

public boolean actualizarCliente(Clientes clienteActualizado) {

    List<Clientes> lista = leerClientes();

    for (int i = 0; i < lista.size(); i++) {

        if (lista.get(i).getDocumento()
                .equals(clienteActualizado.getDocumento())) {

            lista.set(i, clienteActualizado);

            guardarClientes(lista);

            return true;
        }
    }

    return false;
}

public boolean eliminarCliente(String documento) {

    List<Clientes> lista = leerClientes();

    boolean eliminado = lista.removeIf(
            c -> c.getDocumento().equals(documento)
    );

    if (eliminado) {
        guardarClientes(lista);
    }

    return eliminado;
}
}