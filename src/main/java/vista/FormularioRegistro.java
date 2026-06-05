package vista;

import app.MainApp;
import dao.UsuarioDAO;
import modelo.Usuario;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FormularioRegistro extends VBox {

    public FormularioRegistro(MainApp app) {

        this.getStylesheets().add(
                getClass()
                        .getResource("/sistemagestionfx/login.css")
                        .toExternalForm()
        );

        setAlignment(Pos.CENTER);

        HBox root = new HBox();

        root.getStyleClass().add("login-root");

        root.setPrefSize(1200, 700);

        // ======================
        // PANEL IZQUIERDO
        // ======================

        VBox panelIzquierdo = new VBox(20);

        panelIzquierdo.getStyleClass().add(
                "panel-izquierdo"
        );

        Label tituloGrande =
                new Label("LICORERÍA");

        tituloGrande.getStyleClass().add(
                "titulo-login"
        );

        Label descripcion =
                new Label(
                        "Crea tu cuenta y accede\nal sistema para realizar\ncompras y consultar facturas."
                );

        descripcion.getStyleClass().add(
                "descripcion-login"
        );

        panelIzquierdo.getChildren().addAll(
                tituloGrande,
                descripcion
        );

        // ======================
        // PANEL DERECHO
        // ======================

        VBox panelDerecho = new VBox();

        panelDerecho.setAlignment(Pos.CENTER);

        panelDerecho.getStyleClass().add(
                "panel-derecho"
        );

        VBox card = new VBox(15);

        card.setAlignment(Pos.CENTER);

        card.setPrefWidth(380);

        card.setMaxWidth(380);

        card.getStyleClass().add(
                "login-card"
        );

        // ======================
        // BOTÓN CERRAR
        // ======================

        Button btnCerrar =
                new Button("⏻ Salir");

        btnCerrar.getStyleClass().add(
                "btn-cerrar"
        );

        btnCerrar.setOnAction(
                e -> System.exit(0)
        );

        HBox barraSuperior = new HBox();

        barraSuperior.setAlignment(
                Pos.CENTER_RIGHT
        );

        barraSuperior.setMaxWidth(
                Double.MAX_VALUE
        );

        

        // ======================
        // TÍTULOS
        // ======================

        Label titulo =
                new Label("Crear Cuenta");

        titulo.getStyleClass().add(
                "login-title"
        );

        Label subtitulo =
                new Label(
                        "Registra un nuevo usuario"
                );

        subtitulo.getStyleClass().add(
                "login-subtitle"
        );

        // ======================
        // CAMPOS
        // ======================

        TextField txtUsuario =
                new TextField();

        txtUsuario.setPromptText(
                "Usuario"
        );

        txtUsuario.getStyleClass().add(
                "login-field"
        );

        PasswordField txtClave =
                new PasswordField();

        txtClave.setPromptText(
                "Contraseña"
        );

        txtClave.getStyleClass().add(
                "login-field"
        );

        ComboBox<String> cmbRol =
                new ComboBox<>();

        cmbRol.getItems().addAll(
                "CLIENTE",
                "ADMIN"
        );

        cmbRol.setPromptText(
                "Seleccione Rol"
        );

        cmbRol.getStyleClass().add(
                "login-field"
        );

     
        Button btnRegistrar =
                new Button("REGISTRAR");

        btnRegistrar.getStyleClass().add(
                "login-button"
        );

       btnRegistrar.setOnAction(e -> {

    if (txtUsuario.getText().isEmpty()
            || txtClave.getText().isEmpty()
            || cmbRol.getValue() == null) {

        Alert alert =
                new Alert(Alert.AlertType.WARNING);

        alert.setHeaderText(null);

        alert.setContentText(
                "Complete todos los campos"
        );

        alert.showAndWait();

        return;
    }

    Usuario usuario = new Usuario();

    usuario.setUsuario(
            txtUsuario.getText()
    );

    usuario.setClave(
            txtClave.getText()
    );

    usuario.setRol(
            cmbRol.getValue()
    );

    UsuarioDAO dao =
            new UsuarioDAO();

    dao.guardarUsuario(usuario);

    Alert alert =
            new Alert(Alert.AlertType.INFORMATION);

    alert.setHeaderText(null);

    alert.setContentText(
            "Usuario registrado correctamente"
    );

    alert.showAndWait();
});

        // ======================
        // VOLVER AL LOGIN
        // ======================

        Button btnVolver =
                new Button(
                        "Volver al Login"
                );

        btnVolver.getStyleClass().add(
                "login-link"
        );

        btnVolver.setOnAction(e -> {

            getChildren().clear();

            getChildren().add(
                    new FormularioLogin(app)
            );
        });

        card.getChildren().addAll(
                barraSuperior,
                titulo,
                subtitulo,
                txtUsuario,
                txtClave,
                cmbRol,
                btnRegistrar,
                btnVolver,
                btnCerrar
        );

        panelDerecho.getChildren().add(
                card
        );

        root.getChildren().addAll(
                panelIzquierdo,
                panelDerecho
        );

        getChildren().add(root);
    }
}