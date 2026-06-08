package app;



import modelo.Usuario;
import modelo.Producto;
import vista.FormularioCliente;
import vista.FormularioFinanzas;
import vista.FormularioLogin;

import vista.FormularioProductos;
import vista.FormularioProveedor;
import vista.FormularioRegistro;
import vista.FormularioFactura;
import vista.FormularioVentas;
import dao.ProductoDAO;
import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleFactura;

public class MainApp extends Application {
    
    
    public static MainApp instancia;

     public static List<DetalleFactura> carrito =
        new ArrayList<>();
     
    private BorderPane root;

    private StackPane workspace;

    private VBox menuLateral;
    private Label nombreUsuario;

    private Usuario usuarioActual;
    private Button btnInicio;
    private Button btnClientes;
    private Button btnProveedores;
    private Button btnProductos;
    private Button btnVentas;
    private Button btnFinanzas;
    private Button btnSalir;
    public static String codigoSeleccionado;
    public static String productoSeleccionado;
    public static double precioSeleccionado;
   

    public Usuario getUsuarioActual() {
    return usuarioActual;
}
    
    @Override
    public void start(Stage primaryStage) {
        instancia = this;
        root = new BorderPane();

        workspace = new StackPane();
        workspace.getStyleClass().add("workspace");

        // ===== MENÚ LATERAL =====

        menuLateral = new VBox(15);

        menuLateral.setPadding(new Insets(20));
        menuLateral.setPrefWidth(220);

        menuLateral.getStyleClass().add("menu-lateral");
       ImageView logoImg = new ImageView(
        new Image("/sistemagestionfx/images/logo.png")
);

logoImg.setFitWidth(40);
logoImg.setFitHeight(40);

Label logo = new Label("LICORERÍA");

logo.getStyleClass().add("logo-menu");

HBox encabezadoLogo = new HBox(
        10,
        logoImg,
        logo
);

encabezadoLogo.setAlignment(Pos.CENTER_LEFT);

Label subtitulo = new Label("Sistema de Gestión");
subtitulo.getStyleClass().add("subtitulo-menu");

VBox encabezadoMenu = new VBox(
        5,
        encabezadoLogo,
        subtitulo
);



        btnInicio =
        new Button("🏠 Inicio");
        
        btnClientes =
                new Button("👥 Clientes");

        btnProveedores =
                new Button("🚚 Proveedores");

        btnVentas =
                new Button("🛒 Ventas");
        btnProductos = 
                new Button("📦 Productos");

        btnFinanzas =
                new Button("💰 Finanzas");

        btnSalir =
                new Button("✖︎ Salir");

        // Tamaño botones
        
        btnInicio.setMaxWidth(Double.MAX_VALUE);
        btnClientes.setMaxWidth(Double.MAX_VALUE);
        btnProveedores.setMaxWidth(Double.MAX_VALUE);
        btnVentas.setMaxWidth(Double.MAX_VALUE);
        btnFinanzas.setMaxWidth(Double.MAX_VALUE);
        btnProductos.setMaxWidth(Double.MAX_VALUE);
        btnSalir.setMaxWidth(Double.MAX_VALUE);


       
        btnInicio.getStyleClass().add("menu-btn");
        
        btnClientes.getStyleClass().add("menu-btn");
        btnProveedores.getStyleClass().add("menu-btn");
        btnVentas.getStyleClass().add("menu-btn");
        btnFinanzas.getStyleClass().add("menu-btn");
        btnProductos.getStyleClass().add("menu-btn");
        btnSalir.getStyleClass().add("btn-salir");

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

        btnVentas.setOnAction(e -> {

    if (usuarioActual.getRol()
            .equalsIgnoreCase("CLIENTE")) {

        if (MainApp.carrito.isEmpty()) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setHeaderText(null);

            alert.setContentText(
                    "No hay productos en la factura."
            );

            alert.showAndWait();

            return;
        }

        workspace.getChildren().clear();

        workspace.getChildren().add(
                new FormularioFactura(workspace)
        );

    } else {

        abrirFormularioVentas();
    }
});

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

header.setPadding(new Insets(15,25,15,25));

header.setAlignment(Pos.CENTER_RIGHT);

header.setSpacing(15);

header.getStyleClass().add("header");

Label iconoUsuario =
        new Label("👤");

iconoUsuario.getStyleClass()
        .add("icono-usuario");
nombreUsuario = new Label("Invitado");

nombreUsuario.getStyleClass()
        .add("nombre-usuario");
nombreUsuario.getStyleClass()
        .add("nombre-usuario");

HBox usuarioBox =
        new HBox(
            10,
            iconoUsuario,
            nombreUsuario
        );

usuarioBox.setAlignment(Pos.CENTER);

Label tituloSistema =
        new Label("LICORERÍA");

tituloSistema.getStyleClass().add("titulo-sistema");

Region espacioHeader = new Region();

HBox.setHgrow(
        espacioHeader,
        Priority.ALWAYS
);

header.getChildren().addAll(
        tituloSistema,
        espacioHeader,
        usuarioBox
);

header.setAlignment(javafx.geometry.Pos.CENTER);

root.setTop(header);
        //root.setLeft(menuLateral);
        //root.setCenter(workspace);

        Scene scene =
                new Scene(root, 1200, 700);
        scene.getStylesheets().add(
        getClass()
                .getResource("/sistemagestionfx/inicio.css")
                .toExternalForm()
);

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
        root.setCenter(
    new FormularioLogin(this)
);
//mostrarInicio();
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
                    "Productos mas vendidos"
            );

    bienvenida.getStyleClass().add("label-mensaje");

    HBox filaProductos =
            new HBox(25);

    filaProductos.setAlignment(
            javafx.geometry.Pos.CENTER
    );

    ProductoDAO dao = new ProductoDAO();

for (Producto p : dao.leerProductos()) {

    filaProductos.getChildren().add(

            crearCardProducto(
                    p.getNombre(),
                    String.valueOf(p.getPrecio()),
                    p.getCodigo(),
                    p.getImagen(),
                    p.getStock()
)
    );
}

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
        String rutaImagen,
        int stock) {
        Button btnComprar = new Button("Comprar");
        btnComprar.getStyleClass().add("btn-comprar");

btnComprar.setOnAction(e -> {

    MainApp.codigoSeleccionado = codigo;

    MainApp.productoSeleccionado = nombre;

    MainApp.precioSeleccionado =
        Double.parseDouble(
                precio.replace("$", "")
        );

    abrirFormularioVentas();
});

    VBox card = new VBox(10);

    card.setAlignment(
            javafx.geometry.Pos.CENTER
    );

    card.setPrefSize(180, 250);

    card.getStyleClass().add("card");

  ImageView imagen = new ImageView();

try {

    if (rutaImagen.startsWith("/")) {

        imagen.setImage(
                new Image(
                        getClass()
                                .getResourceAsStream(
                                        rutaImagen
                                )
                )
        );

    } else {

        File archivo =
                new File(rutaImagen);

        if (archivo.exists()) {

            imagen.setImage(
                    new Image(
                            archivo.toURI().toString()
                    )
            );
        }
    }

} catch (Exception ex) {

    System.out.println(
            "No se pudo cargar: "
            + rutaImagen
    );

}

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
    
    Label lblStock =
        new Label(
                "Stock: " + stock
        );

card.getChildren().addAll(
        imagen,
        lblNombre,
        lblCodigo,
        lblPrecio,
        lblStock,
        btnComprar
);
    
    

    return card;
}
public void mostrarProductos() {

    workspace.getChildren().clear();

    BorderPane panelPrincipal = new BorderPane();

    panelPrincipal.setPadding(new Insets(20));

    Label titulo = new Label("PRODUCTOS");
    titulo.getStyleClass().add("titulo-formulario");

    FlowPane panelProductos = new FlowPane();

    panelProductos.setHgap(25);
    panelProductos.setVgap(25);
    panelProductos.setPadding(new Insets(20));
    panelProductos.setAlignment(Pos.CENTER);

    ProductoDAO dao = new ProductoDAO();

    for (Producto p : dao.leerProductos()) {

        panelProductos.getChildren().add(

                crearCardProducto(
                        p.getNombre(),
                        "$" + p.getPrecio(),
                        p.getCodigo(),
                        p.getImagen(),
                        p.getStock()
)
        );
    }

    ScrollPane scroll = new ScrollPane(panelProductos);

    scroll.setFitToWidth(true);
    scroll.setFitToHeight(true);

    scroll.getStyleClass().add("scroll-productos");

    VBox centro = new VBox(20);

    centro.setAlignment(Pos.TOP_CENTER);

    centro.getChildren().addAll(
            titulo,
            scroll
    );

    VBox.setVgrow(scroll, Priority.ALWAYS);

    panelPrincipal.setCenter(centro);

    if (usuarioActual.getRol().equals("ADMIN")) {

        Button btnAdministrar =
                new Button("Administrar");

        btnAdministrar.getStyleClass()
                .add("menu-btn");

        btnAdministrar.setPrefWidth(180);

        btnAdministrar.setOnAction(
                e -> abrirFormularioProductos()
        );

        VBox panelDerecho = new VBox();

        panelDerecho.setPadding(
                new Insets(20)
        );

        panelDerecho.setAlignment(
                Pos.TOP_CENTER
        );

        panelDerecho.getChildren()
                .add(btnAdministrar);

        panelPrincipal.setRight(
                panelDerecho
        );
    }

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
    
    private void abrirFormularioProductos() {

        workspace.getChildren().clear();
         FormularioProductos form =
            new FormularioProductos(
                    workspace,
                    this::mostrarInicio
            );
          workspace.getChildren().add(form);

    }
public void mostrarSistema(Usuario usuario) {

    usuarioActual = usuario;

    nombreUsuario.setText(
        usuario.getRol().equalsIgnoreCase("ADMIN")
        ? "Administrador"
        : "Cliente"
    );

    root.setLeft(menuLateral);
    root.setCenter(workspace);

    if (!usuario.getRol().equalsIgnoreCase("ADMIN")) {

    btnClientes.setVisible(false);
    btnClientes.setManaged(false);

    btnProveedores.setVisible(false);
    btnProveedores.setManaged(false);

    btnFinanzas.setVisible(false);
    btnFinanzas.setManaged(false);

    btnVentas.setText("☑ Facturas");

}

    mostrarInicio();
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