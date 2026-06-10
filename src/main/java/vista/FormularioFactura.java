package vista;

import app.MainApp;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.DetalleFactura;
import dao.ProductoDAO;
import modelo.Producto;
import dao.VentaDAO;
import modelo.Venta;
import javafx.scene.control.*;

public class FormularioFactura extends BorderPane {

    public FormularioFactura(StackPane parent) {

        setPadding(new Insets(20));

        // ===== ENCABEZADO =====

        VBox encabezado = new VBox(5);

        encabezado.setAlignment(Pos.CENTER);

        Label empresa =
                new Label("LICORERÍA");

        empresa.setStyle(
                "-fx-font-size:24px;"
                + "-fx-font-weight:bold;"
        );

        Label titulo =
                new Label("FACTURA DE VENTA");

        titulo.setStyle(
                "-fx-font-size:18px;"
                + "-fx-font-weight:bold;"
        );

        Label fecha =
                new Label(
                        "Fecha: "
                        + LocalDate.now()
                );

        encabezado.getChildren().addAll(
                empresa,
                titulo,
                fecha
        );

        setTop(encabezado);

        // ===== TABLA =====

        TableView<DetalleFactura> tabla =
                new TableView<>();

        TableColumn<DetalleFactura, Integer> colCantidad =
                new TableColumn<>("Cantidad");

        colCantidad.setCellValueFactory(
                new PropertyValueFactory<>("cantidad")
        );

        TableColumn<DetalleFactura, String> colProducto =
                new TableColumn<>("Producto");

        colProducto.setCellValueFactory(
                new PropertyValueFactory<>("producto")
        );

        TableColumn<DetalleFactura, Double> colPrecio =
                new TableColumn<>("Precio Unit.");

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        TableColumn<DetalleFactura, Double> colSubtotal =
                new TableColumn<>("Importe");

        colSubtotal.setCellValueFactory(
                new PropertyValueFactory<>("subtotal")
        );

        tabla.getColumns().addAll(
                colCantidad,
                colProducto,
                colPrecio,
                colSubtotal
        );

        tabla.setItems(
                FXCollections.observableArrayList(
                        MainApp.carrito
                )
        );

        setCenter(tabla);

        // ===== TOTAL =====

        double total = 0;

        for (DetalleFactura d : MainApp.carrito) {

            total += d.getSubtotal();
        }

        Label lblTotal =
                new Label(
                        "TOTAL: $"
                        + total
                );

        lblTotal.setStyle(
                "-fx-font-size:20px;"
                + "-fx-font-weight:bold;"
        );

        // ===== BOTONES =====

        Button btnSeguir =
                new Button(
                        "Seguir Comprando"
                );

        Button btnFinalizar =
                new Button(
                        "Finalizar Compra"
                );

        btnSeguir.getStyleClass()
                .add("btn-entrar");

        btnFinalizar.getStyleClass()
                .add("btn-salir");

        btnSeguir.setOnAction(e -> {

            MainApp.instancia.mostrarProductos();
        });

        btnFinalizar.setOnAction(e -> {

    ProductoDAO dao = new ProductoDAO();
    VentaDAO ventaDAO = new VentaDAO();

    for (DetalleFactura item : MainApp.carrito) {

    Producto producto =
            dao.buscarProducto(
                    item.getCodigo()
            );

    if (producto != null) {

        producto.setStock(
                producto.getStock()
                - item.getCantidad()
        );

        dao.actualizarProducto(
                producto
        );

        Venta venta =
                new Venta(
                        item.getCodigo(),
                        item.getProducto(),
                        item.getSubtotal()
                );

        ventaDAO.agregarVenta(
                venta
        );
    }
}

    MainApp.carrito.clear();

    parent.getChildren().clear();

    Label mensaje =
            new Label(
                    "Compra Finalizada"
            );

    mensaje.setStyle(
            "-fx-font-size:24px;"
            + "-fx-font-weight:bold;"
    );

    parent.getChildren().add(
            mensaje
    );
});

        HBox botones =
                new HBox(
                        15,
                        btnSeguir,
                        btnFinalizar
                );

        botones.setAlignment(
                Pos.CENTER
        );

        VBox pie =
                new VBox(
                        15,
                        lblTotal,
                        botones
                );

        pie.setAlignment(
                Pos.CENTER_RIGHT
        );

        pie.setPadding(
                new Insets(20)
        );

        setBottom(pie);
    }
}