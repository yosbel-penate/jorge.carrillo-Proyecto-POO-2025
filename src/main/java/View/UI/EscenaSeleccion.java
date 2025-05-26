package View.UI;

import App.EntityFactory.PlayersFactory;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import com.almasb.fxgl.scene.SubScene;
import java.util.Objects;

import static Domain.Settings.SettingsGame.TILE_SIZE;
import static com.almasb.fxgl.dsl.FXGLForKtKt.*;

public class EscenaSeleccion extends SubScene {


    public static Boolean jaxKaneBool = false ;
    public static Boolean cyborgBool = false;
    public static Boolean zaraQuinnBool = false;
    public static Boolean marcusBool = false;

    public EscenaSeleccion() {
        super();

        // 1. Carga la textura desde assets/textures
        Image tex = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/fondoSeleccion.png"
        )).toExternalForm());

        // 2. Obtén el ImageView y ajústalo
        ImageView bg = new ImageView();
        bg.setImage(tex);
        bg.setFitWidth(getAppWidth());
        bg.setFitHeight(getAppHeight());
        bg.setPreserveRatio(false);

        getContentRoot().getChildren().add(bg);

        getGameWorld().addEntityFactory(new PlayersFactory());

        // Crear el HBox para los botones de seleccion de personajes
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER_LEFT);
        //Hbox para los iconos de los personajes seleccionados
        HBox personajesSeleccionados = new HBox(5);
        personajesSeleccionados.setTranslateX(TILE_SIZE);
        personajesSeleccionados.setTranslateY(TILE_SIZE * 17);

        //Imagenes representativas de los personajes
        //======================Iconos===============================
        //marcus

        Image iconMarcus = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconEngineerMarcus.png"
        )).toExternalForm());
        ImageView iconViewMarcus = new ImageView(iconMarcus);
        //zaraQuinn
        Image iconZaraQuinn = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconZaraQuinn.png"
        )).toExternalForm());
        ImageView iconViewZaraQuinn = new ImageView(iconZaraQuinn);
        //JaxKane
        Image iconjaxKane = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconJaxKane.png"
        )).toExternalForm());
        ImageView jaxKaneView = new ImageView(iconjaxKane);
        //Cyborg
        Image iconCyborg = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconCyborg.png"
        )).toExternalForm());
        ImageView iconCyborgView = new ImageView(iconCyborg);
        //====================Target==================================
        //Marcus
        Image marcus = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaEngineerMarcus.png"
        )).toExternalForm());
        //ZaraQuinn
        Image zaraQuinn = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaZaraQuinn.png"
        )).toExternalForm());
        //Cyborg
        Image cyborg = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaCyborg.png"
        )).toExternalForm());
        //JaxKane
        Image jaxKane = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaJaxKane.png"
        )).toExternalForm());

        //ImagesView de los target
        ImageView marcusView = new ImageView(marcus);
        ImageView zaraQuinnView = new ImageView(zaraQuinn);
        ImageView imageView = new ImageView(cyborg);
        ImageView imageView1 = new ImageView(jaxKane);

        //================Botones===============
        Button marcusButton = new Button();
        marcusButton.setGraphic(marcusView);
        marcusButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        Button zaraQuinnButton = new Button();
        zaraQuinnButton.setGraphic(zaraQuinnView);
        zaraQuinnButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //JaxKane
        Button jaxKaneButton = new Button();
        jaxKaneButton.setGraphic(imageView1);
        jaxKaneButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        //Cyborg
        Button CyborgButton = new Button();
        CyborgButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        CyborgButton.setGraphic(imageView);

        UI.animacionPresionarBoton(jaxKaneButton);
        UI.animacionPresionarBoton(CyborgButton);
        UI.animacionPresionarBoton(zaraQuinnButton);
        UI.animacionPresionarBoton(marcusButton);

        jaxKaneButton.setOnAction(e -> {
            if (!jaxKaneBool) {
                personajesSeleccionados.getChildren().add(jaxKaneView);
            } else {
                personajesSeleccionados.getChildren().remove(jaxKaneView);
            }

            jaxKaneBool = !jaxKaneBool; // alterna el valor booleano
        });

        CyborgButton.setOnAction(e -> {
            if (!cyborgBool){
                personajesSeleccionados.getChildren().add(iconCyborgView);

            }else {
                personajesSeleccionados.getChildren().remove(iconCyborgView);
            }
            cyborgBool = !cyborgBool;
        });

        zaraQuinnButton.setOnAction(e -> {
            if(!zaraQuinnBool){
                personajesSeleccionados.getChildren().add(iconViewZaraQuinn);

            }else {
                personajesSeleccionados.getChildren().remove(iconViewZaraQuinn);
            }
            zaraQuinnBool = !zaraQuinnBool;
        });

        marcusButton.setOnAction(e -> {
            if (!marcusBool){
                personajesSeleccionados.getChildren().add(iconViewMarcus);

            }else {
                personajesSeleccionados.getChildren().remove(iconViewMarcus);
            }
            marcusBool = !marcusBool;
        });


        //Agregar botones en el Hbox
        hbox.getChildren().add(marcusButton);
        hbox.getChildren().add(CyborgButton);
        hbox.getChildren().add(jaxKaneButton);
        hbox.getChildren().add(zaraQuinnButton);

        hbox.setTranslateY(TILE_SIZE * 6);

        // Crear el Slider para desplazarse horizontalmente
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(0);
        slider.setPrefWidth(400);

        slider.setTranslateY(TILE_SIZE * 6);


        //Boton para iniciar el juego
        Image imageBoton = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/selectBtn.png"
        )).toExternalForm());

        ImageView botonInciar = new ImageView();
        botonInciar.setImage(imageBoton);

        Button botonIniciar = new Button();
        botonIniciar.setGraphic(botonInciar);

        botonInciar.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");


        botonIniciar.setTranslateX(TILE_SIZE * 25);
        botonIniciar.setTranslateY(TILE_SIZE * 16 + 20);

        botonIniciar.setOnAction(e -> {
            FXGL.getGameController().startNewGame();
        });



        // Esperar a que se haya calculado el ancho del HBox para habilitar el listener
        hbox.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            // Ahora que tenemos el ancho correcto, actualizamos el slider listener
            slider.valueProperty().addListener((o, oldVal, newVal) -> {
                double maxTranslateX = newBounds.getWidth() - slider.getWidth();
                if (maxTranslateX < 0) maxTranslateX = 0; // evitar desplazamiento negativo
                double targetX = -newVal.doubleValue() * maxTranslateX;
                hbox.setTranslateX(targetX);
            });
        });

        // Crear un VBox para contener el HBox y el Slider
        VBox vbox = new VBox(10);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(slider);
        vbox.setPadding(new Insets(10));

        // Añadir el VBox al contenedor de la SubScene
        StackPane root = new StackPane(vbox);
        getContentRoot().getChildren().add(root);;
        getContentRoot().getChildren().add(personajesSeleccionados);
        getContentRoot().getChildren().add(botonIniciar);
    }
}
