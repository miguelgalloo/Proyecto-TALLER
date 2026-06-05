package vista;

import app.MainApp;
import dao.UsuarioDAO;
import modelo.Usuario;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FormularioLogin extends VBox {

   public FormularioLogin(MainApp app) {

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
 Button btnCerrar =
                new Button("⏻ Salir");

        btnCerrar.setOnAction(
                e -> System.exit(0)
        );
        btnCerrar.getStyleClass().add("btn-cerrar");
HBox barraSuperior = new HBox();

barraSuperior.setAlignment(Pos.TOP_RIGHT);

barraSuperior.getChildren().add(btnCerrar);

    Label tituloGrande =
            new Label("LICORERÍA");

    tituloGrande.getStyleClass().add(
            "titulo-login"
    );

    Label descripcion =
            new Label(
                    "Administra productos,\nventas, proveedores y finanzas\nfácilmente."
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

    card.getStyleClass().add(
            "login-card"
    );

    Label titulo =
            new Label("Iniciar Sesión");

    titulo.getStyleClass().add(
            "login-title"
    );

    Label subtitulo =
            new Label(
                    "Accede a tu cuenta"
            );

    subtitulo.getStyleClass().add(
            "login-subtitle"
    );

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
    

    Button btnIngresar =
            new Button("INGRESAR");

    btnIngresar.getStyleClass().add(
            "login-button"
    );

    UsuarioDAO dao =
            new UsuarioDAO();

    btnIngresar.setOnAction(e -> {

        Usuario usuario =
                dao.validarLogin(
                        txtUsuario.getText(),
                        txtClave.getText()
                );

        if (usuario != null) {

            app.mostrarSistema(usuario);

        } else {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR
                    );

            alert.setHeaderText(null);

            alert.setContentText(
                    "Usuario o contraseña incorrectos"
            );

            alert.showAndWait();
        }
    });
    Button btnRegistro =
        new Button("Crear cuenta");

btnRegistro.getStyleClass()
        .add("login-link");
btnRegistro.setOnAction(e -> {

    getChildren().clear();

    getChildren().add(
            new FormularioRegistro(app)
    );
});

    card.getChildren().addAll(
            barraSuperior,
            titulo,
            subtitulo,
            txtUsuario,
            txtClave,
            btnIngresar,
            btnRegistro,
            btnCerrar
    );

    panelDerecho.getChildren().add(card);

    root.getChildren().addAll(
            panelIzquierdo,
            panelDerecho
    );

    getChildren().add(root);
}
}