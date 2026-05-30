package sistemagestionfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FormularioProveedor extends VBox {

    private ProveedorDAO dao = new ProveedorDAO();

    public FormularioProveedor(StackPane parent) {

        this.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );

        this.getStyleClass().add("form-container");

        this.setSpacing(15);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(500, 400);

        Label titulo = new Label("GESTIÓN DE PROVEEDORES");

        titulo.setStyle(
                "-fx-font-size:20px; -fx-font-weight:bold;"
        );

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label lblNit = new Label("NIT:");

        TextField txtNit = new TextField();

        Label lblEmpresa = new Label("Empresa:");

        TextField txtEmpresa = new TextField();

        Label lblTelefono = new Label("Teléfono:");

        TextField txtTelefono = new TextField();

        grid.add(lblNit, 0, 0);
        grid.add(txtNit, 1, 0);

        grid.add(lblEmpresa, 0, 1);
        grid.add(txtEmpresa, 1, 1);

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

            if (txtNit.getText().isEmpty()
                    || txtEmpresa.getText().isEmpty()
                    || txtTelefono.getText().isEmpty()) {

                mostrarMensaje(
                        "Todos los campos son obligatorios"
                );

                return;
            }

            Proveedor existente =
                    dao.buscarProveedor(
                            txtNit.getText()
                    );

            if (existente != null) {

                mostrarMensaje(
                        "Ya existe un proveedor con ese NIT"
                );

                return;
            }

            Proveedor proveedor =
                    new Proveedor(
                            txtNit.getText(),
                            txtEmpresa.getText(),
                            txtTelefono.getText()
                    );

            dao.agregarProveedor(proveedor);

            mostrarMensaje(
                    "Proveedor guardado correctamente"
            );

            txtNit.clear();
            txtEmpresa.clear();
            txtTelefono.clear();
        });

        btnBuscar.setOnAction(e -> {

            Proveedor proveedor =
                    dao.buscarProveedor(
                            txtNit.getText()
                    );

            if (proveedor != null) {

                txtEmpresa.setText(
                        proveedor.getEmpresa()
                );

                txtTelefono.setText(
                        proveedor.getTelefono()
                );

            } else {

                mostrarMensaje(
                        "Proveedor no encontrado"
                );
            }
        });

        btnActualizar.setOnAction(e -> {

            Proveedor proveedor =
                    new Proveedor(
                            txtNit.getText(),
                            txtEmpresa.getText(),
                            txtTelefono.getText()
                    );

            if (dao.actualizarProveedor(proveedor)) {

                mostrarMensaje(
                        "Proveedor actualizado"
                );

            } else {

                mostrarMensaje(
                        "Proveedor no encontrado"
                );
            }
        });

        btnEliminar.setOnAction(e -> {

    Alert confirmacion =
            new Alert(Alert.AlertType.CONFIRMATION);

    confirmacion.setTitle("Confirmar eliminación");

    confirmacion.setHeaderText(null);

    confirmacion.setContentText(
            "¿Está seguro de eliminar este proveedor?"
    );

    if (confirmacion.showAndWait().get()
            == ButtonType.OK) {

        if (dao.eliminarProveedor(
                txtNit.getText())) {

            mostrarMensaje(
                    "Proveedor eliminado"
            );

            txtNit.clear();
            txtEmpresa.clear();
            txtTelefono.clear();

        } else {

            mostrarMensaje(
                    "Proveedor no encontrado"
            );
        }
    }
});

        btnLimpiar.setOnAction(e -> {

            txtNit.clear();
            txtEmpresa.clear();
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