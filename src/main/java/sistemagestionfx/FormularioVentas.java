package sistemagestionfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FormularioVentas extends VBox {

    private VentaDAO dao = new VentaDAO();

    public FormularioVentas(StackPane parent) {

        this.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );

        this.getStyleClass().add("form-container");

        this.setSpacing(15);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);
        this.setMaxSize(500, 400);

        Label titulo = new Label("GESTIÓN DE VENTAS");

        titulo.setStyle(
                "-fx-font-size:20px; -fx-font-weight:bold;"
        );

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Label lblCodigo = new Label("Código:");

        TextField txtCodigo = new TextField();

        Label lblProducto = new Label("Producto:");

        TextField txtProducto = new TextField();

        Label lblValor = new Label("Valor:");

        TextField txtValor = new TextField();

        grid.add(lblCodigo, 0, 0);
        grid.add(txtCodigo, 1, 0);

        grid.add(lblProducto, 0, 1);
        grid.add(txtProducto, 1, 1);

        grid.add(lblValor, 0, 2);
        grid.add(txtValor, 1, 2);

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

            if (txtCodigo.getText().isEmpty()
                    || txtProducto.getText().isEmpty()
                    || txtValor.getText().isEmpty()) {

                mostrarMensaje(
                        "Todos los campos son obligatorios"
                );

                return;
            }

            Venta existente =
                    dao.buscarVenta(
                            txtCodigo.getText()
                    );

            if (existente != null) {

                mostrarMensaje(
                        "Ya existe una venta con ese código"
                );

                return;
            }

            try {

                double valor =
                        Double.parseDouble(
                                txtValor.getText()
                        );

                Venta venta =
                        new Venta(
                                txtCodigo.getText(),
                                txtProducto.getText(),
                                valor
                        );

                dao.agregarVenta(venta);

                mostrarMensaje(
                        "Venta guardada correctamente"
                );

                txtCodigo.clear();
                txtProducto.clear();
                txtValor.clear();

            } catch (NumberFormatException ex) {

                mostrarMensaje(
                        "El valor debe ser numérico"
                );
            }
        });

        btnBuscar.setOnAction(e -> {

            Venta venta =
                    dao.buscarVenta(
                            txtCodigo.getText()
                    );

            if (venta != null) {

                txtProducto.setText(
                        venta.getProducto()
                );

                txtValor.setText(
                        String.valueOf(
                                venta.getValor()
                        )
                );

            } else {

                mostrarMensaje(
                        "Venta no encontrada"
                );
            }
        });

        btnActualizar.setOnAction(e -> {

            try {

                double valor =
                        Double.parseDouble(
                                txtValor.getText()
                        );

                Venta venta =
                        new Venta(
                                txtCodigo.getText(),
                                txtProducto.getText(),
                                valor
                        );

                if (dao.actualizarVenta(venta)) {

                    mostrarMensaje(
                            "Venta actualizada"
                    );

                } else {

                    mostrarMensaje(
                            "Venta no encontrada"
                    );
                }

            } catch (NumberFormatException ex) {

                mostrarMensaje(
                        "El valor debe ser numérico"
                );
            }
        });

        btnEliminar.setOnAction(e -> {

            Alert confirmacion =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmacion.setTitle(
                    "Confirmar eliminación"
            );

            confirmacion.setHeaderText(null);

            confirmacion.setContentText(
                    "¿Está seguro de eliminar esta venta?"
            );

            if (confirmacion.showAndWait().get()
                    == ButtonType.OK) {

                if (dao.eliminarVenta(
                        txtCodigo.getText())) {

                    mostrarMensaje(
                            "Venta eliminada"
                    );

                    txtCodigo.clear();
                    txtProducto.clear();
                    txtValor.clear();

                } else {

                    mostrarMensaje(
                            "Venta no encontrada"
                    );
                }
            }
        });

        btnLimpiar.setOnAction(e -> {

            txtCodigo.clear();
            txtProducto.clear();
            txtValor.clear();
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