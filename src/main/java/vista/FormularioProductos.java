package vista;

import dao.ProductoDAO;

import modelo.Producto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;

public class FormularioProductos extends VBox {

    private ProductoDAO dao = new ProductoDAO();
    private String rutaImagen = "";

    public FormularioProductos(StackPane parent,Runnable volverInicio) {
        this.getStylesheets().add(
                getClass().getResource("/sistemagestionfx/style.css").toExternalForm()
        );
        VBox panelDerecho = new VBox(8);
        

        panelDerecho.setPadding(new Insets(15));

        panelDerecho.getStyleClass().add("form-container");
        this.setAlignment(Pos.CENTER);

panelDerecho.setMaxWidth(500);
panelDerecho.setPrefWidth(500);

        Label titulo = new Label("GESTIÓN DE PRODUCTOS");
        titulo.getStyleClass().add("titulo-formulario");

        Label lblNombre = new Label("📦 Nombre");
        lblNombre.getStyleClass().add("label-form");

        TextField txtNombre = new TextField();
        txtNombre.getStyleClass().add("campo-texto");

        Label lblPrecio = new Label("💲 Precio");
        lblPrecio.getStyleClass().add("label-form");

        TextField txtPrecio = new TextField();
        txtPrecio.getStyleClass().add("campo-texto");

    
        Label lblStock = new Label("📊 Stock");
        lblStock.getStyleClass().add("label-form");

        TextField txtStock = new TextField();
        txtStock.setPromptText("Ej: 25");
        txtStock.getStyleClass().add("campo-texto");

        /* SOLO PERMITE NÚMEROS */
        TextFormatter<Integer> formatterStock
                = new TextFormatter<>(change -> {

                    if (change.getControlNewText()
                            .matches("\\d*")) {

                        return change;
                    }

                    return null;
                });

        txtStock.setTextFormatter(formatterStock);
        
        Label lblCodigo = new Label(" Codigo");
        lblCodigo.getStyleClass().add("label-form");

        TextField txtCodigo = new TextField();
        txtCodigo.getStyleClass().add("campo-texto");

        Label lblImagen = new Label("🖼 Imagen");
        lblImagen.getStyleClass().add("label-form");

        Button btnImagen = new Button("Seleccionar Imagen");
        btnImagen.getStyleClass().add("btn-secundario");

        ImageView preview = new ImageView();

        preview.setFitWidth(100);
        preview.setFitHeight(100);
        preview.setPreserveRatio(true);

        StackPane contenedorImagen = new StackPane(preview);

        contenedorImagen.getStyleClass().add("preview-producto");

        Button btnGuardar = new Button("Guardar");
        Button btnBuscar = new Button("Buscar");
        Button btnActualizar = new Button("Actualizar");
          Button btnEliminar = new Button("Eliminar");
        Button btnLimpiar = new Button("Limpiar");
        Button btnSalir = new Button("Salir");


        btnGuardar.getStyleClass().add("btn-guardar");
        btnBuscar.getStyleClass().add("btn-secundario");
        btnActualizar.getStyleClass().add("btn-actualizar");
       
        btnEliminar.getStyleClass().add("btn-eliminar");
        btnLimpiar.getStyleClass().add("btn-secundario");
        btnSalir.getStyleClass().add("btn-salir");

        HBox fila1 = new HBox(
                10,
                btnGuardar,
                btnBuscar,
                btnActualizar
        );

        fila1.setAlignment(Pos.CENTER);

       
  btnGuardar.setOnAction(e -> {

            if (txtNombre.getText().isEmpty()
                    || txtPrecio.getText().isEmpty()
                    || txtCodigo.getText().isEmpty()
                    || txtStock.getText().isEmpty()) {

                mostrarMensaje(
                        "Todos los campos son obligatorios"
                );

                return;
            }

            Producto existente
                    = dao.buscarProducto(
                            txtCodigo.getText()
                    );

            if (existente != null) {

                mostrarMensaje(
                        "Ya existe un Producto con ese Codigo"
                );

                return;
      }
     

      double precio;
      int stock;

      try {

          precio = Double.parseDouble(
                  txtPrecio.getText()
          );

          stock = Integer.parseInt(
                  txtStock.getText()
          );

      } catch (NumberFormatException ex) {

          mostrarMensaje(
                  "Precio o stock inválidos"
          );

          return;
      }if (rutaImagen.isEmpty()) {

    mostrarMensaje(
            "Debe seleccionar una imagen"
    );

    return;
}

Producto producto =
        new Producto(
                txtCodigo.getText(),
                txtNombre.getText(),
                precio,
                rutaImagen,
                stock
        );
            dao.agregarProducto(producto);

            mostrarMensaje(
                    "Producto guardado correctamente"
            );

            txtNombre.clear();
            txtPrecio.clear();
            txtStock.clear();
            txtCodigo.clear();
            
        });

        btnBuscar.setOnAction(e -> {

            Producto producto
                    = dao.buscarProducto(
                            txtCodigo.getText()
                    );

            if (producto != null) {

                txtNombre.setText(
                        producto.getNombre()
                );
                txtPrecio.setText(
        String.valueOf(
                producto.getPrecio()
        )
);

                txtStock.setText(
        String.valueOf(
                producto.getStock()
        )
);

            } else {

                mostrarMensaje(
                        "Producto no encontrado"
                );
            }
        });

        btnActualizar.setOnAction(e -> {
 double precio;
      int stock;

      try {

          precio = Double.parseDouble(
                  txtPrecio.getText()
          );

          stock = Integer.parseInt(
                  txtStock.getText()
          );

      } catch (NumberFormatException ex) {

          mostrarMensaje(
                  "Precio o stock inválidos"
          );

          return;
      }
         
            Producto producto =
        new Producto(
                txtCodigo.getText(),
                txtNombre.getText(),
                precio,
                rutaImagen,
                stock
        );

            if (dao.actualizarProducto(producto)) {

                mostrarMensaje(
                        "Producto actualizado"
                );

            } else {

                mostrarMensaje(
                        "Producto no encontrado"
                );
            }
        });

        btnEliminar.setOnAction(e -> {

            Alert confirmacion
                    = new Alert(Alert.AlertType.CONFIRMATION);

            confirmacion.setTitle("Confirmar eliminación");

            confirmacion.setHeaderText(null);

            confirmacion.setContentText(
                    "¿Está seguro de eliminar este producto?"
            );

            if (confirmacion.showAndWait().get()
                    == ButtonType.OK) {

                if (dao.eliminarProducto(
                        txtCodigo.getText())) {

                    mostrarMensaje(
                            "Producto eliminado"
                    );
                    

                    txtNombre.clear();
                    txtCodigo.clear();
                    txtPrecio.clear();
                    txtStock.clear();

                } else {

                    mostrarMensaje(
                            "Producto no encontrado"
                    );
                }
            }
        });

       btnImagen.setOnAction(e -> {

    FileChooser chooser = new FileChooser();

    chooser.setTitle("Seleccionar Imagen");

    chooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter(
                    "Imágenes",
                    "*.png",
                    "*.jpg",
                    "*.jpeg"
            )
    );

    File archivo =
            chooser.showOpenDialog(
                    getScene().getWindow()
            );

    if (archivo != null) {

        rutaImagen =
                archivo.getAbsolutePath();

        Image imagen =
                new Image(
                        archivo.toURI().toString()
                );

        preview.setImage(imagen);
    }
});

        btnLimpiar.setOnAction(e -> {

            txtNombre.clear();
            txtCodigo.clear();
            txtPrecio.clear();
            txtStock.clear();
        });
 btnSalir.setOnAction(e -> {
            volverInicio.run();
        });

        HBox fila2 = new HBox(
                10,
                btnEliminar,
                btnLimpiar,
                btnSalir
        );

        fila2.setAlignment(Pos.CENTER);
        
this.getChildren().add(panelDerecho);
        panelDerecho.getChildren().addAll(
    titulo,

    lblCodigo,
    txtCodigo,

    lblNombre,
    txtNombre,

    lblPrecio,
    txtPrecio,

    lblStock,
    txtStock,

    lblImagen,
    btnImagen,
    contenedorImagen,

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
    