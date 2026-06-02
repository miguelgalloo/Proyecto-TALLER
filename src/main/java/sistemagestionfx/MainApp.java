package sistemagestionfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    "-fx-background-color: linear-gradient(to bottom, #0f172a, #1e293b);"

);Label logo = new Label("🍸 LICORERÍA");
logo.setStyle(
    "-fx-text-fill:white;"
    + "-fx-font-size:26px;"
    + "-fx-font-weight:bold;"
);

Label subtitulo = new Label("Sistema de Gestión");
subtitulo.setStyle(
    "-fx-text-fill:#cbd5e1;"
    + "-fx-font-size:12px;"
);

VBox encabezadoMenu = new VBox(
        5,
        logo,
        subtitulo
);



        Button btnInicio =
        new Button("🏠 Inicio");
        
        Button btnClientes =
                new Button("👥 Clientes");

        Button btnProveedores =
                new Button("🚚 Proveedores");

        Button btnVentas =
                new Button("🛒 Ventas");
        Button btnProductos = 
                new Button("📦 Productos");

        Button btnFinanzas =
                new Button("💰 Finanzas");

        Button btnSalir =
                new Button("⏻ Salir");

        // Tamaño botones
        
        btnInicio.setMaxWidth(Double.MAX_VALUE);
        btnClientes.setMaxWidth(Double.MAX_VALUE);
        btnProveedores.setMaxWidth(Double.MAX_VALUE);
        btnVentas.setMaxWidth(Double.MAX_VALUE);
        btnFinanzas.setMaxWidth(Double.MAX_VALUE);
        btnProductos.setMaxWidth(Double.MAX_VALUE);
        btnSalir.setMaxWidth(Double.MAX_VALUE);

        // Estilo botones

        String estiloBoton =
"-fx-background-color: transparent;"
+ "-fx-text-fill: white;"
+ "-fx-font-size:15px;"
+ "-fx-font-weight:bold;"
+ "-fx-alignment:CENTER_LEFT;"
+ "-fx-padding:15 20 15 20;"
+ "-fx-background-radius:12;";
       
        btnInicio.setStyle(estiloBoton);
        
        btnClientes.setStyle(estiloBoton);
        btnProveedores.setStyle(estiloBoton);
        btnVentas.setStyle(estiloBoton);
        btnFinanzas.setStyle(estiloBoton);
        btnProductos.setStyle(estiloBoton);
        btnSalir.setStyle("-fx-background-color: transparent;"
        + "-fx-text-fill:#ef4444;"
        + "-fx-font-size:15px;"
        + "-fx-font-weight:bold;"
        + "-fx-alignment:CENTER_LEFT;"
        + "-fx-padding:15;"
);

        VBox menuSuperior = new VBox(15);

menuSuperior.getChildren().addAll(
        btnInicio,
        btnClientes,
        btnProveedores,
        btnProductos,
        btnVentas,
        btnFinanzas
);
        Region espacio = new Region();

VBox.setVgrow(
        espacio,
        javafx.scene.layout.Priority.ALWAYS
);
menuLateral.getChildren().addAll(
        encabezadoMenu,
        menuSuperior,
        espacio,
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
       btnProductos.setOnAction(
        e -> mostrarProductos()
);

        btnSalir.setOnAction(
                e -> System.exit(0)
        );

//        // ===== LAYOUT =====
HBox header = new HBox();

header.setStyle(
    "-fx-background-color: white;"
    + "-fx-border-color:#e5e7eb;"
    + "-fx-border-width:0 0 1 0;"
);



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
    + "-fx-background-radius:20;"
    + "-fx-border-radius:20;"
    + "-fx-border-color:#e2e8f0;"
    + "-fx-effect:dropshadow(gaussian,"
    + "rgba(0,0,0,0.08),15,0,0,4);"
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
private void mostrarProductos() {

    workspace.getChildren().clear();

    BorderPane panelPrincipal =
            new BorderPane();

    Label titulo =
            new Label("PRODUCTOS");

    titulo.setStyle(
            "-fx-font-size:24px;"
            + "-fx-font-weight:bold;"
    );

    FlowPane panelProductos =
            new FlowPane();

    panelProductos.setHgap(25);
    panelProductos.setVgap(25);
    panelProductos.setPadding(
            new Insets(20)
    );

    panelProductos.setAlignment(
            javafx.geometry.Pos.CENTER
    );

    ProductoDAO dao =
            new ProductoDAO();

    for (Producto p : dao.leerProductos()) {

        panelProductos.getChildren().add(

                crearCardProducto(
                        p.getNombre(),
                        "$" + p.getPrecio(),
                        p.getCodigo(),
                        p.getImagen()
                )
        );
    }

    ScrollPane scroll =
            new ScrollPane(panelProductos);

    scroll.setFitToWidth(true);

    VBox centro =
            new VBox(20);

    centro.setAlignment(
            javafx.geometry.Pos.TOP_CENTER
    );

    centro.getChildren().addAll(
            titulo,
            scroll
    );

    Button btnAdministrar =
            new Button(
                    "Administrar Productos"
            );
btnAdministrar.setStyle(
    "-fx-background-color:#4f46e5;"
    + "-fx-text-fill:white;"
    + "-fx-font-size:15px;"
    + "-fx-font-weight:bold;"
    + "-fx-background-radius:15;"
    + "-fx-padding:12 20 12 20;"
);
    btnAdministrar.setPrefWidth(180);

    panelPrincipal.setCenter(centro);
    panelPrincipal.setRight(btnAdministrar);

    workspace.getChildren().add(
            panelPrincipal
    );
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