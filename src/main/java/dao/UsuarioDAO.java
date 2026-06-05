package dao;
import modelo.Usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String ARCHIVO =
            "usuario.json";

    private Gson gson = new Gson();

    public List<Usuario> leerUsuarios() {

        try {

            Type tipo =
                    new TypeToken<List<Usuario>>() {}.getType();

            FileReader reader =
                    new FileReader(ARCHIVO);

            List<Usuario> lista =
                    gson.fromJson(reader, tipo);

            reader.close();

            return lista == null
                    ? new ArrayList<>()
                    : lista;

        } catch (Exception e) {

            return new ArrayList<>();
        }
    }

    public Usuario validarLogin(
            String usuario,
            String clave) {

        List<Usuario> usuarios =
                leerUsuarios();

        for (Usuario u : usuarios) {

            if (u.getUsuario().equals(usuario)
                    && u.getClave().equals(clave)) {

                return u;
            }
        }

        return null;
    }
    public void guardarUsuario(Usuario usuario) {

    List<Usuario> usuarios = leerUsuarios();

    usuarios.add(usuario);

    escribirUsuarios(usuarios);
}
    public void escribirUsuarios(
        List<Usuario> usuarios
) {

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    try (FileWriter writer =
            new FileWriter("usuario.json")) {

        gson.toJson(
                usuarios,
                writer
        );

    } catch (IOException e) {

        e.printStackTrace();
    }
}
    
}