package View.UI;

import Domain.Entity.Characters.Players.EngineerMarcus;
import Domain.Entity.Characters.Players.KaelRiddle;
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

public class EscenaSeleccion extends SubScene {
    public EscenaSeleccion() {
        super();

        // Crear el HBox para los botones
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(10));
        hbox.setAlignment(Pos.CENTER_LEFT);

        Image iconjaxKane = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconJaxKane.png"
        )).toExternalForm());

        ImageView iconjaxKaneView = new ImageView(iconjaxKane);

        HBox personajesSeleccionados = new HBox(5);
        personajesSeleccionados.setTranslateX(TILE_SIZE);
        personajesSeleccionados.setTranslateY(TILE_SIZE * 17);

        Image iconCyborg = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconCyborg.png"
        )).toExternalForm());

        ImageView iconCyborgView = new ImageView(iconCyborg);

        Image iconEngineerMarcus = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconEngineerMarcus.png"
        )).toExternalForm());

        ImageView iconEngineerMarcusView = new ImageView(iconEngineerMarcus);

        Image iconDraLara = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconDraLara.png"
        )).toExternalForm());

        ImageView iconDraLaraView = new ImageView(iconDraLara);

        Image iconElenaDrakeCapitan = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconElenaDrakeCapitan.png"
        )).toExternalForm());

        ImageView iconElenaDrakeCapitanView = new ImageView(iconElenaDrakeCapitan);

        Image iconZaraQuinn = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconZaraQuinn.png"
        )).toExternalForm());

        ImageView iconZaraQuinnView = new ImageView(iconZaraQuinn);

        Image iconVeironSnake = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconVeironSnake.png"
        )).toExternalForm());

        ImageView iconVeironSnakeView = new ImageView(iconVeironSnake);

        Image iconKaelRiddle = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/iconKaelRiddle.png"
        )).toExternalForm());

        ImageView iconKaelRiddleView = new ImageView(iconKaelRiddle);

        //Imagenes
        Image cyborg = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaCyborg.png"
        )).toExternalForm());

        Image jaxKane = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaJaxKane.png"
        )).toExternalForm());

        Image EngineerMarcus = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaEngineerMarcus.png"
        )).toExternalForm());

        Image DraLara = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaDraLara.png"
        )).toExternalForm());

        Image ElenaDrakeCapitan = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaElenaDrakeCapitan.png"
        )).toExternalForm());

        Image ZaraQuinn = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaZaraQuinn.png"
        )).toExternalForm());

        Image KaelRiddle = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaKaelRiddle.png"
        )).toExternalForm());

        Image VeironSnake = new Image(Objects.requireNonNull(getClass().getResource(
                "/assets/textures/targetaVeironSnake.png"
        )).toExternalForm());

        //ImagesView
        ImageView imageView = new ImageView(cyborg);

        ImageView imageView1 = new ImageView(jaxKane);

        ImageView imageView2 = new ImageView(EngineerMarcus);

        ImageView imageView3 = new ImageView(DraLara);

        ImageView imageView4 = new ImageView(ElenaDrakeCapitan);

        ImageView imageView5 = new ImageView(ZaraQuinn);

        ImageView imageView6 = new ImageView(KaelRiddle);

        ImageView imageView7 = new ImageView(VeironSnake);

        //Botones
        Button jaxKaneButton = new Button();
        jaxKaneButton.setGraphic(imageView1);
        jaxKaneButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        Button CyborgButton = new Button();
        CyborgButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        CyborgButton.setGraphic(imageView);

        Button EngineerMarcusButton = new Button();
        EngineerMarcusButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");
        EngineerMarcusButton.setGraphic(imageView2);

        Button DraLaraButton = new Button();
        DraLaraButton.setGraphic(imageView3);
        DraLaraButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        Button ElenaDrakeCapitanButton = new Button();
        ElenaDrakeCapitanButton.setGraphic(imageView4);
        ElenaDrakeCapitanButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        Button ZaraQuinnButton = new Button();
        ZaraQuinnButton.setGraphic(imageView5);
        ZaraQuinnButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        Button KaelRiddleButton = new Button();
        KaelRiddleButton.setGraphic(imageView6);
        KaelRiddleButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        Button VeironSnakeButton = new Button();
        VeironSnakeButton.setGraphic(imageView7);
        VeironSnakeButton.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-background-insets: 0;");

        UI.animacionPresionarBoton(jaxKaneButton);
        UI.animacionPresionarBoton(CyborgButton);
        UI.animacionPresionarBoton(EngineerMarcusButton);
        UI.animacionPresionarBoton(DraLaraButton);
        UI.animacionPresionarBoton(ElenaDrakeCapitanButton);
        UI.animacionPresionarBoton(ZaraQuinnButton);
        UI.animacionPresionarBoton(KaelRiddleButton);
        UI.animacionPresionarBoton(VeironSnakeButton);

        jaxKaneButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconjaxKaneView);

        });

        CyborgButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconCyborgView);
        });

        EngineerMarcusButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconEngineerMarcusView);

        });

        DraLaraButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconDraLaraView);

        });

        ElenaDrakeCapitanButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconElenaDrakeCapitanView);

        });

        ZaraQuinnButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconZaraQuinnView);

        });

        KaelRiddleButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconKaelRiddleView);

        });

        VeironSnakeButton.setOnAction(e -> {
            personajesSeleccionados.getChildren().add(iconVeironSnakeView);

        });

        //Agregar botones en el Hbox
        hbox.getChildren().add(CyborgButton);
        hbox.getChildren().add(jaxKaneButton);
        hbox.getChildren().add(EngineerMarcusButton);
        hbox.getChildren().add(DraLaraButton);
        hbox.getChildren().add(ElenaDrakeCapitanButton);
        hbox.getChildren().add(ZaraQuinnButton);
        hbox.getChildren().add(KaelRiddleButton);
        hbox.getChildren().add(VeironSnakeButton);

        hbox.setTranslateY(TILE_SIZE * 6);

        // Crear el Slider para desplazarse horizontalmente
        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(0);
        slider.setPrefWidth(400);

        slider.setTranslateY(TILE_SIZE * 6);

        // A帽adir 13 botones al HBox
        for (int i = 1; i <= 15; i++) {
            Button btn = new Button("Bot贸n " + i);
            btn.setMinWidth(80);
            hbox.getChildren().add(btn);
        }

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
    }
               
}
            
