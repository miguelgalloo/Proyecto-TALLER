package vista;


import dao.ClienteDAO;
import modelo.Clientes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FormularioCliente extends VBox {

    private ClienteDAO dao = new ClienteDAO();

    public FormularioCliente(StackPane parent) {

        this.getStylesheets().add(
                getClass().getResource("/sistemagestionfx/style.css").toExternalForm()
        );

        this.getStyleClass().add("form-container");

        this.setSpacing(15);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(500, 400);

        Label titulo = new Label("GESTIÓN DE CLIENTES");

        titulo.setStyle(
                "-fx-font-size:20px; -fx-font-weight:bold;"
        );

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label lblDocumento = new Label("Documento:");

        TextField txtDocumento = new TextField();

        Label lblNombre = new Label("Nombre:");

        TextField txtNombre = new TextField();

        Label lblTelefono = new Label("Teléfono:");

        TextField txtTelefono = new TextField();

        grid.add(lblDocumento, 0, 0);
        grid.add(txtDocumento, 1, 0);

        grid.add(lblNombre, 0, 1);
        grid.add(txtNombre, 1, 1);

        grid.add(lblTelefono, 0, 2);
        grid.add(txtTelefono, 1, 2);

        Button btnGuardar = new Button("Guardar");
        Button btnBuscar = new Button("Buscar");
        Button btnActualizar = new Button("Actualizar");
        Button btnEliminar = new Button("Eliminar");
        Button btnLimpiar = new Button("Limpiar");
        Button btnSalir = new Button("Salir");

        btnGuardar.getStyleClass().add("btn-entrar");
        btnActualizar.getStyleClass().add("btn-entrar");

        btnEliminar.getStyleClass().add("btn-salir");
        btnSalir.getStyleClass().add("btn-salir");

        btnGuardar.setOnAction(e -> {

    if (txtDocumento.getText().isEmpty()
            || txtNombre.getText().isEmpty()
            || txtTelefono.getText().isEmpty()) {

        mostrarMensaje("Todos los campos son obligatorios");
        return;
    }

    Clientes existente =
            dao.buscarCliente(txtDocumento.getText());

    if (existente != null) {

        mostrarMensaje(
                "Ya existe un cliente con ese documento"
        );

        return;
    }

    Clientes cliente = new Clientes(
            txtDocumento.getText(),
            txtNombre.getText(),
            txtTelefono.getText()
    );

    dao.agregarCliente(cliente);

    mostrarMensaje(
            "Cliente guardado correctamente"
    );

    txtDocumento.clear();
    txtNombre.clear();
    txtTelefono.clear();
});

        btnBuscar.setOnAction(e -> {

            Clientes cliente =
                    dao.buscarCliente(
                            txtDocumento.getText()
                    );

            if (cliente != null) {

                txtNombre.setText(
                        cliente.getNombre()
                );

                txtTelefono.setText(
                        cliente.getTelefono()
                );

            } else {

                mostrarMensaje(
                        "Cliente no encontrado"
                );
            }
        });

        btnActualizar.setOnAction(e -> {

            Clientes cliente =
                    new Clientes(
                            txtDocumento.getText(),
                            txtNombre.getText(),
                            txtTelefono.getText()
                    );

            if (dao.actualizarCliente(cliente)) {

                mostrarMensaje(
                        "Cliente actualizado"
                );

            } else {

                mostrarMensaje(
                        "Cliente no encontrado"
                );
            }
        });

        btnEliminar.setOnAction(e -> {

    Alert confirmacion =
            new Alert(Alert.AlertType.CONFIRMATION);

    confirmacion.setTitle("Confirmar eliminación");

    confirmacion.setHeaderText(null);

    confirmacion.setContentText(
            "¿Está seguro de eliminar este cliente?"
    );

    if (confirmacion.showAndWait().get()
            == ButtonType.OK) {

        if (dao.eliminarCliente(
                txtDocumento.getText())) {

            mostrarMensaje(
                    "Cliente eliminado"
            );

            txtDocumento.clear();
            txtNombre.clear();
            txtTelefono.clear();

        } else {

            mostrarMensaje(
                    "Cliente no encontrado"
            );
        }
    }
});

        btnLimpiar.setOnAction(e -> {

            txtDocumento.clear();
            txtNombre.clear();
            txtTelefono.clear();
        });

        btnSalir.setOnAction(
                e -> parent.getChildren().remove(this)
        );

        HBox fila1 = new HBox(
                10,
                btnGuardar,
                btnBuscar,
                btnActualizar
        );

        fila1.setAlignment(Pos.CENTER);

        HBox fila2 = new HBox(
                10,
                btnEliminar,
                btnLimpiar,
                btnSalir
        );

        fila2.setAlignment(Pos.CENTER);

        this.getChildren().addAll(
                titulo,
                grid,
                fila1,
                fila2
        );
    }

    private void mostrarMensaje(String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}