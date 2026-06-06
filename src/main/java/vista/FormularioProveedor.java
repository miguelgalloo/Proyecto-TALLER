package vista;

import dao.ProveedorDAO;
import modelo.Proveedor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class FormularioProveedor extends VBox {

    private ProveedorDAO dao = new ProveedorDAO();

    public FormularioProveedor(StackPane parent) {

        this.getStylesheets().add(
                getClass().getResource("/sistemagestionfx/style.css").toExternalForm()
        );

        this.getStyleClass().add("form-container");

        this.setSpacing(15);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.setMaxWidth(850);

this.setPrefHeight(450);


ImageView iconoTitulo = new ImageView(
        new Image(
                getClass()
                .getResourceAsStream("/sistemagestionfx/images/icono1.png")
        )
);

iconoTitulo.setFitWidth(40);
iconoTitulo.setFitHeight(35);

        Label titulo = new Label("GESTIÓN DE PROVEDORES");
titulo.getStyleClass().add("titulo-formulario");

HBox encabezado = new HBox(10);

encabezado.setAlignment(Pos.CENTER);

encabezado.getChildren().addAll(
        iconoTitulo,
        titulo
);


        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label lblNit = new Label("NIT:");
        lblNit.getStyleClass().add("label-form");

        TextField txtNit = new TextField();

        Label lblEmpresa = new Label("Empresa:");
        lblEmpresa.getStyleClass().add("label-form");

        TextField txtEmpresa = new TextField();

        Label lblTelefono = new Label("Teléfono:");
        lblTelefono.getStyleClass().add("label-form");

        TextField txtTelefono = new TextField();
        txtEmpresa.getStyleClass().add("campo-texto");
        txtNit.getStyleClass().add("campo-texto");
        txtTelefono.getStyleClass().add("campo-texto");

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

        btnGuardar.getStyleClass().add("btn-guardar");
        btnActualizar.getStyleClass().add("btn-actualizar");
        btnBuscar.getStyleClass().add("btn-secundario");
        btnLimpiar.getStyleClass().add("btn-secundario");

        btnEliminar.getStyleClass().add("btn-eliminar");

        btnGuardar.setOnAction(e -> {

            if (txtNit.getText().isEmpty()
                    || txtEmpresa.getText().isEmpty()
                    || txtTelefono.getText().isEmpty()) {

                mostrarMensaje(
                        "Todos los campos son obligatorios"
                );

                return;
            }

            Proveedor existente
                    = dao.buscarProveedor(
                            txtNit.getText()
                    );

            if (existente != null) {

                mostrarMensaje(
                        "Ya existe un proveedor con ese NIT"
                );

                return;
            }

            Proveedor proveedor
                    = new Proveedor(
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

            Proveedor proveedor
                    = dao.buscarProveedor(
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

            Proveedor proveedor
                    = new Proveedor(
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

            Alert confirmacion
                    = new Alert(Alert.AlertType.CONFIRMATION);

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
                btnLimpiar
        );

        fila2.setAlignment(Pos.CENTER);

        this.getChildren().addAll(
                encabezado,
                grid,
                fila1,
                fila2
        );
    }

    private void mostrarMensaje(String mensaje) {

        Alert alert
                = new Alert(Alert.AlertType.INFORMATION);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}
