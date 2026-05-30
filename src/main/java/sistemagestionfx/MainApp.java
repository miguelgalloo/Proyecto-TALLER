package sistemagestionfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    private StackPane workspace;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        workspace = new StackPane();

        workspace.setStyle("-fx-background-color: #f4f4f4;");

        // ===== MENU BAR =====

        MenuBar menuBar = new MenuBar();

        // ===== CLIENTE =====

        Menu menuCliente = new Menu("Cliente");

        MenuItem itemCliente =
                new MenuItem("Gestionar Cliente");

        itemCliente.setOnAction(
                e -> abrirFormularioCliente()
        );

        menuCliente.getItems().add(itemCliente);

        // ===== PROVEEDOR =====

        Menu menuProveedor = new Menu("Proveedor");

        MenuItem itemProveedor =
                new MenuItem("Gestionar Proveedor");

        itemProveedor.setOnAction(
                e -> abrirFormularioProveedor()
        );

        menuProveedor.getItems().add(itemProveedor);

        // ===== VENTAS =====

        Menu menuVentas = new Menu("Ventas");

        MenuItem itemVentas =
                new MenuItem("Gestionar Ventas");

        itemVentas.setOnAction(
                e -> abrirFormularioVentas()
        );

        menuVentas.getItems().add(itemVentas);

        // ===== FINANZAS =====

        Menu menuFinanzas = new Menu("Finanzas");

        MenuItem itemFinanzas =
                new MenuItem("Ver Reporte");

        itemFinanzas.setOnAction(
                e -> abrirFormularioFinanzas()
        );

        menuFinanzas.getItems().add(itemFinanzas);

        // ===== SALIR =====

        Menu menuSalir = new Menu("Salir");

        MenuItem itemSalir =
                new MenuItem("Cerrar Aplicación");

        itemSalir.setOnAction(
                e -> System.exit(0)
        );

        menuSalir.getItems().add(itemSalir);

        // ===== AGREGAR MENUS =====

        menuBar.getMenus().addAll(
                menuCliente,
                menuProveedor,
                menuVentas,
                menuFinanzas,
                menuSalir
        );

        root.setTop(menuBar);

        root.setCenter(workspace);

        Scene scene = new Scene(root, 1000, 700);

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
                    "Debe salir desde el menú Salir."
            );

            alert.showAndWait();
        });

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