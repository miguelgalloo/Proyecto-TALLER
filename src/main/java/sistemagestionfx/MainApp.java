package sistemagestionfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private StackPane workspace;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        workspace = new StackPane();
        workspace.setStyle("-fx-background-color: #f4f4f4;");

        // ===== MENÚ LATERAL =====

        VBox menuLateral = new VBox(15);

        menuLateral.setPadding(new Insets(20));
        menuLateral.setPrefWidth(220);

        menuLateral.setStyle(
    "-fx-background-color: #1e272e;"
);

        Button btnInicio =
        new Button("🏠 Inicio");
        
        Button btnClientes =
                new Button("Clientes");

        Button btnProveedores =
                new Button("Proveedores");

        Button btnVentas =
                new Button("Ventas");

        Button btnFinanzas =
                new Button("Finanzas");

        Button btnSalir =
                new Button("Salir");

        // Tamaño botones
        
        btnInicio.setMaxWidth(Double.MAX_VALUE);
        btnClientes.setMaxWidth(Double.MAX_VALUE);
        btnProveedores.setMaxWidth(Double.MAX_VALUE);
        btnVentas.setMaxWidth(Double.MAX_VALUE);
        btnFinanzas.setMaxWidth(Double.MAX_VALUE);
        btnSalir.setMaxWidth(Double.MAX_VALUE);

        // Estilo botones

        String estiloBoton =
    "-fx-background-color:#34495e;"
    + "-fx-text-fill:white;"
    + "-fx-font-size:14px;"
    + "-fx-pref-height:45px;"
    + "-fx-background-radius:8;";
       
        btnInicio.setStyle(estiloBoton);
        btnClientes.setStyle(estiloBoton);
        btnProveedores.setStyle(estiloBoton);
        btnVentas.setStyle(estiloBoton);
        btnFinanzas.setStyle(estiloBoton);
        btnSalir.setStyle(estiloBoton);

        menuLateral.getChildren().addAll(
                btnInicio,
                btnClientes,
                btnProveedores,
                btnVentas,
                btnFinanzas,
                btnSalir
        );

        // ===== EVENTOS =====
        
btnInicio.setOnAction(
        e -> mostrarInicio()
);
        btnClientes.setOnAction(
                e -> abrirFormularioCliente()
        );

        btnProveedores.setOnAction(
                e -> abrirFormularioProveedor()
        );

        btnVentas.setOnAction(
                e -> abrirFormularioVentas()
        );

        btnFinanzas.setOnAction(
                e -> abrirFormularioFinanzas()
        );

        btnSalir.setOnAction(
                e -> System.exit(0)
        );

        // ===== LAYOUT =====
HBox header = new HBox();

header.setStyle(
    "-fx-background-color: #1e272e;"
);

header.setPrefHeight(70);

Label tituloSistema =
        new Label("LICORERÍA");

tituloSistema.setStyle(
    "-fx-text-fill: white;"
    + "-fx-font-size: 28px;"
    + "-fx-font-weight: bold;"
);

header.getChildren().add(tituloSistema);

header.setAlignment(javafx.geometry.Pos.CENTER);

root.setTop(header);
        root.setLeft(menuLateral);
        root.setCenter(workspace);

        Scene scene =
                new Scene(root, 1200, 700);

        primaryStage.setTitle(
                "Sistema de Gestión de Licorería"
        );

        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> {

            event.consume();

            Alert alert =
                    new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Aviso");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Debe salir desde el botón Salir."
            );

            alert.showAndWait();
        });
mostrarInicio();
        primaryStage.show();
    }

    // ===== CLIENTE =====

    private void abrirFormularioCliente() {

        workspace.getChildren().clear();

        FormularioCliente form =
                new FormularioCliente(workspace);

        workspace.getChildren().add(form);
    }

    // ===== PROVEEDOR =====

    private void abrirFormularioProveedor() {

        workspace.getChildren().clear();

        FormularioProveedor form =
                new FormularioProveedor(workspace);

        workspace.getChildren().add(form);
    }
    private void mostrarInicio() {

    workspace.getChildren().clear();

    VBox contenedor = new VBox(30);

    contenedor.setAlignment(
            javafx.geometry.Pos.CENTER
    );

    Label bienvenida =
            new Label(
                    "Bienvenido al Sistema"
            );

    bienvenida.setStyle(
            "-fx-font-size: 24px;"
            + "-fx-font-weight: bold;"
    );

    HBox filaProductos =
            new HBox(25);

    filaProductos.setAlignment(
            javafx.geometry.Pos.CENTER
    );

    filaProductos.getChildren().addAll(

            crearCardProducto(
                    "Aguila",
                    "$4.000",
                    "00023",
                    "/sistemagestionfx/images/aguila.png"
            ),

            crearCardProducto(
                    "Vino",
                    "$35.000",
                    "00549",
                    "/sistemagestionfx/images/vino.png"
            ),

            crearCardProducto(
                    "Old Parr",
                    "$120.000",
                    "00590",
                    "/sistemagestionfx/images/oldparr.png"
            ),

            crearCardProducto(
                    "Ron Medellín",
                    "$80.000",
                    "00567",
                    "/sistemagestionfx/images/ron.png"
            )
    );

    contenedor.getChildren().addAll(
            bienvenida,
            filaProductos
    );

    workspace.getChildren().add(contenedor);
}
    private VBox crearCardProducto(
        String nombre,
        String precio,
        String codigo,
        String rutaImagen) {
        Button btnComprar = new Button("Comprar");

btnComprar.setOnAction(e -> {
    abrirFormularioVentas();
});

    VBox card = new VBox(10);

    card.setAlignment(
            javafx.geometry.Pos.CENTER
    );

    card.setPrefSize(180, 250);

    card.setStyle(
            "-fx-background-color:white;"
            + "-fx-background-radius:15;"
            + "-fx-border-radius:15;"
            + "-fx-border-color:#dfe6e9;"
            + "-fx-padding:15;"
    );

    javafx.scene.image.ImageView imagen =
            new javafx.scene.image.ImageView(
                    new javafx.scene.image.Image(
                            getClass()
                                    .getResourceAsStream(
                                            rutaImagen
                                    )
                    )
            );

    imagen.setFitWidth(100);
    imagen.setFitHeight(130);
//Label imagen = new Label("SIN IMAGEN");

    Label lblNombre =
            new Label(nombre);

    lblNombre.setStyle(
            "-fx-font-size:16px;"
            + "-fx-font-weight:bold;"
    );
    Label lblCodigo =
            new Label(codigo);

    Label lblPrecio =
            new Label(precio);

    card.getChildren().addAll(
            imagen,
            lblNombre,
            lblCodigo,
            lblPrecio,
            btnComprar
    );

    return card;
}

    // ===== VENTAS =====

    private void abrirFormularioVentas() {

        workspace.getChildren().clear();

        FormularioVentas form =
                new FormularioVentas(workspace);

        workspace.getChildren().add(form);
    }

    // ===== FINANZAS =====

    private void abrirFormularioFinanzas() {

        workspace.getChildren().clear();

        FormularioFinanzas form =
                new FormularioFinanzas(workspace);

        workspace.getChildren().add(form);
    }

    public static void main(String[] args) {

        launch(args);
    }
}