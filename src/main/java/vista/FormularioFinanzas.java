package vista;


import dao.FinanzasDAO;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FormularioFinanzas extends VBox {

    public FormularioFinanzas(StackPane parent) {

        this.getStylesheets().add(
                getClass().getResource("/sistemagestionfx/style.css").toExternalForm()
        );

        this.setSpacing(15);
        this.setPadding(new Insets(30));
        this.setAlignment(Pos.CENTER);

        Label titulo =
                new Label("REPORTE FINANCIERO");

        titulo.getStyleClass().add("titulo-formulario");

        Label lblTotal = new Label();

        Label lblCantidad = new Label();

        Label lblPromedio = new Label();
        lblTotal.getStyleClass().add("dato-finanza");
        lblCantidad.getStyleClass().add("dato-finanza");
        lblPromedio.getStyleClass().add("dato-finanza");

        FinanzasDAO dao = new FinanzasDAO();

        Runnable actualizar = () -> {

            lblTotal.setText(
                    "Total Ventas: $"
                    + dao.totalVentas()
            );

            lblCantidad.setText(
                    "Cantidad Ventas: "
                    + dao.cantidadVentas()
            );

            lblPromedio.setText(
                    "Promedio Venta: $"
                    + dao.promedioVentas()
            );
        };

        actualizar.run();
        VBox tarjeta = new VBox(15);

        tarjeta.getStyleClass().add("card-finanzas");

        tarjeta.getChildren().addAll(
                lblTotal,
                lblCantidad,
                lblPromedio
        );

        Button btnActualizar =
                new Button("Actualizar");

        btnActualizar.setOnAction(
                e -> actualizar.run()
        );

        Button btnSalir =
                new Button("Salir");

        btnSalir.setOnAction(
                e -> parent.getChildren().remove(this)
        );

        HBox botones
                = new HBox(10,
                        btnActualizar,
                        btnSalir);

        botones.setAlignment(Pos.CENTER);

        this.getChildren().addAll(
        titulo,
        tarjeta,
        botones
);
    }}